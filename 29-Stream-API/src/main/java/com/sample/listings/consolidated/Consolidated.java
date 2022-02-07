package com.sample.listings.consolidated;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Consolidated {

	List<Person> list = Arrays.asList(
	        new Person("John Blue", 28, "India", "Developer", 100.00),
	        new Person("Anna Brown", 53, "US", "Manager", 300.00),
	        new Person("Paul Black", 47, "UK", "Quality Analyst", 200.00)
	);
	
	List<Seller> sellerList = Arrays.asList(
	        new Seller("Oracle", 9),
	        new Seller("IBM", 9),
	        new Seller("Dell", 9),
	        new Seller("Canon", 9),
	        new Seller("HP", 9)
	        );
	List<Transaction> txns = Arrays.asList(
				new Transaction(100.00, sellerList.get(0)),
				new Transaction(90.00, sellerList.get(0)),
				new Transaction(10.00, sellerList.get(0)),
				new Transaction(70.00, sellerList.get(0)),
				
				new Transaction(100.00, sellerList.get(1)),
				new Transaction(80.00, sellerList.get(1)),
				new Transaction(30.00, sellerList.get(1)),
				
				new Transaction(100.00, sellerList.get(2)),
				new Transaction(100.00, sellerList.get(2)),
				
				new Transaction(60.00, sellerList.get(3)),
				new Transaction(10.00, sellerList.get(3)),
				
				new Transaction(80.00, sellerList.get(4)),
				new Transaction(50.00, sellerList.get(4))
			);
	List<String> items =
            Arrays.asList("apple", "apple", "banana",
                    "apple", "orange", "banana", "papaya");
	
	public static void main(String args[]) {
		Consolidated consolidated = new Consolidated();
		consolidated.getStatisticsByReduction();
		consolidated.getStatisticsByMutableReduction();
		consolidated.getCount();
		consolidated.getMaxGroupBy();
	}

	private void getStatisticsByReduction() {
		// Individual calls
		int min = list.stream()
		        .mapToInt(Person::getAge)
		        .min()
		        .orElseThrow(NoSuchElementException::new);
		System.out.println(String.format("Minimum Age : %d", min));
		
		int max = list.stream()
		        .mapToInt(Person::getAge)
		        .max()
		        .orElseThrow(NoSuchElementException::new);
		System.out.println(String.format("Maximum Age : %d", max));

		double avg = list.stream()
		        .mapToInt(Person::getAge)
		        .average()
		        .orElseThrow(NoSuchElementException::new);
		System.out.println(String.format("Average Age : %.2f\n", avg));
		
		// Single call to get all statistics using single function
		// Age statistics
		IntSummaryStatistics ageStatistics = list.stream()
		        .mapToInt(Person::getAge)
		        .summaryStatistics();
		System.out.println(String.format("Minimum Age : %d\nMaximum age : %d\nAverage age group : %.2f\n\n", ageStatistics.getMin(), ageStatistics.getMax(), ageStatistics.getAverage()));


		// Salary statistics
		DoubleSummaryStatistics salaryStatistics = list.stream()
		        .mapToDouble(Person::getSalary)
		        .summaryStatistics();
		System.out.println(String.format("Minimum Salary : %.2f\nMaximum Salary : %.2f\nAverage Salary group : %.2f\n\n", salaryStatistics.getMin(), salaryStatistics.getMax(), salaryStatistics.getAverage()));

	}
	
	private void getStatisticsByMutableReduction() {
		Map<Seller, List<Transaction>> bigTxnsBySeller = txns
				.stream()
				.filter(t -> t.getAmount() > 50)
				.collect(Collectors.groupingBy(Transaction::getSeller));
		
		bigTxnsBySeller.keySet().stream().forEach(key -> {
			System.out.println(String.format("Seller Name : %s", key.getName()));
			bigTxnsBySeller.get(key).forEach(txn -> {
				System.out.print(String.format("Amount : %.2f\n", txn.getAmount()));
			});
			System.out.println();
		});
	}
	
	private void getCount() {
		Map<String, Long> result = items.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result);
		
		Map<Transaction, Long> resultTxns = txns.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		resultTxns.keySet().stream().forEach(key -> {
			System.out.println(String.format("Seller Name : %s", key.seller.getName()));});
		System.out.println(resultTxns);
	}
	
	private void getMaxGroupBy() {
		Map<Seller, Optional<Transaction>> maxTxnsBySeller = txns.stream().collect(Collectors.groupingBy(Transaction::getSeller, Collectors.maxBy(Comparator.comparing(Transaction::getAmount))));
		Map<Seller, Optional<Transaction>> minTxnsBySeller = txns.stream().collect(Collectors.groupingBy(Transaction::getSeller, Collectors.minBy(Comparator.comparing(Transaction::getAmount))));
		System.out.println("\n\nMax Sale Amount For Each Seller");
		maxTxnsBySeller.keySet().stream().forEach(key -> {
			System.out.println(String.format("Seller Name : %s : %.2f", key.getName(), maxTxnsBySeller.get(key).get().getAmount()));});	

		// TODO : Check
//		String salaryStatistics = list.stream()
//		        .mapToDouble(Person::getSalary)
//		        .summaryStatistics();
		
		System.out.println("\n\nMin Sale Amount For Each Seller");
		minTxnsBySeller.keySet().stream().forEach(key -> {
			System.out.println(String.format("Seller Name : %s : %.2f", key.getName(), minTxnsBySeller.get(key).get().getAmount()));});
		//Map<Seller, Double> sumTxnsBySeller = txns.stream().collect(Transaction::getSeller, Collectors.summingDouble(Transaction::getAmount));
	}

}
