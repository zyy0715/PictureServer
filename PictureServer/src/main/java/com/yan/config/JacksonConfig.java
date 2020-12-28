package com.yan.config;

import com.yan.utils.JacksonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @Auther: farben
 * @Date: 2020/12/22 16:38
 * @Description: Jackson配置文件,处理对象转换json数据时,null使用""替代
 */

@Configuration
public class JacksonConfig {
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters((HttpMessageConverter<?>) new JacksonHttpMessageConverter());
    }
}