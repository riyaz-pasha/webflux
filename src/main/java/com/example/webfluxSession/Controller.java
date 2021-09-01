package com.example.webfluxSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/fruits")
    public Flux<String> getFruits() {
        return Flux.just("Apples", "Bananas", "Cherries", "Dates").log();
    }

    @GetMapping(value = "/fruits-stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getFruitsStream() {
        return Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .delayElements(Duration.ofSeconds(2))
                .log();
    }

    @GetMapping("/fruits-mono")
    public Mono<List<String>> getFruitsMono() {
        return Mono.just(List.of("Apples", "Bananas", "Cherries", "Dates")).log();
    }
}
