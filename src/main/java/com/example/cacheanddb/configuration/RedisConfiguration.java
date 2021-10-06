package com.example.cacheanddb.configuration;

import com.example.cacheanddb.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.RedisSerializationContextBuilder;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public ReactiveRedisTemplate<String, Employee> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        Jackson2JsonRedisSerializer<Employee> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Employee.class);

        RedisSerializationContextBuilder<String, Employee> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);

        RedisSerializationContext<String, Employee> context = builder
                .value(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, context);
    }
}
