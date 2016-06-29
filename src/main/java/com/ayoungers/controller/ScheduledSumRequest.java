package com.ayoungers.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

// Creates a timed task to send requests for the amicable sum every 3 seconds and prints the result to console
@Component
public class ScheduledSumRequest {
    // Seeds only need to be generated for numbers between 1000 and 20000
    private static final int MAX_SEED = 20000;
    private static final int MIN_SEED = 1000;

    private int idCount;
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private Random random;

    @PostConstruct
    public void initialize() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        idCount = 0;
        random = new Random();
    }

    @Scheduled(fixedRate = 3000)
    public void requestRandomSum() {
        Integer seed = random.nextInt(MAX_SEED - MIN_SEED + 1) + MIN_SEED;

        Map<String,Integer> request = new HashMap<>();
        request.put("missionId",idCount);
        request.put("seed",seed);

        HttpEntity<String> entity = new HttpEntity(request,headers);
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:8080/messages",entity,String.class);


        System.out.println("Mission ID: " + idCount);
        System.out.println("Seed: " + seed);
        System.out.println("Response: " + result.getStatusCode());
        System.out.println(result.getBody());
        System.out.println("--------------");
        idCount++;
    }
}
