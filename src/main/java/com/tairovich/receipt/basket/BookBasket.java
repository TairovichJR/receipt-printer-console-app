package com.tairovich.receipt.basket;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tairovich.receipt.items.Book;

public class BookBasket {

	private static List<Book> availableBooks = BookLoader.loadedBooks();
	private static List<Book> addedBooks = new LinkedList<>();

	public static void showBooks() {
		System.out.println("\n#### Available books in the Shop #####\n");
		for (int i = 0; i < availableBooks.size(); i++) {
			System.out
					.println(i + " - " + availableBooks.get(i).getName() + " at $" + availableBooks.get(i).getPrice());
		}
		System.out.println("\n");
	}

	private static List<Book> checkout() {
		if (addedBooks == null || addedBooks.size() == 0) {
			System.out.println("Your Cart is empty. Nothing to checkout");
			return addedBooks;
		}
		double salesTax = 0.0, totalPriceOnAllItems = 0.0;
		for (int i = 0; i < addedBooks.size(); i++) {
			System.out.println(addedBooks.get(i).getName() + " at " + " $" + addedBooks.get(i).getAllCalculatedCost()
					+ " Imported Item: " + addedBooks.get(i).isImported());
			salesTax += addedBooks.get(i).getDutyTaxedCost();
			totalPriceOnAllItems += addedBooks.get(i).getAllCalculatedCost();
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println(
				"Your total Transaction is:  Sales Tax: $" + salesTax + " Total Cost: $" + totalPriceOnAllItems);
		return addedBooks;
	}

	public static Book findBook(int index) {
		if (availableBooks != null && availableBooks.size() > index) {
			return availableBooks.get(index);
		}
		return null;
	}

	public static boolean addToCart(int index) {
		Book book = findBook(index);

		if (book != null) {
			addedBooks.add(book);
			System.out.println("\nAdded: " + book.getName() + " to basket. You have " + addedBooks.size() + " items:");
			showCurrentCart(addedBooks);
			return true;
		} else {
			System.out.println("Book at index " + index + " does not exist");
			showCurrentCart(addedBooks);
			return false;
		}

	}

	public static void showCurrentCart(List<Book> books) {
		System.out.println("\nYou current Cart");
		for (int i = 0; i < books.size(); i++) {
			System.out.println(i + " " + books.get(i).getName());
		}
	}

	public static boolean removeFromCart(int index) {
		if (addedBooks != null && addedBooks.size() > index) {
			Book book = addedBooks.get(index);
			addedBooks.remove(index);
			System.out.println(
					"Removed: " + book.getName() + " from the Cart. You have " + addedBooks.size() + " item(s):");
			showCurrentCart(addedBooks);
			return true;
		}
		System.out.println("Cannot find book with index " + index);
		showCurrentCart(addedBooks);
		return false;
	}

	public static void reload() {
		addedBooks = new LinkedList<>();
	}

	public static void start() {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Omonjon's Book Shop");

		boolean flag = true;

		while (flag) {
			System.out.println("0 - Start Shopping    |     1 - Quit");
			int option = s.nextInt();

			if (option == 0) {
				shopBooks();
				continue;
			} else if (option == 1) {
				System.out.println("Thanks for shopping with us. Have a nice day");
				flag = false;
			} else {
				System.out.println("Please enter a valid option: ");
				continue;
			}
		}

		s.close();

	}

	public static void shopBooks() {

		Scanner s = new Scanner(System.in);
		boolean flag = true;

		while (flag) {

			showBooks();

			System.out.println("Options:  Add   |   Remove   |   Checkout   |   Cancel");
			String action = s.next();
			if (action.equalsIgnoreCase("add")) {
				System.out.println("Enter index to add to cart");
				int add = s.nextInt();
				boolean bookExists = addToCart(add);
				if (bookExists)
					continue;

			} else if (action.equalsIgnoreCase("remove")) {
				System.out.println("Enter index to remove from cart");
				int remove = s.nextInt();
				removeFromCart(remove);
				continue;
			} else if (action.equalsIgnoreCase("checkout")) {
				System.out.println("You cart is looking like this:");
				if (checkout() == null || checkout().size() == 0) {
					continue;
				} else {
					System.out.println(
							"To complete transaction enter 'Complete'. Enter 'Cancel' to cancel your transcation");
					String finalAction = s.next();

					while (true) {
						if (finalAction.equalsIgnoreCase("complete")) {
							System.out.println("Payment complete. Thank you for shopping with us");
							flag = false;
							break;
						} else if (finalAction.equalsIgnoreCase("cancel")) {
							System.out.println("Transaction cancelled successfully.");
							break;
						} else {
							System.out.println("Please enter valid option");
							continue;
						}
					}
				}

			} else if (action.equalsIgnoreCase("cancel")) {
				System.out.println("If you cancel you cart items wont be remembered");
				System.out.println("Option:  yes |  no");
				String answer = s.next();

				while (true) {
					if (answer.equalsIgnoreCase("yes")) {
						reload();
						flag = false;
						break;
					} else if (answer.equalsIgnoreCase("no")) {
						break;
					} else {
						System.out.println("Invalid option.Please Try again.");
						continue;
					}
				}
			}

			else {
				System.out.println("Invalid Command. Please try again");
				continue;
			}

		}

	}

}
