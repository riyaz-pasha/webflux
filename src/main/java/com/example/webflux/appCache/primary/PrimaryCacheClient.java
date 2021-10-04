package com.example.webflux.appCache.primary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PrimaryCacheClient {

    @Cacheable(value = "numCache", key = "#number", cacheManager = "ehCache")
    public Mono<String> getWordForNumber(Integer number) {
        return getWordForNumberFromClient(number).cache();
    }

    private Mono<String> getWordForNumberFromClient(Integer number) {
        return WebClient.create("http://localhost:9002")
                .get().uri("/numberToWord/" + number)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(word -> log.info("Fetched word from client {} - {}", number, word));
    }
}
