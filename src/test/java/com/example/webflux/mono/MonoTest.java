package com.example.webflux.mono;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    void monoTest() {
        Mono<String> name = Mono.just("Riyaz").log();

        StepVerifier.create(name)
                .expectNext("Riyaz")
                .verifyComplete();
    }

    @Test
    void monoTest_withException() {
        StepVerifier.create(Mono.error(new RuntimeException("Runtime exception occurred")).log())
                .expectErrorMessage("Runtime exception occurred")
                .verify();
    }
}
