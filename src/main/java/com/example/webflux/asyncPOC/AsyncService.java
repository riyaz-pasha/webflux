package com.example.webflux.asyncPOC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {
    private final Map<String, Mono<?>> responseMap;

    public AsyncService() {
        super();
        this.responseMap = new TreeMap<>();
    }

    public void executeTask(String endpointKey, Callable<Mono<?>> task) {
        log.info("Async service");
        CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> {
            Mono<?> returnValue = null;
            try {
                returnValue = task.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return returnValue;
        });
        future.whenCompleteAsync((returnValue, exception) -> {
            log.info("Received mono : " + returnValue);
            responseMap.put(endpointKey, (Mono<?>) returnValue);
            log.info("responseMap : " + responseMap);
        });
    }

    public Mono<?> getResponse(String endpointKey) {
        return responseMap.get(endpointKey);
    }

    public void removeResponse(String endpointKey, Mono<?> responseEntity) {
        responseMap.remove(endpointKey, responseEntity);
    }
}
