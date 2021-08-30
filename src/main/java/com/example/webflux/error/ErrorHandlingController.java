package com.example.webflux.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ErrorHandlingController {

    @GetMapping("error")
    public Mono<String> example1() {
        return Mono.error(new NullPointerException("Hi error occurred"));
    }

    @GetMapping("error2")
    public Mono<String> example2() {
        throw new NullPointerException("Exception thrown");
    }
}
