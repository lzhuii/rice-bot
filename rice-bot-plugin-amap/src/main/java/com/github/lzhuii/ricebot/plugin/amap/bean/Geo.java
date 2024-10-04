package com.github.lzhuii.ricebot.plugin.amap.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
public class Geo {
    private String country;
    private String province;
    private String city;
    private String citycode;
    private String adcode;
    private String location;
    private String level;
}
