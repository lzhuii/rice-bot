package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * WebSocket 网关
 *
 * @author hui
 * @since 2024-09-29
 */
@Getter
@Setter
public class Gateway {
    /**
     * WebSocket 的连接地址
     */
    private String url;
    /**
     * 建议的 shard 数
     */
    private Integer shards;
    /**
     * 创建 Session 限制信息
     */
    private SessionStartLimit sessionStartLimit;
}
