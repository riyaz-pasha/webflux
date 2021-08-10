package com.example.webflux.asyncPOC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DummyService {

    public Mono<String> getItemsWithDelay() {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mono<String> monoResponse = Mono.just("abc");
        log.info("Returning mono response from service : " + monoResponse);
        return monoResponse;
    }
}
