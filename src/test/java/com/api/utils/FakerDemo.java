package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {
	
	public static void main(String[] args) {
		//We will be using the faker library for our fake test data creation creation!!
		//We will be creating faker util that uses this faker libraray
		Faker faker= new Faker(new Locale("en-IND"));
		String firstName=faker.name().firstName();
		String lastName=faker.name().lastName();
		System.out.println(firstName);
		System.out.println(lastName);
		String streetAddress=faker.address().streetAddress();
		String streetName=faker.address().streetName();
		String city= faker.address().city();
		System.out.println(faker.number().digits(10));
		System.out.println(faker.numerify("704#######"));
		System.out.println(faker.internet().emailAddress());

		System.out.println(streetAddress);
		System.out.println(streetName);
		System.out.println(city);

		
	}

}
