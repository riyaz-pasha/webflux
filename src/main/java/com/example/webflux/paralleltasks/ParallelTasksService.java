package com.example.webflux.paralleltasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ParallelTasksService {

    public Flux<List<String>> getTables() {
        return getTablesFlux();
    }

    private Flux<List<String>> getTablesFlux() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(num -> log.info("Calculating table for : " + num))
                .flatMap(this::getTable);
    }

    private Mono<List<String>> getTable(int num) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(3))
                .map(multiplier -> num + " * " + multiplier + " = " + num * multiplier)
                .doOnNext(log::info)
                .collect(Collectors.toList());
    }

    public Flux<List<String>> getTables2() {
        return getTablesFlux2();
    }

    private Flux<List<String>> getTablesFlux2() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(num -> log.info("Calculating table for : " + num))
                .flatMap(this::getTable2);
    }

    private Mono<List<String>> getTable2(int num) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(3))
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .map(multiplier -> num + " * " + multiplier + " = " + num * multiplier)
                .doOnNext(log::info)
                .sequential()
                .collect(Collectors.toList());
    }
}
