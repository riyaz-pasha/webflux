package com.example.cacheanddb.service;

import com.example.cacheanddb.entity.Employee;
import com.example.cacheanddb.repository.ReactiveEmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    ReactiveEmployeeRepository reactiveEmployeeRepository;

    public Flux<Page<Employee>> saveInRedisCache() {
        var pageRequest = PageRequest.ofSize(250);
        return reactiveEmployeeRepository.findAll(pageRequest)
                .expand(employeesPageResponse -> {
                    if (employeesPageResponse.hasNext()) {
                        return reactiveEmployeeRepository.findAll(employeesPageResponse.nextPageable());
                    }
                    return Mono.empty();
                })
                .doOnNext(res -> log.info("Fetched response for page" + res.toString()));
    }
}
