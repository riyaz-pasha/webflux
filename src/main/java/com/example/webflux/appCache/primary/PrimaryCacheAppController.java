package com.example.webflux.appCache.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PrimaryCacheAppController {

    @Autowired
    PrimaryCacheClient primaryCacheClient;

    @GetMapping("/word/{number}")
    Mono<String> getWordForNumber(@PathVariable int number) {
        return primaryCacheClient.getWordForNumber(number);
    }
}
