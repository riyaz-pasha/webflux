package com.example.webflux.pagination;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class PaginationProducerController {

    @GetMapping("/paginationProducerService/tables/{num}")
    public PaginatedResponse getTables(@PathVariable("num") int num) {
        List<String> table = getTable(num);
        PaginatedResponse paginatedResponse = new PaginatedResponse();
        paginatedResponse.setValue(table);
        paginatedResponse.setCurrentNum(num);
        paginatedResponse.setTotalNum(10);
        return paginatedResponse;
    }

    private List<String> getTable(int num) {
        return IntStream.range(1, 10)
                .mapToObj(multiplier -> num + " * " + multiplier + " = " + num * multiplier)
                .collect(Collectors.toList());
    }
}

@Data
class PaginatedResponse {
    List<String> value;
    int currentNum;
    int totalNum;
}