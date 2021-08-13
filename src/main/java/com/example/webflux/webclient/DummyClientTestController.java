package com.example.webflux.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
public class DummyClientTestController {

    @Autowired
    DummyClient client;

    @GetMapping("/myService/tables")
    public Mono<List> getTablesFromDummyService() {
        log.info("MyService is calling dummy client");
        return client.getTablesFromDummyService();
    }

    @GetMapping("/myService/tables/flux")
    public Flux<String> getTablesFromDummyServiceAsynchronous() {
        log.info("MyService is calling dummy client");
        return client.getTablesFromDummyServiceAsynchronous();
    }

    @GetMapping("/myService/exception")
    public Mono<List> exception() {
        log.info("MyService is calling dummy client");
        return client.getException();
    }
}
