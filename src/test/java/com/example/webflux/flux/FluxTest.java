package com.example.webflux.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    void basicFluxExampleWithException() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .concatWith(Flux.error(new RuntimeException("Run time exceptions occurred")))
                .log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }

    @Test
    void basicFluxExampleWhichCompletes() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .log();

        fruits.subscribe(
                System.out::println,
                (err) -> System.err.println(err),
                () -> System.out.println("Completed")
        );
    }

    @Test
    void fluxExampleWithStepVerifier() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .log();

        StepVerifier.create(fruits)
                .expectNext("Apples")
                .expectNext("Bananas")
                .expectNext("Cherries")
                .expectNext("Dates")
                .verifyComplete();
    }

    @Test
    void fluxExampleWithStepVerifier_withCount() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .log();

        StepVerifier.create(fruits)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void fluxExampleWithStepVerifier_withError() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .concatWith(Flux.error(new RuntimeException("Run time exceptions occurred")))
                .log();

        StepVerifier.create(fruits)
                .expectNext("Apples", "Bananas", "Cherries", "Dates")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void fluxExampleWithStepVerifier_withErrorMessage() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .concatWith(Flux.error(new RuntimeException("Run time exceptions occurred")))
                .log();

        StepVerifier.create(fruits)
                .expectNext("Apples", "Bananas", "Cherries", "Dates")
                .expectErrorMessage("Run time exceptions occurred")
                .verify();
    }
}
