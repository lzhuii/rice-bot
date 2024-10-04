package com.github.lzhuii.ricebot.api;

import com.github.lzhuii.ricebot.bean.Gateway;
import com.github.lzhuii.ricebot.bean.Message;
import com.github.lzhuii.ricebot.bean.MessageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author hui
 * @since 2024-09-29
 */
public interface QQBotApi {

    @GetExchange("/gateway")
    Gateway getGateway();

    @PostExchange("/v2/users/{userId}/messages")
    Message sendUserMessage(@PathVariable String userId, @RequestBody MessageRequest request);

    @PostExchange("/channels/{channelId}/messages")
    Message sendChannelMessage(@PathVariable String channelId, @RequestBody MessageRequest request);

    @PostExchange("/v2/groups/{groupId}/messages")
    Message sendGroupMessage(@PathVariable String groupId, @RequestBody MessageRequest request);

    @PostExchange("/dms/{guildId}/messages")
    Message sendDirectMessage(@PathVariable String guildId, @RequestBody MessageRequest request);

}
