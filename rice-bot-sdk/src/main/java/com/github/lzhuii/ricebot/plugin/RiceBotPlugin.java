package com.github.lzhuii.ricebot.plugin;

import com.github.lzhuii.ricebot.bean.Payload;

/**
 * RiceBot 插件接口
 *
 * @author hui
 * @since 2024-09-29
 */
public interface RiceBotPlugin {

    String getCommand();

    void execute(Payload payload);

}
