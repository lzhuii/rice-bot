package com.github.lzhuii.ricebot.constant;

import lombok.Getter;

/**
 * @author hui
 * @since 2024-10-01
 */
@Getter
public enum OpCode {

    DISPATCH(0, "服务端进行消息推送"),
    HEARTBEAT(1, "客户端或服务端发送心跳"),
    IDENTIFY(2, "客户端发送鉴权"),
    RESUME(6, "客户端恢复连接"),
    RECONNECT(7, "服务端通知客户端重新连接"),
    INVALID_SESSION(9, "当 identify 或 resume 的时候，如果参数有错，服务端会返回该消息"),
    HELLO(10, "当客户端与网关建立 ws 连接之后，网关下发的第一条消息"),
    HEARTBEAT_ACK(11, "当发送心跳成功之后，就会收到该消息"),
    HTTP_CALLBACK_ACK(12, "仅用于 http 回调模式的回包，代表机器人收到了平台推送的数据");

    private final Integer value;
    private final String description;

    OpCode(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static OpCode getOpCode(Integer value) {
        for (OpCode opCode : OpCode.values()) {
            if (opCode.getValue().equals(value)) {
                return opCode;
            }
        }
        return null;
    }

}
