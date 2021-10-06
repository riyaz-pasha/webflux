package com.example.webflux.paralleltasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.example.webflux.paralleltasks")
@EnableCaching
public class WebfluxParallelTasksApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxParallelTasksApplication.class, args);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(9001);
    }
}
