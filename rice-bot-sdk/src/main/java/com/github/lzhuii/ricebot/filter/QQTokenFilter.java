package com.github.lzhuii.ricebot.filter;

import com.github.lzhuii.ricebot.service.QQTokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

/**
 * @author hui
 * @since 2024-09-29
 */
@Component
public class QQTokenFilter implements ExchangeFilterFunction {

    QQTokenService qqTokenService;

    public QQTokenFilter(QQTokenService qqTokenService) {
        this.qqTokenService = qqTokenService;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        ClientRequest newRequest = ClientRequest.from(request)
                .header("Authorization", "QQBot " + qqTokenService.getAccessToken())
                .build();
        return next.exchange(newRequest);
    }
}
