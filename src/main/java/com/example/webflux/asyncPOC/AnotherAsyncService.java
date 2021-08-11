package com.example.webflux.asyncPOC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

@EnableAsync
@Slf4j
@Service
public class AnotherAsyncService {

    @Async
    public void asyncMethod(Callable<Mono<?>> task) {
        log.info("Execute method asynchronously. in thread " + Thread.currentThread().getName());
        Mono<?> response = null;
        try {
            response = task.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("Finally ");
            response.doOnSuccess(result -> log.info("Response in finally" + result));
        }
    }
}
