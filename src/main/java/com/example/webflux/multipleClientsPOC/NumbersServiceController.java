package com.example.webflux.multipleClientsPOC;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class NumbersServiceController {

    @GetMapping("/numbers")
    public Flux<Integer> getNumbers() {
        return Flux.range(1, 10);
    }
}
