package com.github.lzhuii.ricebot.plugin.amap.api;

import com.github.lzhuii.ricebot.plugin.amap.bean.GeoResponse;
import com.github.lzhuii.ricebot.plugin.amap.bean.WeatherResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * @author hui
 * @since 2024-09-30
 */
public interface AmapApi {

    @GetExchange("/v3/geocode/geo")
    GeoResponse getGeoCode(@RequestParam String key, @RequestParam String address);

    @GetExchange("/v3/weather/weatherInfo")
    WeatherResponse getWeather(@RequestParam String key, @RequestParam String city);
}
