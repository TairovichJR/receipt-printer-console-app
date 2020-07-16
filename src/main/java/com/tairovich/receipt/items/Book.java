package com.tairovich.receipt.items;

import java.util.Arrays;
import java.util.List;

public class Book implements SalesTaxFreeItems {

	private String name;
	private double price;
	private boolean imported;

	public Book(String name, double price, boolean imported) {
		this.name = name;
		this.price = price;
		this.imported = imported;
	}
	
	public double getDutyTaxedCost() {
		double dutyCost = 0.0;
		if(imported) {
			dutyCost = dutyCost + (DUTY_Tax * this.price);
		}
		return dutyCost;
	}
	
	public double getAllCalculatedCost() {
		double finalPrice = 0;
		if(imported) {
			finalPrice = this.price + (DUTY_Tax * this.price);
			return finalPrice;
		}
		return (finalPrice += this.price);
	}
	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public boolean isImported() {
		return imported;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", imported=" + imported + "]";
	}	
	
	
	private static List<String> bookNames = Arrays.asList("Titanic", "Shawshank Redemption", "Rich Dad Poor Dad",
			"Monarch Who Sold His Ferrari", "Think And Grow Rich", "10X Rule", "Intro to CS");
	
}
