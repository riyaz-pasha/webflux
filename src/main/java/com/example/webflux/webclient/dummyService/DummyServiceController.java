package com.example.webflux.webclient.dummyService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class DummyServiceController {

    private static void sleepThreadForOneMinute(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/dummyService/tables")
    public List<List<String>> getTables() {
        long startTime = System.currentTimeMillis();
        List<List<String>> tables = IntStream.range(1, 10)
                .peek(num -> log.info("Calculating table for : " + num))
                .peek(DummyServiceController::sleepThreadForOneMinute)
                .mapToObj(this::getTable)
                .collect(Collectors.toList());
        long endTime = System.currentTimeMillis();
        log.info("DummyServiceController ----- Total synchronous execution time " + (endTime - startTime));
        return tables;
    }

    private List<String> getTable(int num) {
        return IntStream.range(1, 10)
                .mapToObj(multiplier -> num + " * " + multiplier + " = " + num * multiplier)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/dummyService/tables/flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<String>> getTablesFlux() {
        long startTime = System.currentTimeMillis();
        Flux<List<String>> tablesFlux = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(num -> log.info("Calculating table for : " + num))
                .map(this::getTable);
        long endTime = System.currentTimeMillis();
        log.info("DummyServiceController ----- Total asynchronous execution time " + (endTime - startTime));
        return tablesFlux;
    }

    private Flux<String> getTableFlux(int num) {
        return Flux.range(1, 10)
                .map(multiplier -> num + " * " + multiplier + " = " + num * multiplier);
    }

    @GetMapping("/dummyService/exception")
    public ResponseEntity<Object> throwException() {
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        log.info("DummyServiceController ----- Response " + (responseEntity));
        return responseEntity;
    }
}
