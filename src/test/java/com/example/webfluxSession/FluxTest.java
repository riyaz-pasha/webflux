package com.example.webfluxSession;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxTest {

    @Test
    void basicFluxSubscriptionExample() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .log();

        fruits.subscribe((res) -> System.out.println("Consumer : " + res));
    }

    @Test
    void basicFluxExampleWithException() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .concatWith(Flux.error(new RuntimeException("Run time exceptions occurred")))
                .log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err.getMessage()));
    }

    @Test
    void basicFluxExampleWhichCompletes() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .log();

        fruits.subscribe(
                System.out::println,
                (err) -> System.err.println(err),
                () -> System.out.println("Completed : Fetched all the elements")
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
    void fluxExampleWithStepVerifier_withErrorMessage() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .concatWith(Flux.error(new RuntimeException("Run time exceptions occurred")))
                .log();

        StepVerifier.create(fruits)
                .expectNext("Apples", "Bananas", "Cherries", "Dates")
                .expectErrorMessage("Run time exceptions occurred")
                .verify();
    }

    @Test
    void FluxToMono() {
        Mono<List<String>> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .collectList()
                .log();

        StepVerifier.create(fruits)
                .expectNext(List.of("Apples", "Bananas", "Cherries", "Dates"))
                .verifyComplete();
    }
}
