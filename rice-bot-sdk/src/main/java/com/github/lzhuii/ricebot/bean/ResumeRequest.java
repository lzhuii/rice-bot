package com.github.lzhuii.ricebot.bean;

import lombok.*;

/**
 * 恢复连接
 *
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeRequest {
    /**
     * 格式为"QQBot {AccessToken}"
     */
    private String token;
    /**
     * 会话 ID
     */
    private String sessionId;
    /**
     * 最后一个消息序号
     */
    private Integer seq;
}
