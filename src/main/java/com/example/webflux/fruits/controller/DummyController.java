package com.example.webflux.fruits.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
public class DummyController {

    @PostMapping("/student")
    public Mono getStudent(@Valid @RequestBody Student student) {
        return Mono.just(Arrays.asList("Orange", "Apple", "Grapes", "Mango"))
                .log();
    }
}
