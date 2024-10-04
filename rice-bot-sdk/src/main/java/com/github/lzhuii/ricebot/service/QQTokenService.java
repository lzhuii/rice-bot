package com.github.lzhuii.ricebot.service;

import com.github.lzhuii.ricebot.api.QQTokenApi;
import com.github.lzhuii.ricebot.bean.Token;
import com.github.lzhuii.ricebot.bean.TokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hui
 * @since 2024-10-01
 */
@Slf4j
@Service
public class QQTokenService {

    RedisTemplate<String, String> redisTemplate;
    QQTokenApi qqTokenApi;

    public QQTokenService(RedisTemplate<String, String> redisTemplate, QQTokenApi qqTokenApi) {
        this.redisTemplate = redisTemplate;
        this.qqTokenApi = qqTokenApi;
    }

    /**
     * 获取凭证
     *
     * @return access_token
     */
    public String getAccessToken() {
        // 从缓存获取 access_token
        String accessToken = redisTemplate.opsForValue().get("access_token");
        if (accessToken == null) {
            // 如果缓存没有 access_token，则调用 API 获取新的 access_token
            String appId = System.getenv("QQ_BOT_APP_ID");
            String appSecret = System.getenv("QQ_BOT_APP_SECRET");
            TokenRequest tokenRequest = new TokenRequest(appId, appSecret);
            Token token = qqTokenApi.getAccessToken(tokenRequest);
            accessToken = token.getAccessToken();
            // 将 access_token 写入缓存
            redisTemplate.opsForValue().set("access_token", accessToken, token.getExpiresIn(), TimeUnit.SECONDS);
        }
        return accessToken;
    }
}
