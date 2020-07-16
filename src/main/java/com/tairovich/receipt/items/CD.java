package com.tairovich.receipt.items;

public class CD implements SalesTaxedItems{
	private String name;
	private double price;
	private boolean imported;

	public CD() {
		
	}
	
	public double getDutyTaxedCost() {
		double dutyCost = 0.0;
		if(imported) {
			dutyCost = dutyCost + (DUTY_Tax * this.price);
		}
		return dutyCost + (this.price * SALES_TAX);
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
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isImported() {
		return imported;
	}
	public void setImported(boolean imported) {
		this.imported = imported;
	}
}
