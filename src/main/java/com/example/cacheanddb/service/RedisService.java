package com.example.cacheanddb.service;

import com.example.cacheanddb.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class RedisService {

    private final ReactiveValueOperations<String, Employee> valueOperations;

    public RedisService(ReactiveRedisTemplate<String, Employee> redisTemplate) {
        valueOperations = redisTemplate.opsForValue();
    }

    Mono<List<Boolean>> saveEmployees(Page<Employee> employees) {
        return Flux.fromIterable(employees.getContent())
                .delayElements(Duration.ofMillis(500))
                .doOnNext(employee -> log.info("Page {} -  employee id {}", employees.getNumber(), employee.getId()))
                .flatMap(employee -> valueOperations.set(employee.getId(), employee))
                .doOnNext(res -> log.info("save response status {}", res))
                .collectList();
    }
}
