package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Session 限制信息
 *
 * @author hui
 * @since 2024-09-29
 */
@Getter
@Setter
public class SessionStartLimit {
    /**
     * 每 24 小时可创建 Session 数
     */
    private Integer total;
    /**
     * 目前还可以创建的 Session 数
     */
    private Integer remaining;
    /**
     * 重置计数的剩余时间(ms)
     */
    private Integer resetAfter;
    /**
     * 每 5s 可以创建的 Session 数
     */
    private Integer maxConcurrency;
}
