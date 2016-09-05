package com.cs.test.week4;

import java.util.Optional;

/**
 * 如果有值，则对其执行调用mapping函数得到返回值。
 * 如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
 * @author Administrator
 *
 */
public class OptionalflatMap {
	public static void main(String[] args) {
		Optional<PrimeMinister> primeMinister = Optional.of(new PrimeMinister("Narendra Modi", 65));
		Optional<Country> country = Optional.of(new Country(primeMinister));
		Optional<Person> person = Optional.of(new Person(country));
		String pmName = person.flatMap(Person::getCountry).flatMap(Country::getPrimeMinister)
				.map(PrimeMinister::getName).orElse("None");
		System.out.println(pmName);
	}
}
