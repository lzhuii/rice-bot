package com.github.lzhuii.ricebot;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.lzhuii.ricebot.api.QQBotApi;
import com.github.lzhuii.ricebot.bean.IdentifyRequest;
import com.github.lzhuii.ricebot.bean.Message;
import com.github.lzhuii.ricebot.bean.Payload;
import com.github.lzhuii.ricebot.bean.ResumeRequest;
import com.github.lzhuii.ricebot.constant.Intents;
import com.github.lzhuii.ricebot.constant.OpCode;
import com.github.lzhuii.ricebot.plugin.RiceBotPlugin;
import com.github.lzhuii.ricebot.service.QQTokenService;
import com.github.lzhuii.ricebot.util.JsonUtil;
import com.github.lzhuii.ricebot.util.QQCommandUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;

/**
 * QQ WebSocket 客户端
 *
 * @author hui
 * @since 2024-10-02
 */
@Slf4j
@Setter
@Getter
@Component
public class QQWebSocketClient {

    private final QQTokenService qqTokenService;
    private final QQBotApi qqBotApi;
    private final HashMap<String, RiceBotPlugin> plugins;

    private WebSocketSession session;
    /**
     * 是否鉴权成功
     */
    private boolean identified = false;
    /**
     * 是否重试连接
     */
    private boolean retried = false;
    /**
     * WebSocket 会话 ID
     */
    private String sessionId;
    /**
     * WebSocket 消息序号
     */
    private Integer seq;

    public QQWebSocketClient(QQTokenService qqTokenService, QQBotApi qqBotApi, HashMap<String, RiceBotPlugin> plugins) {
        this.qqTokenService = qqTokenService;
        this.qqBotApi = qqBotApi;
        this.plugins = plugins;
    }

    /**
     * 连接
     *
     * @return Mono<Void>
     */
    public Mono<Void> connect() {
        return Mono.fromCallable(qqBotApi::getGateway)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(gateway -> {
                    URI uri = URI.create(gateway.getUrl());
                    ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
                    return client.execute(uri, webSocketHandler());
                })
                .then();
    }

    /**
     * 重连
     */
    public void reconnect() {
        // 修改重连状态
        setRetried(true);
        // 等待1秒后重连
        Mono.delay(Duration.ofSeconds(1))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(tick -> connect())
                .subscribe();
    }

    /**
     * WebSocket 生命周期
     *
     * @return WebSocketHandler对象
     */
    private WebSocketHandler webSocketHandler() {
        return session -> session.receive()
                .doOnSubscribe(subscription -> doOnSubscribe(session))
                .doOnRequest(request -> log.info("WebSocket 请求处理中"))
                .doOnCancel(() -> log.info("WebSocket 连接已取消"))
                .doOnError(error -> log.error("WebSocket 错误", error))
                .map(WebSocketMessage::getPayloadAsText)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(this::doOnNext)
                .doOnTerminate(this::doOnTerminate)
                .then();
    }

    private void doOnSubscribe(WebSocketSession session) {
        log.info("WebSocket 连接已订阅");
        setSession(session);
    }

    private void doOnNext(String message) {
        log.info("收到 WebSocket 消息 {}", message);
        Payload payload = JsonUtil.toBean(message, Payload.class);
        OpCode opCode = OpCode.getOpCode(payload.getOp());
        if (opCode != null) {
            switch (opCode) {
                case HELLO -> hello();
                case DISPATCH -> dispatch(payload);
            }
        }
    }

    private void doOnTerminate() {
        log.info("WebSocket 连接已终止");
        setIdentified(false);
        if (!isRetried()) {
            reconnect();
        }
    }

    /**
     * 发送 WebSocket 消息
     *
     * @param message 字符串
     */
    private void send(String message) {
        log.info("发送 WebSocket 消息 {}", message);
        session.send(Mono.just(session.textMessage(message))).subscribe();
    }

    /**
     * 发送 WebSocket 消息
     *
     * @param obj 对象
     */
    private void send(Object obj) {
        send(JsonUtil.toJson(obj));
    }

    /**
     * 建立连接成功
     */
    private void hello() {
        if (getSessionId() == null) {
            // 首次鉴权
            identify();
        } else {
            // 重连鉴权
            resume();
        }
    }

    /**
     * 首次鉴权
     */
    private void identify() {
        IdentifyRequest identifyRequest = IdentifyRequest.builder()
                .token("QQBot " + qqTokenService.getAccessToken())
                .intents(Intents.getIntentsValue(Intents.values()))
                .shard(new int[]{0, 1})
                .build();
        Payload payload = Payload.builder()
                .op(OpCode.IDENTIFY.getValue())
                .d(identifyRequest)
                .build();
        send(payload);
    }

    /**
     * 重连鉴权
     */
    private void resume() {
        ResumeRequest resumeRequest = ResumeRequest.builder()
                .token("QQBot " + qqTokenService.getAccessToken())
                .sessionId(sessionId)
                .seq(seq)
                .build();
        Payload payload = Payload.builder()
                .op(OpCode.RESUME.getValue())
                .d(resumeRequest)
                .build();
        send(payload);
    }

    /**
     * 消息处理
     *
     * @param payload WebSocket 消息体
     */
    private void dispatch(Payload payload) {
        seq = payload.getS();
        switch (payload.getT()) {
            case "READY" -> ready(payload);
            case "RESUMED" -> resumed();
            case "C2C_MESSAGE_CREATE",
                 "MESSAGE_CREATE",
                 "AT_MESSAGE_CREATE",
                 "GROUP_AT_MESSAGE_CREATE",
                 "DIRECT_MESSAGE_CREATE" -> {
                Message message = JsonUtil.toBean(payload.getD(), Message.class);
                String[] resolvedMessage = QQCommandUtil.resolveMessage(message.getContent());
                String command = resolvedMessage[resolvedMessage.length - 2];
                RiceBotPlugin plugin = plugins.get(command);
                if (plugin != null) {
                    plugin.execute(payload);
                }
            }
        }
    }

    /**
     * 连接就绪
     *
     * @param payload WebSocket 消息体
     */
    private void ready(Payload payload) {
        JsonNode json = JsonUtil.toNode(payload.getD());
        setSessionId(json.get("session_id").asText());
        setIdentified(true);
        setRetried(false);
    }

    /**
     * 重连成功
     */
    private void resumed() {
        setIdentified(true);
        setRetried(false);
    }

    /**
     * 心跳
     */
    @Scheduled(fixedRate = 41250)
    private void heartbeat() {
        if (isIdentified()) {
            Payload payload = Payload.builder()
                    .op(1)
                    .d(seq)
                    .build();
            send(payload);
        }
    }
}
