package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 频道
 *
 * @author hui
 * @since 2024-10-01
 */
@Getter
@Setter
public class Guild {
    /**
     * 频道ID
     */
    private String id;
    /**
     * 频道名称
     */
    private String name;
    /**
     * 频道头像地址
     */
    private String icon;
    /**
     * 创建人用户ID
     */
    private String ownerId;
    /**
     * 当前人是否是创建人
     */
    private Boolean owner;
    /**
     * 成员数
     */
    private Integer memberCount;
    /**
     * 最大成员数
     */
    private Integer maxMembers;
    /**
     * 描述
     */
    private String description;
    /**
     * 加入时间
     */
    private String joinedAt;
}
