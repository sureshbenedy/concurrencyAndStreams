package com.sample.listings.predicates;

import java.util.function.Predicate;

// Simple Predicate
public class SimplePredicate {
	public static void main(String[] args)
    {
        // Creating predicate
        Predicate<Integer> lesserthan = i -> (i < 18); 
  
        // Calling Predicate method
        System.out.println(lesserthan.test(10)); 
    }
}
