package com.example.cacheanddb.controller;

import com.example.cacheanddb.entity.Employee;
import com.example.cacheanddb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees/save")
    Mono<Page<Employee>> saveEmployees() {
        return employeeService.saveInRedisCache();
    }

}
