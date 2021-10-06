package com.example.cacheanddb.repository;

import com.example.cacheanddb.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ReactiveEmployeeRepository {

    @Autowired
    EmployeeRepository employeeRepository;

    public Mono<Page<Employee>> findAll(Pageable pageable) {
        return Mono.defer(() -> Mono.just(employeeRepository.findAll(pageable)));
    }

}
