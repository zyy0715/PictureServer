package com.yan.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;


/**
 * @Auther: farben
 * @Date: 2020/12/28 15:35
 * @Description: Redis辅助操作类
 */

@Component
public class RedisUtil {
    @Resource
    private StringRedisTemplate template;

    public void  setString(String key,Object value)  {

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setHashValueSerializer(new JdkSerializationRedisSerializer());

        ValueOperations<String, String> ops = template.opsForValue();

        String valueResult = "";
        if (value instanceof java.lang.String){
            valueResult = (String)value;
        }else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                valueResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ops.set(key, valueResult);
    }

    public Object getString(String key){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = template.opsForValue().get(key);
        Object resultStr = new Object();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            resultStr = objectMapper.treeToValue(jsonNode, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
