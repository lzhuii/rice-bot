package com.github.lzhuii.ricebot.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author hui
 * @since 2024-10-03
 */
@Getter
@Setter
public class MessageArkKv {
    private String key;
    private String value;
    private List<MessageArkObj> obj;
}
