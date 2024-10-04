package com.github.lzhuii.ricebot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hui
 * @since 2024-09-29
 */
@EnableScheduling
@SpringBootApplication
public class RiceBotApplication implements ApplicationRunner {

    QQWebSocketClient qqWebSocketClient;

    public RiceBotApplication(QQWebSocketClient qqWebSocketClient) {
        this.qqWebSocketClient = qqWebSocketClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(RiceBotApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        qqWebSocketClient.connect().subscribe();
    }
}
