package com.github.lzhuii.ricebot.api;

import com.github.lzhuii.ricebot.bean.Token;
import com.github.lzhuii.ricebot.bean.TokenRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author hui
 * @since 2024-09-29
 */
public interface QQTokenApi {

    @PostExchange("/app/getAppAccessToken")
    Token getAccessToken(@RequestBody TokenRequest request);

}
