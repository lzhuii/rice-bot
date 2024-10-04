package com.github.lzhuii.ricebot.plugin.amap.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
public class AmapResponse {
    private Integer status;
    private Integer count;
    private String info;
    private String infocode;
}
