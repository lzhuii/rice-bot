package com.github.lzhuii.ricebot.constant;

import lombok.Getter;

/**
 * @author hui
 * @since 2024-10-01
 */
@Getter
public enum Intents {

    GUILDS(1 << 0),
    GUILDS_MEMBERS(1 << 1),
    GUILDS_MESSAGES(1 << 9),
    GUILD_MESSAGE_REACTIONS(1 << 10),
    DIRECT_MESSAGE(1 << 12),
    GROUP_AND_C2C_EVENT(1 << 25),
    INTERACTION(1 << 26),
    MESSAGE_AUDIT(1 << 27),
    FORUMS_EVENT(1 << 28),
    AUDIO_ACTION(1 << 29),
    PUBLIC_GUILD_MESSAGES(1 << 30);

    private final Integer value;

    Intents(Integer value) {
        this.value = value;
    }

    public static Integer getIntentsValue(Intents... intents) {
        Integer result = 0;
        for (Intents intent : intents) {
            result |= intent.getValue();
        }
        return result;
    }

}
