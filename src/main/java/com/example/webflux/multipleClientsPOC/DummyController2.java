package com.example.webflux.multipleClientsPOC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class DummyController2 {

    @Autowired
    NumbersClient numbersClient;

    @Autowired
    WordsClient wordsClient;

    @GetMapping("/wordsForNumbers")
    public Flux<String> getWordsForNumbers() {
        return numbersClient.getFirstTenNumbers()
                .flatMapSequential(wordsClient::getWordForGivenNumber);
    }
}
