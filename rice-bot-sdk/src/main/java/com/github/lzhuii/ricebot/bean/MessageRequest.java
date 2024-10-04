package com.github.lzhuii.ricebot.bean;

import lombok.*;

/**
 * @author hui
 * @since 2024-09-30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String content;
    private Integer msgType;
    private String msgId;
}
