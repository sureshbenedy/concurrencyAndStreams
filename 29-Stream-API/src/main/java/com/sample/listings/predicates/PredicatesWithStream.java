package com.sample.listings.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// PredicatesWithStream and function arguments
public class PredicatesWithStream {
	public static void main(String args[]) {
		List<User> users = new ArrayList<>();
		users.add(new User("John", "admin", 40, 100.00, "Male"));
		users.add(new User("Jacob", "admin", 40, 60.00, "Male"));
		users.add(new User("James", "admin", 25, 90.00, "Male"));
		users.add(new User("Prerna", "admin", 25, 100.00, "Female"));

		users.add(new User("Peter", "member", 22, 70.00, "Male"));
		users.add(new User("Pragyan", "member", 25, 80.00, "Female"));		
		users.add(new User("Praneetha", "member", 30, 90.00, "Female"));
		users.add(new User("Prakash", "member", 20, 30.00, "Male"));
		
		
		List<User> salaryGreaterThanEighty = process(users, (User u) -> u.getSalary()> 80);
		List<User> admins = process(users, (User u) -> u.getRole().equals("member"));
		List<User> members = process(users, (User u) -> u.getRole().equals("member"));
		System.out.println("\nAdmins : ");
		admins.forEach(System.out::println);
		System.out.println("\nmembers : ");
		members.forEach(System.out::println);
		System.out.println("\nUsers having salary greater than 80 : ");
		salaryGreaterThanEighty.forEach(System.out::println);
		
		// predicate chaining
		Predicate<User> predicate1 = (User u) -> u.getRole().equals("admin");
		Predicate<User> predicate2 = (User u) -> u.getSalary()> 90;
		
		List<User> adminsAndSalary = process(users, predicate2.and(predicate1));
		
		System.out.println("\nAdmin Users having salary greater than 90 : ");
		adminsAndSalary.forEach(System.out::println);
		
		// Predicates with return types of function from a common class
		List<User> femaleUsers = process(users, PredicatesAsFunctionReturnTypes.isAdultFemale());
		System.out.println("\nFemale Users :");
		femaleUsers.forEach(System.out::println);

		List<User> maleUsers = process(users, PredicatesAsFunctionReturnTypes.isAdultMale());
		System.out.println("\nMale Users :");
		maleUsers.forEach(System.out::println);

		List<User> maleUsersAndSalary = process(users, PredicatesAsFunctionReturnTypes.isAdultMale().and(predicate2));
		System.out.println("\nMale Users having salary greater than 90 : ");
		maleUsersAndSalary.forEach(System.out::println);

		List<User> femaleUsersAndSalary = process(users, PredicatesAsFunctionReturnTypes.isAdultFemale().and(predicate2));
		System.out.println("\nFemale Users having salary greater than 90 : ");
		femaleUsersAndSalary.forEach(System.out::println);
	}

	public static List<User> process(List<User> users, Predicate<User> predicate) {
		List<User> result = new ArrayList<User>();
		for (User user : users)
			if (predicate.test(user))
				result.add(user);
		return result;
	}
	
}
