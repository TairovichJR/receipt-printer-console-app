package com.tairovich.receipt.taxFreeItems;

import java.util.Map;

public interface TaxFreeItems {

	static final double dutyTax = 0.05;
	
	public abstract double getTotalPrice();
	
	public abstract double getTotalDutySalesTax();
	
	public Map<String,Double> initiateItems();
	
}
