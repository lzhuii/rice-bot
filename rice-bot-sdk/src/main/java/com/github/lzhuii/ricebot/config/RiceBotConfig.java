package com.github.lzhuii.ricebot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lzhuii.ricebot.api.QQBotApi;
import com.github.lzhuii.ricebot.api.QQTokenApi;
import com.github.lzhuii.ricebot.filter.QQTokenFilter;
import com.github.lzhuii.ricebot.plugin.RiceBotPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.HashMap;
import java.util.List;

/**
 * @author hui
 * @since 2024-09-29
 */
@Configuration
public class RiceBotConfig {

    @Bean
    HashMap<String, RiceBotPlugin> botPluginHashMap(List<RiceBotPlugin> plugins) {
        HashMap<String, RiceBotPlugin> botPluginHashMap = new HashMap<>();
        for (RiceBotPlugin plugin : plugins) {
            botPluginHashMap.put(plugin.getCommand(), plugin);
        }
        return botPluginHashMap;
    }

    @Bean
    ExchangeStrategies exchangeStrategies(ObjectMapper objectMapper) {
        return ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
                .build();
    }

    @Bean
    QQTokenApi qqTokenApi(ExchangeStrategies exchangeStrategies) {
        WebClient client = WebClient.builder()
                .baseUrl("https://bots.qq.com")
                .exchangeStrategies(exchangeStrategies)
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(QQTokenApi.class);
    }

    @Bean
    QQBotApi qqBotApi(ExchangeStrategies exchangeStrategies, QQTokenFilter qqTokenFilter) {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.sgroup.qq.com")
                .exchangeStrategies(exchangeStrategies)
                .filter(qqTokenFilter)
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(QQBotApi.class);
    }
}
