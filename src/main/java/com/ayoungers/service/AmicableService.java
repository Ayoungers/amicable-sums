package com.ayoungers.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service for calculating amicable sums.
@Service
public class AmicableService {
    // 220 is the smallest possible amicable number, so there is no need to check numbers smaller than that
    private static final int MINIMUM_AMICABLE_NUMBER = 220;

    // Takes a given seed number and calculates the sum of all amicable numbers less than it.
    public Integer getAmicableSum(int seed) {
        List<Integer> amicableNumbers = findAmicableNumbers(seed);

        int amicableSum = 0;
        for(int i = 0;i < amicableNumbers.size();i++){
            amicableSum += amicableNumbers.get(i);
        }

        return amicableSum;
    }

    // Find all divisors of a given number
    private List<Integer> findDivisors(int number) {
        List<Integer> divisors = new ArrayList();

        // It's only necessary to iterate to the square root of the given number, because all divisors of a number are
        // either less than the square root, or paired with a number that is less than the square root.
        double root = Math.sqrt(number);

        divisors.add(1);
        for(int i = 2; i < root;i++){
            if(number%i == 0){
                divisors.add(i);
                divisors.add(number/i);
            }
        }

        if(root == (int)root){
            divisors.add((int)root);
        }

        return divisors;
    }

    // Helper function to find all amicable numbers less than a given number.
    private List<Integer> findAmicableNumbers(int seed) {
        List<Integer> amicableNumbers = new ArrayList<>();

        // Iterate through numbers less than the given seed, starting at the lowest amicable number to save time
        for(int index = MINIMUM_AMICABLE_NUMBER;index <= seed;index++){

            // Check if current index was added as a pair to a previous amicable number, and skip if so.
            if(!amicableNumbers.contains(index)) {
                // Find the sum of the divisors of the current index
                List<Integer> divisors = findDivisors(index);
                int originalSum = 0;

                for(int i = 0;i < divisors.size();i++){
                    originalSum += divisors.get(i);
                }

                // Perfect numbers are not amicable numbers, so we exclude numbers with divisors that sum to itself.
                if(originalSum != index){
                    // Sum divisors of the previous sum, to check if they are an amicable pair
                    List<Integer> divisors2 = findDivisors(originalSum);
                    int pairSum = 0;

                    for(int i = 0;i < divisors2.size();i++){
                        pairSum += divisors2.get(i);
                    }

                    // If numbers are amicable, add to list of numbers to sum.
                    if(pairSum == index){
                        amicableNumbers.add(index);

                        // Add paired number as well if it is also less than the seed number
                        if(originalSum < seed){
                            amicableNumbers.add(originalSum);
                        }
                    }
                }
            }
        }

        return amicableNumbers;
    }
}
