package com.cs.test.week4.optional;

import java.util.Optional;

/**
 * Optional.isPresent() It returns true and false value. If value is present,
 * then returns true otherwise false.
 * 
 * @author Administrator
 *
 */
public class OptionalisPresent {
	public static void main(String[] args) {
		Optional<PrimeMinister> pm = Optional.of(new PrimeMinister("Narendra Modi"));
		// Using Optional.isPresent
		System.out.println(pm.isPresent());
	}
}