package com.github.lzhuii.ricebot.plugin.amap.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
public class LiveWeather {
    private String province;
    private String city;
    private String adcode;
    private String weather;
    private String temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime;
}
