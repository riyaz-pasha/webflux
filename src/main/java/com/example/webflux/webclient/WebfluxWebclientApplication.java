package com.example.webflux.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.webflux.webclient")
public class WebfluxWebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxWebclientApplication.class, args);
	}

}
