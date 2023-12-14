package com.caoyang.springboot3.utils;

import com.caoyang.springboot3.exception.JsonSerializationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import io.micrometer.common.util.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author caoyang
 * @create 2023-12-14 13:30
 */
public final class JsonUtils {

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final ObjectMapper mapper = ObjectMapperFactory.getInstance();

    /**
     * JsonPath中的“根成员对象”始终称为$，无论是对象还是数组
     */
    private static final String ROOT_PREFIX = "$";

    private static final Configuration configuration;

    static {
        configuration = Configuration.builder()
                .options(
                        // 如果路径不存在则返回null,而不要抛出PathNotFoundException
                        Option.DEFAULT_PATH_LEAF_TO_NULL,
                        // 抑制异常的抛出，当设置了Option.ALWAYS_RETURN_LIST时返回[],否则返回null
                        Option.SUPPRESS_EXCEPTIONS)
                .jsonProvider(new JacksonJsonProvider())
                .mappingProvider(new JacksonMappingProvider(mapper))
                .build();
    }

    public static ObjectMapper getMapper(){
        return mapper;
    }

    public static String bean2Json(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new JsonSerializationException("bean2Json序列化异常", e);
        }
    }

    public static <T> T json2Bean(String jsonData, Class<T> beanType) {
        try {
            return mapper.readValue(jsonData, beanType);
        } catch (Exception e) {
            throw new JsonSerializationException("json2Bean序列化异常", e);
        }
    }

    public static <T> T obj2Bean(Object obj, Class<T> beanType) {
        try {
            return mapper.readValue(bean2Json(obj), beanType);
        } catch (Exception e) {
            throw new JsonSerializationException("json2Bean序列化异常", e);
        }
    }

    /**
     * Object对象转换为List
     * @param data Object对象
     * @param clazz List中的对象类型
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> objToList(Object data, Class<T> clazz) {
        if (Objects.isNull(data)) {
            return new ArrayList<>();
        }
        String dataString;
        if (data instanceof String) {
            // 如果是String类型，直接转换
            dataString = (String) data;
        } else {
            dataString = bean2Json(data);
        }
        return json2List(dataString, clazz);
    }

    public static<T> List<T> jsonPath2List(String jsonData, Class<T> beanType, String jsonPath) {
        try {
            return JsonPath.using(configuration).parse(jsonData).read(jsonPath, new TypeRef<List<T>>() {
                @Override
                public Type getType() {
                    return mapper.getTypeFactory().constructParametricType(List.class, beanType);
                }
            });
        } catch (Exception e) {
            throw new JsonSerializationException("jsonPath2List序列化异常", e);
        }
    }

    public static<T> T jsonPath2Bean(String jsonData, Class<T> beanType, String jsonPath) {
        try {
            return JsonPath.using(configuration).parse(jsonData).read(jsonPath, beanType);
        } catch (Exception e) {
            throw new JsonSerializationException("jsonPath2Bean序列化异常", e);
        }
    }

    /**
     * 复杂 POJO 转换
     *
     * @param jsonData json数据
     * @param beanType bean类型
     */
    public static<T> T jsonPath2NestedBean(String jsonData, TypeRef<T> beanType) {
        // $ 代表整个json字符串
        try {
            return JsonPath.using(configuration).parse(jsonData).read(ROOT_PREFIX, beanType);
        } catch (Exception e) {
            throw new JsonSerializationException("jsonPath2NestedBean序列化异常", e);
        }
    }

    /**
     * json path 转复杂 POJO
     *
     * @param jsonData json数据
     * @param beanType bean类型
     */
    public static<T> T jsonPath2NestedBean(String jsonData, TypeRef<T> beanType,  String jsonPath) {
        try {
            return JsonPath.using(configuration).parse(jsonData).read(jsonPath, beanType);
        } catch (Exception e) {
            throw new JsonSerializationException("jsonPath2NestedBean序列化异常", e);
        }
    }

    public static String readValUsingJsonPath(String jsonData, String path) {
        if (StringUtils.isBlank(jsonData) || StringUtils.isBlank(path)) {
            return "";
        }
        try {
            Object val = JsonPath.using(configuration).parse(jsonData).read(path);
            return val == null ? "" : val.toString();
        } catch (Exception e) {
            throw new JsonSerializationException("readValUsingJsonPath序列化异常", e);
        }
    }

    public static <T> List<T> json2List(String jsonData, Class<T> beanType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return mapper.readValue(jsonData, javaType);
        } catch (Exception e) {
            throw new JsonSerializationException("json2List序列化异常", e);
        }
    }

    public static <K, V> Map<K, V> json2Map(String jsonData, Class<K> keyType, Class<V> valueType) {
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        try {
            return mapper.readValue(jsonData, javaType);
        } catch (Exception e) {
            throw new JsonSerializationException("json2Map序列化异常", e);
        }
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws Exception {
        try {
            return mapper.convertValue(map, clazz);
        } catch (Exception e) {
            throw new JsonSerializationException("mapToBean序列化异常", e);
        }
    }

    public static String mapToJson(Map<String, Object> map) throws Exception {
        try {
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            throw new JsonSerializationException("mapToJson序列化异常", e);
        }
    }

    public static <T> Map<String, Object> beanToMap(T clazz) {
        try {
            return mapper.convertValue(clazz, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new JsonSerializationException("beanToMap序列化异常", e);
        }
    }

    public static <T> Map<String, Object> beanToMapWithUnderLine(T clazz) {
        try {
            ObjectMapper instance = ObjectMapperFactory.getInstance(PropertyNamingStrategy.SNAKE_CASE);
            return instance.convertValue(clazz, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new JsonSerializationException("beanToMap序列化异常", e);
        }
    }

}
