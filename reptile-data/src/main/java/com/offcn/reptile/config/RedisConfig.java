package com.offcn.reptile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 16:00
 **/
@Configuration
public class RedisConfig {
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate template=new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new StringRedisSerializer());
        return template;
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        JedisConnectionFactory factory=new JedisConnectionFactory(configuration);

        return factory;
    }

}
