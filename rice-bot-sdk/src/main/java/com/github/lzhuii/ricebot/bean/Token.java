package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 调用凭证
 *
 * @author hui
 * @since 2024-09-29
 */
@Getter
@Setter
public class Token {
    /**
     * 获取到的凭证
     */
    private String accessToken;
    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值
     */
    private Long expiresIn;
}
