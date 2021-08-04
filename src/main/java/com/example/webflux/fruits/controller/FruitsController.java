package com.example.webflux.fruits.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

@RestController
public class FruitsController {

    @GetMapping("/fruits")
    public Flux getFruits() {
        return Flux.just("Orange", "Apple", "Grapes", "Mango").log();
    }

    @GetMapping(value = "/fruits-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux getFruitsStream() {
        return Flux.just("Orange", "Apple", "Grapes", "Mango")
                .delayElements(Duration.ofSeconds(2))
                .log();
    }

    @GetMapping("/fruits-mono")
    public Mono getFruitsMono() {
        return Mono.just(Arrays.asList("Orange", "Apple", "Grapes", "Mango"))
                .log();
    }

}
