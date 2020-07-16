package com.tairovich.receipt.basket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tairovich.receipt.items.Book;
import com.tairovich.receipt.items.CD;
import com.tairovich.receipt.items.IAllSalesItems;
import com.tairovich.receipt.utils.ProjectUtils;

public class ItemLoader{

	private static ProjectUtils projectUtils = new ProjectUtils();
	private static List<String> bookNames = Arrays.asList("Titanic", "Shawshank Redemption", "Rich Dad Poor Dad",
			"Monarch Who Sold His Ferrari", "Think And Grow Rich", "10X Rule", "Intro to CS");
	private static List<String> cdNames = Arrays.asList("Linkin Park", "The Weeknd", "30 Seconds To Mars", "Benom",
			"Ummon", "Brian Adams", "Eminem", "Lady Gaga");

	private static List<IAllSalesItems> items = new ArrayList<>();
	
	
	public static List<IAllSalesItems> getAllAvailableItems(){
		loadCDs();
		loadBooks();
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


}
