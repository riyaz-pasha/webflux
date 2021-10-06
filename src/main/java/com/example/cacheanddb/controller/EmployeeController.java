package com.example.cacheanddb.controller;

import com.example.cacheanddb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees/save")
    Flux<List<Boolean>> saveEmployees() {
        return employeeService.saveInRedisCache();
    }

}
