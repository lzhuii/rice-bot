package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hui
 * @since 2024-09-30
 */
@Getter
@Setter
public class Message {
    private String id;
    private String channelId;
    private String guildId;
    private String groupId;
    private String content;
    private String timestamp;
    private String editedTimestamp;
    private Boolean mentionEveryone;
    private User author;
    private List<MessageAttachment> attachments;
    private List<MessageEmbed> embeds;
    private List<User> mentions;
    private Member member;
    private MessageArk ark;
    private Integer seq;
    private String seqInChannel;
    private MessageReference messageReference;
}
