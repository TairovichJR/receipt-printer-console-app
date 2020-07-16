package com.tairovich.receipt.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class ProjectUtils {

	public double formatDouble(double arg) {
		DecimalFormat format = new DecimalFormat("0.00");
		return Double.parseDouble(format.format(arg));
	}

	public double generateRandomDoublePrice() {
		Random r = new Random();
		double randomPrice = r.nextInt(50) + 1;
		randomPrice += formatDouble(randomPrice);
		return randomPrice;
	}

	public boolean generateRandomBoolean() {
		Random r = new Random();
		return r.nextBoolean();
	}

}
