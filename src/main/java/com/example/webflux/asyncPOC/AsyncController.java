package com.example.webflux.asyncPOC;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.Callable;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    protected ResponseEntity<?> runAsync(String endpoint, Callable<Mono<?>> task) {

        String endpointKey = String.format("%s_%s", endpoint, UUID.randomUUID());
        asyncService.executeTask(endpointKey, task);

        Mono<?> responseEntity = asyncService.getResponse(endpointKey);

        if (responseEntity != null) {
            log.info("Returning response from async controller without polling {}", endpointKey);
            try {
                return ResponseEntity.status(HttpStatus.OK).body(responseEntity);
            } finally {
                asyncService.removeResponse(endpointKey, responseEntity);
            }
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).body(httpHeaders);
    }
}
