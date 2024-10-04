package com.github.lzhuii.ricebot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import lombok.SneakyThrows;

/**
 * Json 工具类
 *
 * @author hui
 * @since 2024-09-30
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 下划线转驼峰
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy.INSTANCE);
        // 序列化空值不报错
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化字段不存在不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 只序列化不为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 对象转 Json 字符串
     *
     * @param obj 对象
     * @return Json 字符串
     */
    @SneakyThrows
    public static String toJson(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Json 字符串转对象
     *
     * @param json  Json 字符串
     * @param clazz 类型
     * @param <T>   泛型
     * @return 泛型对象
     */
    @SneakyThrows
    public static <T> T toBean(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * 对象转对象
     *
     * @param obj   原对象
     * @param clazz 目标类型
     * @param <T>   泛型
     * @return 目标对象
     */
    public static <T> T toBean(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    /**
     * 对象转 JsonNode 对象
     *
     * @param obj 对象
     * @return JsonNode 对象
     */
    public static JsonNode toNode(Object obj) {
        return objectMapper.valueToTree(obj);
    }

}
