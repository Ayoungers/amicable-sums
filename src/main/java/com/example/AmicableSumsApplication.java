package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmicableSumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmicableSumsApplication.class, args);

		SumRequester requester = new SumRequester();
		requester.start();
	}
}
