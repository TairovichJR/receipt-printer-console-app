package com.tairovich.receipt.items;

public interface Items {

	public final static double DUTY_Tax = 0.05;
	public double getDutyTaxedCost();
	public double getAllCalculatedCost();
	
}
