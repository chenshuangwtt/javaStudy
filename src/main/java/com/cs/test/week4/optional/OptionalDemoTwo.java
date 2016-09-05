package com.cs.test.week4.optional;

import java.util.Optional;

/**
 * Optional.flatMap() flatMap () method is same as map() method but the
 * difference is that the method argument which is a function must have Optional
 * mapper
 * 
 */
public class OptionalDemoTwo {
	public static void main(String[] args) {
		Optional<PrimeMinister> primeMinister = Optional.of(new PrimeMinister("Narendra Modi"));
		Optional<Country> country = Optional.of(new Country(primeMinister));
		Optional<Person> person = Optional.of(new Person(country));
		String pmName = person.flatMap(Person::getCountry).flatMap(Country::getPrimeMinister)
				.map(PrimeMinister::getName).orElse("None");
		System.out.println(pmName);
	}
}