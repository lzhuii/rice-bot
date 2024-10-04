package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 子频道
 *
 * @author hui
 * @since 2024-10-01
 */
@Getter
@Setter
public class Channel {
    /**
     * 子频道 id
     */
    private String id;
    /**
     * 频道 id
     */
    private String guildId;
    /**
     * 子频道名
     */
    private String name;
    /**
     * 子频道类型 ChannelType
     */
    private Integer type;
    /**
     * 子频道子类型 ChannelSubType
     */
    private Integer subType;
    /**
     * 排序值 具体请参考 有关 position 的说明
     */
    private Integer position;
    /**
     * 所属分组 id 仅对子频道有效 对 子频道分组（ChannelType=4） 无效
     */
    private String parentId;
    /**
     * 创建人 id
     */
    private String ownerId;
    /**
     * 子频道私密类型 PrivateType
     */
    private Integer privateType;
    /**
     * 子频道发言权限 SpeakPermission
     */
    private Integer speakPermission;
    /**
     * 用于标识应用子频道应用类型 仅应用子频道时会使用该字段 具体定义请参考 应用子频道的应用类型
     */
    private String applicationId;
    /**
     * 用户拥有的子频道权限 Permissions
     */
    private String permissions;
}
