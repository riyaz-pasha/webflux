package com.example.webflux.multipleClientsPOC;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WordsClient {

    public Mono<String> getWordForGivenNumber(int number) {
        return WebClient.create("http://localhost:8082")
                .get().uri("/convertToWord/" + number)
                .retrieve()
                .bodyToMono(String.class);
    }
}
