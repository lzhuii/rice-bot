package com.github.lzhuii.ricebot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取调用凭证请求参数
 *
 * @author hui
 * @since 2024-09-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    /**
     * 在开放平台管理端上获得
     */
    private String appId;
    /**
     * 在开放平台管理端上获得
     */
    private String clientSecret;
}
