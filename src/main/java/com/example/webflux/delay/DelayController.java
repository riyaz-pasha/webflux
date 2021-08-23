package com.example.webflux.delay;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@RestController
public class DelayController {
    int timeDelay = 5;

    @RequestMapping(value = "/polling/{count}",
            method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET})
    public Mono<?> pollingEndpoint(@PathVariable int count) {
        return waitAndGetResponse();
    }

    private Mono<?> waitAndGetResponse() {
        Mono<?> response = null;
        for (int i = 0; i < timeDelay; i++) {
            if (response == null) sleep();
            else break;
            response = getResponse();
        }
        return response;
    }

    private Mono<?> getResponse() {
        return null;
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
    }
}
