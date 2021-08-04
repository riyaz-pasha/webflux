package com.example.webflux.fruits.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest
class FruitsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getFruits_1() {
        Flux<String> fruitsFlux = webTestClient.get().uri("/fruits")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(fruitsFlux)
                .expectSubscription()
                .expectNext("Orange", "Apple", "Grapes", "Mango")
                .verifyComplete();
    }

    @Test
    void getFruits_2() {
        webTestClient.get().uri("/fruits")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(String.class)
                .hasSize(4);
    }

    @Test
    void getFruits_3() {
        EntityExchangeResult<List<String>> entityExchangeResult = webTestClient.get().uri("/fruits")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .returnResult();
        assertEquals(Arrays.asList("Orange", "Apple", "Grapes", "Mango"), entityExchangeResult.getResponseBody());
    }

    @Test
    void getFruitsStream_1() {
        Flux<String> fruitsFlux = webTestClient.get().uri("/fruits-stream")
//                .accept(MediaType.valueOf(MediaType.APPLICATION_STREAM_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Apple", "Grapes", "Mango")
                .verifyComplete();
    }

    @Test
    void getFruitsMono() {
        webTestClient.get().uri("/fruits-mono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .consumeWith((res) -> assertEquals(Arrays.asList("Orange", "Apple", "Grapes", "Mango"), res.getResponseBody()));
    }
}