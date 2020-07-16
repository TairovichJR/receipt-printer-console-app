package com.tairovich.receipt.basket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tairovich.receipt.items.Book;
import com.tairovich.receipt.utils.ProjectUtils;

public class BookLoader {

	private static ProjectUtils projectUtils = new ProjectUtils();
	private static List<String> bookNames = Arrays.asList("Titanic", "Shawshank Redemption", "Rich Dad Poor Dad",
			"Monarch Who Sold His Ferrari", "Think And Grow Rich", "10X Rule", "Intro to CS");
	private static List<Book> books = new ArrayList<>();
	
	
	
	
	
	public static List<Book> loadedBooks(){
		
		for(int i = 0; i < bookNames.size(); i++) {
			Book book = new Book();
			book.setName(bookNames.get(i));
			book.setPrice(projectUtils.generateRandomDoublePrice());
			book.setImported(projectUtils.generateRandomBoolean());
			books.add(book);
		}
		
		return books;
	}

}
