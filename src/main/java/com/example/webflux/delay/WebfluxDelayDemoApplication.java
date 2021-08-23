package com.example.webflux.delay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.webflux.delay")
public class WebfluxDelayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxDelayDemoApplication.class, args);
    }

}
