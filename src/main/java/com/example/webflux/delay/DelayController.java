package com.example.webflux.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class DelayController {
    int timeDelay = 5;

    @RequestMapping(value = "/polling/{count}",
            method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET})
    public Mono<String> pollingEndpoint(@PathVariable int count) {
        return waitAndGetResponseMono();
    }
//
//    private Mono<?> waitAndGetResponse() {
//        Mono<?> response = null;
//        for (int i = 0; i < timeDelay; i++) {
//            if (response == null) sleep();
//            else break;
//            response = getResponse();
//        }
//        return response;
//    }

    private Mono<String> waitAndGetResponseMono() {
        return getReponseWithDelay(1)
                .expand(res -> {
                    if (res == "dataNotPresent") return Mono.empty();
                    return getReponseWithDelay(Integer.parseInt(String.valueOf(res.charAt(res.length() - 1))) + 1);
                })
                .reduce((a, b) -> a + b);
    }

    private Mono<String> getReponseWithDelay(int num) {
        return getResponse(num).delaySubscription(Duration.ofSeconds(5));
    }

    private Mono<String> getResponse(int num) {
        log.error("Returning response {}", num);
        if (num > 5) return Mono.just("dataNotPresent");
        return Mono.just("Riyaz" + num)
                .doOnSubscribe((a) -> log.info("Subscribing {} {}", num, a))
                .doOnNext(item -> log.info("doOnNext {}", item));
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
    }
}
