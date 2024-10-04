package com.github.lzhuii.ricebot.plugin.amap.config;

import com.github.lzhuii.ricebot.plugin.amap.api.AmapApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author hui
 * @since 2024-09-30
 */
@Configuration
public class AmapConfig {

    @Bean
    AmapApi amapApi(ExchangeStrategies exchangeStrategies) {
        WebClient client = WebClient.builder()
                .baseUrl("https://restapi.amap.com")
                .exchangeStrategies(exchangeStrategies)
                .build();
        WebClientAdapter adapter = WebClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AmapApi.class);
    }

}
