package com.tairovich.receipt.items;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tairovich.receipt.taxFreeItems.TaxFreeItems;
import com.tairovich.receipt.utils.ProjectUtils;

public class Chocolate implements TaxFreeItems {

	private String name;
	private double price;
	private boolean imported;
	private double individualDutyTax;
	ProjectUtils projectUtils = new ProjectUtils();
	
	public Chocolate() {

	}

	public double getTotalPrice() {

		return this.price;
	}

	public void setPrice() {
		Random r = new Random();
		double randomPrice = r.nextInt(20) + 1;

		if (isImported()) {
			individualDutyTax = projectUtils.formatDouble(randomPrice * dutyTax);
			randomPrice = randomPrice + individualDutyTax;
		}
		this.price = projectUtils.formatDouble(randomPrice);
	}

	@Override
	public double getTotalDutySalesTax() {
		return individualDutyTax;
	}
	public void setName() {
		this.name = ChocolateNames.generateChocolateName();
	}

	public double getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}

	public boolean isImported() {
		Random r = new Random();
		this.imported = r.nextBoolean();
		return imported;
	}


	@Override
	public String toString() {
		return "Chocolate [name=" + name +  ", imported=" + imported +", price=" + price + ", individualDutyTax="
				+ individualDutyTax + "]";
	}


	public static class ChocolateNames {

		private static List<String> chocolates = Arrays.asList("Milky Way", "Red Bliss", "Almond Joy", "Paradise",
				"Caramel Joy","Dark Choco");

		public static String generateChocolateName() {
			int ran = new Random().nextInt(chocolates.size()) + 0;
			return chocolates.get(ran);
		}

	}


	@Override
	public Map<String, Double> initiateItems() {
		// TODO Auto-generated method stub
		return null;
	}





	
}
