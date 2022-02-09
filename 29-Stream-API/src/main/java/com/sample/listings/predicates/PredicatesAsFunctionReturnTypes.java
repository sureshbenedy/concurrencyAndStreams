package com.sample.listings.predicates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicatesAsFunctionReturnTypes {
	public static Predicate<User> isAdultMale() {
		return p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("Male");
	}

	public static Predicate<User> isAdultFemale() {
		return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("Female");
	}

	public static Predicate<User> isAgeMoreThan(Integer age) {
		return p -> p.getAge() > age;
	}

	public static List<User> filterUsers(List<User> Users, Predicate<User> predicate) {
		return Users.stream().filter(predicate).collect(Collectors.<User>toList());
	}
}
