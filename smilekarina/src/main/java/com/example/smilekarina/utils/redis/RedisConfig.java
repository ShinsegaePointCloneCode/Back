//package com.example.smilekarina.utils.redis;
//
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@RequiredArgsConstructor
//public class RedisConfig {
//    private final Environment env;
//    private String redisHost = env.getProperty("spring.data.redis.host");
//    private Integer redisPort = env.getProperty("spring.data.redis.port", Integer.class);
//    private String redisPassword = env.getProperty("spring.data.redis.password");
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//
//        template.setConnectionFactory(factory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        return template;
//    }
//}
