package com.example.webflux.multipleClientsPOC;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class NumbersClient {

    public Flux<Integer> getFirstTenNumbers() {
        return WebClient.create("http://localhost:8082")
                .get().uri("/numbers")
                .retrieve()
                .bodyToFlux(int.class);
    }
}
