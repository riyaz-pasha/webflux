package com.example.webflux.validations;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.constraints.AssertTrue;

@Service
public class UserService {

    @AssertTrue
    public Mono<User> registerUser(Mono<User> userDtoMono) {
        return userDtoMono
                .doOnNext(System.out::println);
    }
}
