package com.sample.listings.consolidated;

public class Person{
	String name;
	Integer age;
	String country;
	String designation;
	Double salary;
		
	public Person(String name, Integer age, String country, String designation, Double salary){
		this.name = name;
		this.age= age;
		this.country = country;
		this.designation = designation;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}	
}