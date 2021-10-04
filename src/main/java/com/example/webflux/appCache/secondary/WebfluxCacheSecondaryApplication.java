package com.example.webflux.appCache.secondary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@SpringBootApplication(scanBasePackages = "com.example.webflux.appCache.secondary")
public class WebfluxCacheSecondaryApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebfluxCacheSecondaryApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9002"));
        app.run(args);
    }
}
