package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MarkableAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkableAssessmentApplication.class, args);

		SumRequester requester = new SumRequester();
		requester.start();
	}
}
