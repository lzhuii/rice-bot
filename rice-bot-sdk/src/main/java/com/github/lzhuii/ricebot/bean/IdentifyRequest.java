package com.github.lzhuii.ricebot.bean;

import lombok.*;

/**
 * 鉴权连接
 *
 * @author hui
 * @since 2024-09-30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentifyRequest {
    /**
     * 格式为"QQBot {AccessToken}"
     */
    private String token;
    /**
     * 连接所需要接收的事件
     */
    private Integer intents;
    /**
     * 考虑到开发者事件接收时可以实现负载均衡，QQ 提供了分片逻辑，事件通知会落在不同的分片上，该参数是个拥有两个元素的数组。
     */
    private int[] shard;
    /**
     * 目前无实际作用，可以按照自己的实际情况填写，也可以留空
     */
    private String properties;
}

