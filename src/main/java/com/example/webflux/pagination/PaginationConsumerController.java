package com.example.webflux.pagination;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
public class PaginationConsumerController {

    @GetMapping("/paginationConsumerService/tables")
    public Mono<List<List<String>>> getAllTables() {
        return getTable(1).expand(paginatedResponse -> {
            if (paginatedResponse.getCurrentNum() >= paginatedResponse.getTotalNum()) {
                return Mono.empty();
            }
            return getTable(paginatedResponse.getCurrentNum() + 1);
        }).map(paginatedResponse -> {
            log.info("{}", paginatedResponse);
            return paginatedResponse.getValue();
        }).collectList();
    }

    private Mono<PaginatedResponse> getTable(int num) {
        return WebClient.create("http://localhost:8080")
                .get().uri("/paginationProducerService/tables/" + num)
                .retrieve()
                .toEntity(PaginatedResponse.class)
                .map(HttpEntity::getBody);
    }
}


