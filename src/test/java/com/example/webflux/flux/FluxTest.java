package com.example.webflux.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    void reduceFluxToMonoHasElements() {
        Mono<Boolean> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates").hasElements()
//                .reduce(new ArrayList<String>(), (list, nextItem) -> {
//                    list.add(nextItem);
//                    return list;
//                })
//                .map(list -> !list.isEmpty())
                .log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }

    @Test
    void reduceFluxToMono() {
        Mono<ArrayList<String>> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .reduce(new ArrayList<String>(), (list, nextItem) -> {
                    list.add(nextItem);
                    return list;
                }).log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }

    @Test
    void FluxToMono() {
        Mono<List<String>> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .collectList()
                .log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }

    @Test
    void reduceMonoToFlux() {
        Flux<String> fruits = Mono.just(List.of("Apples", "Bananas", "Cherries", "Dates")).flatMapMany(Flux::fromIterable)
//                .reduce(new ArrayList<String>(), (list, nextItem) -> {
//                    list.add(nextItem);
//                    return list;
//                })
//                .map(list -> !list.isEmpty())
                .log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }

    @Test
    void reduceFluxToMonoThenMonoToFlux() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .reduce(new ArrayList<String>(), (list, nextItem) -> {
                    list.add(nextItem);
                    return list;
                })
                .map(listOfFruits -> listOfFruits.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()))
                .flatMapIterable(listOfFruitsInReverseOrder -> listOfFruitsInReverseOrder)
                .log();


        StepVerifier.create(fruits)
                .expectNext("Dates", "Cherries", "Bananas", "Apples")
                .verifyComplete();
        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }

    @Test
    void fluxDoNextMutation() {
        Flux<String> fruits = Flux.just("Apples", "Bananas", "Cherries", "Dates")
                .doOnNext(fruit -> {
                    System.out.println("Prev value - " + fruit);
                    fruit.toUpperCase();
                })
                .log();

        fruits.subscribe(System.out::println, (err) -> System.err.println(err));
    }
}
