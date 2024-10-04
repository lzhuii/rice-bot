package com.github.lzhuii.ricebot.plugin.amap.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
public class WeatherResponse extends AmapResponse {
    private List<LiveWeather> lives;
}
