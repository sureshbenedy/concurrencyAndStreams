package com.sample.listings.consolidated;

public class Seller{
	String name;
	int rating;

	public Seller(String name, int rating) {
		this.name = name;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Seller [name=" + name + ", rating=" + rating + "]";
	}
	
}