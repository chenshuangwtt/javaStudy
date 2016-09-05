package com.cs.test.week4.optional;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * filter() method takes Predicate instance as an argument. The value in
 * Optional instance is filtered and if filtered value is not empty then value
 * is returned otherwise empty Optional instance is returned
 * 
 */
public class Optionalfilter {
	public static void main(String[] args) {
		Optional<PrimeMinister> pm = Optional.of(new PrimeMinister("Narendra Modi"));
		// Using Optional.filter
		Predicate<PrimeMinister> pmPredicate = p -> p.getName().length() > 15;
		System.out.println(pm.filter(pmPredicate));
	}
}