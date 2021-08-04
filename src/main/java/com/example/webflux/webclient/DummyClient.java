package com.example.webflux.webclient;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class DummyClient {

    public Mono<List> getTablesFromDummyService() {
        log.info("Dummy client is calling Dummy service via webclient");
        Mono<List> listMono = WebClient.create("http://localhost:8080")
                .get().uri("/dummyService/tables")
                .retrieve()
                .bodyToMono(List.class);
        log.info("Dummy client is called Dummy service via webclient successfully....");
        return listMono;
    }

    public Flux<String> getTablesFromDummyServiceAsynchronous() {
        log.info("Dummy client is calling Dummy service via webclient");
        Flux<String> listMono = WebClient.create("http://localhost:8080")
                .get().uri("/dummyService/tables/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
                .retrieve()
                .bodyToFlux(String.class);
        log.info("Dummy client is called Dummy service via webclient successfully....");
        return listMono;
    }
}

@Data
class ListOfStrings {
    List<String> table;
}