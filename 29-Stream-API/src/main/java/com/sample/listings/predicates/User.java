package com.sample.listings.predicates;

public 	class User {
	String name, role;
	String gender;
	Integer age;
	Double salary;

	public User(String a, String b, Integer age, Double salary, String gender) {
		name = a;
		role = b;
		this.age = age;
		this.salary = salary;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public Integer getAge() {
		return age;
	}

	public Double getSalary() {
		return salary;
	}

	public String getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", role=" + role + ", age=" + age + ", salary=" + salary + "]";
	}
}