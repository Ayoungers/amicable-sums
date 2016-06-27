package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Controller to handle amicable sum requests. Requests must have have a unique missionId and a seed number to serve as
// the ceiling for the sum.
@RestController
public class AmicableController {

    @Autowired
    AmicableService amicableService;

    private Set<Integer> ids = new HashSet();

    @RequestMapping(value="/messages",method=RequestMethod.POST,produces="application/json",consumes="application/json")
    public ResponseEntity messageHandler(@RequestBody AmicableRequestMapper request) {
        int seed = request.getSeed();
        int missionId = request.getMissionId();

        // Sanity check for valid inputs
        if(seed < 1000 || seed > 20000){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            // Add missionId to processed requests, and reject request if a duplicate request is received.
            if(!ids.add(missionId)){
                return new ResponseEntity(HttpStatus.CONFLICT);
            }

            Integer sum = amicableService.getAmicableSum(seed);
            return ResponseEntity.ok(Collections.singletonMap("answer", sum));
        }
    }

}
