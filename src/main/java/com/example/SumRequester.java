package com.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

// Creates a timed task to send requests for the amicable sum every 3 seconds and prints the result to console
public class SumRequester {
    // Seeds only need to be generated for numbers between 1000 and 20000
    private static final int MAX_SEED = 20000;
    private static final int MIN_SEED = 1000;

    private int idCount;

    public void start() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        idCount = 0;
        Random random = new Random();

        // Sends a request every 3 seconds
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
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
        },3000,3000);
    }
}
