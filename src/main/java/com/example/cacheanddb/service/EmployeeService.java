package com.example.cacheanddb.service;

import com.example.cacheanddb.entity.Employee;
import com.example.cacheanddb.repository.ReactiveEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    ReactiveEmployeeRepository reactiveEmployeeRepository;

    public Mono<Page<Employee>> saveInRedisCache() {
        var pageRequest = PageRequest.ofSize(250);
        return reactiveEmployeeRepository.findAll(pageRequest);
    }
}
