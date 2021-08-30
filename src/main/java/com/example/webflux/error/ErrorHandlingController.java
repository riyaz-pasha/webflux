package com.example.webflux.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ErrorHandlingController {

    @GetMapping("error")
    public Mono<String> example1() {
        return Mono.error(new NullPointerException("Hi error occurred"));
    }

    @GetMapping("error2")
    public Mono<String> example2() {
        throw new NullPointerException("Exception thrown");
    }

    @GetMapping("error3")
    public Mono<String> getException() {
        Mono<String> listMono = WebClient.create("http://localhost:8080")
                .get().uri("/error")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorMap(WebClientResponseException.class, throwable -> new NullPointerException("Exception thrown - " + throwable.getStatusCode()))
                .doOnSuccess(res -> log.info("Success res " + res))
                .doOnError(res -> log.info("Error res " + res));
        return listMono;
    }
}
