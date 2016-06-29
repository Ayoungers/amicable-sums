package com.ayoungers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AmicableSumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmicableSumsApplication.class, args);
	}
}
