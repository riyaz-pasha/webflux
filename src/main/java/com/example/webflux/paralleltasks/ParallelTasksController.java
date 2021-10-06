package com.example.webflux.paralleltasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ParallelTasksController {

    @Autowired
    ParallelTasksService parallelTasksService;

    @GetMapping("/getSomething")
    public Flux<List<String>> getSomething() {
        return parallelTasksService.getTables();
    }

    @GetMapping("/getSomething2")
    public Flux<List<String>> getSomething2() {
        return parallelTasksService.getTables2();
    }
}
