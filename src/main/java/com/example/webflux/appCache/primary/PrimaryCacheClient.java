package com.example.webflux.appCache.primary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PrimaryCacheClient {

    @Autowired
    CacheManager cacheManager;
    private Map<Integer, List<Character>> listMap = new HashMap<>();

    Cache getCache() {
        return cacheManager.getCache("numCache");
    }

    public Mono<String> getWordForNumber(Integer number) {
        return CacheMono.lookup(key -> Mono.justOrEmpty(getCache().get(number, String.class)).map(Signal::next), number)
                .onCacheMissResume(() -> getWordForNumberFromClient(number))
                .andWriteWith((key, signal) ->
                        Mono.fromRunnable(() ->
                                Optional.ofNullable(signal.get())
                                        .ifPresent(value -> getCache().put(key, value))
                        )
                );
    }

    private Mono<String> getWordForNumberFromClient(Integer number) {
        return WebClient.create("http://localhost:9002")
                .get().uri("/numberToWord/" + number)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(word -> log.info("Fetched word from client {} - {}", number, word));
    }

    // We need to fix this.
    // Storing list of values for a key

    Cache getCache2() {
        return cacheManager.getCache("numCache");
    }

    public Mono<List<Character>> getWordCharListForNumber(Integer number) {
        return CacheMono.lookup(key -> Mono.justOrEmpty(listMap.get(number)).map(Signal::next), number)
                .onCacheMissResume(() -> getWordCharListForNumberFromClient(number))
                .andWriteWith((key, signal) ->
                        Mono.fromRunnable(() ->
                                Optional.ofNullable(signal.get())
                                        .ifPresent(value -> listMap.put(key, value))
                        )
                );
    }

    private Mono<List<Character>> getWordCharListForNumberFromClient(Integer number) {
        return WebClient.create("http://localhost:9002")
                .get().uri("/numberToWord/" + number)
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toList()))
                .doOnNext(word -> log.info("Fetched word from client {} - {}", number, word));
    }
}
