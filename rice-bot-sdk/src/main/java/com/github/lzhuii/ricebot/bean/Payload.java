package com.github.lzhuii.ricebot.bean;

import lombok.*;

/**
 * WebSocket 消息体
 *
 * @author hui
 * @since 2024-09-30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    /**
     * 消息 ID
     */
    private String id;
    /**
     * 操作
     */
    private Integer op;
    /**
     * 序号
     */
    private Integer s;
    /**
     * 事件
     */
    private String t;
    /**
     * 数据对象
     */
    private Object d;
}
