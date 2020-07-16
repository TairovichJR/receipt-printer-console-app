package com.tairovich.receipt.basket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tairovich.receipt.items.Book;
import com.tairovich.receipt.items.CD;
import com.tairovich.receipt.items.Chocolate;
import com.tairovich.receipt.items.IAllSalesItems;
import com.tairovich.receipt.items.Perfume;
import com.tairovich.receipt.items.Pill;
import com.tairovich.receipt.utils.ProjectUtils;

public class ItemLoader{

	private static ProjectUtils projectUtils = new ProjectUtils();
	private static List<String> bookNames = Arrays.asList("Titanic", "Shawshank Redemption", "Rich Dad Poor Dad",
			"Monarch Who Sold His Ferrari", "Think And Grow Rich", "10X Rule", "Intro to CS");
	private static List<String> cdNames = Arrays.asList("Linkin Park", "The Weeknd", "30 Seconds To Mars", "Benom",
			"Ummon", "Brian Adams", "Eminem", "Lady Gaga");
	private static List<String> perfumeNames = Arrays.asList("Shalimar", "Vetiver", "Lilly of the Valley", "Chanel",
			"Dior", "Armani", "Prada", "Bvlgari");
	private static List<String> pillNames = Arrays.asList("Acetaminophen", "Bacitracin", "Ramashka", "Bisiptol",
			"Tylenol", "Advil", "Paracetamol", "NightQuil");
	private static List<String> chocolateNames = Arrays.asList("Milky Way", "Red Bliss", "Almond Joy", "Paradise",
			"Caramel Joy","Dark Choco");
	
	
	private static List<IAllSalesItems> items = new ArrayList<>();

	public static List<IAllSalesItems> getAllAvailableItems(){
		loadCDs();
		loadBooks();
		loadPerfumes();
		loadPills();
		loadChocolates();
		return items;
	}
	
	private static List<IAllSalesItems> loadCDs() {

		for (int i = 0; i < cdNames.size(); i++) {
			CD book = new CD(cdNames.get(i), projectUtils.generateRandomDoublePrice(),
					projectUtils.generateRandomBoolean());
			items.add(book);
		}
		return items;
	}

	private static List<IAllSalesItems> loadBooks() {

		for (int i = 0; i < bookNames.size(); i++) {
			Book book = new Book(bookNames.get(i), projectUtils.generateRandomDoublePrice(),
					projectUtils.generateRandomBoolean());
			items.add(book);
		}
		return items;
	}

	public static List<IAllSalesItems> loadPerfumes(){
		for (int i = 0; i < perfumeNames.size(); i++) {
			Perfume perfume = new Perfume(perfumeNames.get(i), projectUtils.generateRandomDoublePrice(),
					projectUtils.generateRandomBoolean());
			items.add(perfume);
		}
		return items;
	}
	
	public static List<IAllSalesItems> loadPills(){
		for (int i = 0; i < pillNames.size(); i++) {
			Pill pill = new Pill(pillNames.get(i), projectUtils.generateRandomDoublePrice(),
					projectUtils.generateRandomBoolean());
			items.add(pill);
		}
		return items;
	}
	
	public static List<IAllSalesItems> loadChocolates(){
		for (int i = 0; i < chocolateNames.size(); i++) {
			Chocolate chocolate = new Chocolate(chocolateNames.get(i), projectUtils.generateRandomDoublePrice(),
					projectUtils.generateRandomBoolean());
			items.add(chocolate);
		}
		return items;
	}

}
