package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 成员
 *
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
public class Member {
    private String guildId;
    private User user;
    private String nick;
    private String[] roles;
    private String joinedAt;
}
