package com.github.lzhuii.ricebot.plugin.amap;

import com.github.lzhuii.ricebot.api.QQBotApi;
import com.github.lzhuii.ricebot.bean.Message;
import com.github.lzhuii.ricebot.bean.MessageRequest;
import com.github.lzhuii.ricebot.bean.Payload;
import com.github.lzhuii.ricebot.plugin.RiceBotPlugin;
import com.github.lzhuii.ricebot.plugin.amap.api.AmapApi;
import com.github.lzhuii.ricebot.plugin.amap.bean.Geo;
import com.github.lzhuii.ricebot.plugin.amap.bean.GeoResponse;
import com.github.lzhuii.ricebot.plugin.amap.bean.LiveWeather;
import com.github.lzhuii.ricebot.plugin.amap.bean.WeatherResponse;
import com.github.lzhuii.ricebot.util.JsonUtil;
import com.github.lzhuii.ricebot.util.QQCommandUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author hui
 * @since 2024-09-30
 */
@Slf4j
@Component
public class AmapWeatherPlugin implements RiceBotPlugin {

    QQBotApi qqBotApi;
    AmapApi amapApi;

    public AmapWeatherPlugin(QQBotApi qqBotApi, AmapApi amapApi) {
        this.qqBotApi = qqBotApi;
        this.amapApi = amapApi;
    }

    @Override
    public String getCommand() {
        return "/天气";
    }

    @Override
    public void execute(Payload payload) {
        Message message = JsonUtil.toBean(payload.getD(), Message.class);
        String[] result = QQCommandUtil.resolveMessage(message.getContent());
        String content = getWeather(result[2]);
        MessageRequest messageRequest = MessageRequest.builder()
                .msgId(message.getId())
                .msgType(0)
                .content(content)
                .build();
        switch (payload.getT()) {
            case "C2C_MESSAGE_CREATE" -> {
                Message response = qqBotApi.sendUserMessage(message.getAuthor().getId(), messageRequest);
                log.info("发送单聊消息返回结果 {}", JsonUtil.toJson(response));
            }
            case "MESSAGE_CREATE" -> {
                Message response = qqBotApi.sendChannelMessage(message.getChannelId(), messageRequest);
                log.info("发送频道群聊消息返回结果 {}", JsonUtil.toJson(response));
            }
            case "DIRECT_MESSAGE_CREATE" -> {
                Message response = qqBotApi.sendDirectMessage(message.getGuildId(), messageRequest);
                log.info("发送频道私聊消息返回结果 {}", JsonUtil.toJson(response));
            }
            case "GROUP_AT_MESSAGE_CREATE" -> {
                Message response = qqBotApi.sendGroupMessage(message.getGroupId(), messageRequest);
                log.info("发送群消息返回结果 {}", JsonUtil.toJson(response));
            }
        }
    }

    private String getWeather(String address) {
        String key = System.getenv("AMAP_KEY");
        GeoResponse geoResponse = amapApi.getGeoCode(key, address);
        Geo geo = geoResponse.getGeocodes().getFirst();
        WeatherResponse weatherResponse = amapApi.getWeather(key, geo.getAdcode());
        LiveWeather weather = weatherResponse.getLives().getFirst();
        return weather.getProvince() + weather.getCity() + "\n天气 " + weather.getWeather()
                + "\n温度 " + weather.getTemperature()
                + "\n湿度 " + weather.getHumidity()
                + "\n风向 " + weather.getWinddirection()
                + "\n风力 " + weather.getWindpower();
    }
}
