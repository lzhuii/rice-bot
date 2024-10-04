package com.github.lzhuii.ricebot.constant;

import lombok.Getter;

/**
 * @author hui
 * @since 2024-10-01
 */
@Getter
public enum ChannelType {
    TEXT_CHANNEL(0, "文字子频道"),
    VOICE_CHANNEL(2, "语音子频道"),
    CHANNEL_GROUP(4, "子频道分组"),
    LIVE_CHANNEL(10005, "直播子频道"),
    APPLICATION_CHANNEL(10006, "应用子频道"),
    FORUM_CHANNEL(10007, "论坛子频道");

    private final Integer value;
    private final String description;

    ChannelType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ChannelType getChannelType(Integer value) {
        for (ChannelType channelType : values()) {
            if (channelType.getValue().equals(value)) {
                return channelType;
            }
        }
        return null;
    }
}
