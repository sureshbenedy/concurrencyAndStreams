package com.sample.listings.consolidated;


public class Transaction {
	Double amount;
	Seller seller;

	public Transaction(Double amount, Seller seller) {
		this.amount = amount;
		this.seller = seller;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", seller=" + seller + "]";
	}


}
