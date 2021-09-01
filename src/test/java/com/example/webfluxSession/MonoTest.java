package com.example.webfluxSession;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    void monoSubscriptionExample() {
        Mono<String> stringMono = Mono.just("Hello World");

        stringMono.subscribe(System.out::println);
    }

    @Test
    void monoSubscriptionErrorExample() {
        Mono<String> stringMono = Mono.error(new RuntimeException("Custom Runtime exception"));

        stringMono.subscribe(System.out::println, (err) -> System.err.println(err.getMessage()));
    }

    @Test
    void monoTestExample() {
        Mono<String> stringMono = Mono.just("Hello World").log();

        StepVerifier.create(stringMono)
                .expectNext("Hello World")
                .verifyComplete();
    }

    @Test
    void emptyMonoExample() {
        Mono<String> stringMono = Mono.empty();

        StepVerifier.create(stringMono)
                .verifyComplete();
    }

    @Test
    void monoBlockExample() {
        String actualResponse = Mono.just("Hello World").block();

        Assertions.assertEquals("Hello World", actualResponse);
    }
}
