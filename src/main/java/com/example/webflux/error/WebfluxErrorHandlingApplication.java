package com.example.webflux.error;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.webflux.error")
public class WebfluxErrorHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxErrorHandlingApplication.class, args);
    }

}
