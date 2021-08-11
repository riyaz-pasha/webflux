package com.example.webflux.asyncPOC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.webflux.asyncPOC")
public class WebfluxApplicationAsync {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplicationAsync.class, args);
    }

}
