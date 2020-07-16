package com.tairovich.receipt.basket;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tairovich.receipt.cart.ICart;
import com.tairovich.receipt.items.IAllSalesItems;

public class ItemBasket implements ICart{

	
	private static List<IAllSalesItems> availableItems = ItemLoader.getAllAvailableItems();
	private static List<IAllSalesItems> addedItems = new LinkedList<>();
	

	public void showAllItems() {
		System.out.println("\n#### Available items in the Shop #####\n");
		for (int i = 0; i < availableItems.size(); i++) {
			System.out
					.println(i + " - " + availableItems.get(i).getName() + " at $" + availableItems.get(i).getPrice());
		}
		System.out.println("\n");
	}

	public List<IAllSalesItems> checkout() {
		if (addedItems == null || addedItems.size() == 0) {
			System.out.println("Your Cart is empty. Nothing to checkout");
			return addedItems;
		}
		double salesTax = 0.0, totalPriceOnAllItems = 0.0;
		for (int i = 0; i < addedItems.size(); i++) {
			System.out.println(addedItems.get(i).getClass().getSimpleName() + " :: "+ addedItems.get(i).getName() + " at " + " $" + addedItems.get(i).getAllCalculatedCost()
					+ " Imported Item: " + addedItems.get(i).isImported());
			salesTax += addedItems.get(i).getDutyTaxedCost();
			totalPriceOnAllItems += addedItems.get(i).getAllCalculatedCost();
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println(
				"Your total Transaction is:  Sales Tax: $" + salesTax + " Total Cost: $" + totalPriceOnAllItems);
		return addedItems;
	}

	public IAllSalesItems findItem(int index) {
		if (availableItems != null && availableItems.size() > index) {
			return availableItems.get(index);
		}
		return null;
	}

	public boolean addToCart(int index) {
		IAllSalesItems foundItem = findItem(index);

		if (foundItem != null) {
			addedItems.add(foundItem);
			System.out.println("\nAdded " + foundItem.getClass().getSimpleName() + " '"+ foundItem.getName() + "' "+ " to basket. You have " + addedItems.size() + " item(s):");
			showCurrentCart(addedItems);
			return true;
		} else {
			System.out.println("Book at index " + index + " does not exist");
			showCurrentCart(addedItems);
			return false;
		}

	}

	public void showCurrentCart(List<IAllSalesItems> items) {
		System.out.println("\nYou current Cart");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getClass().getSimpleName() + " " + items.get(i).getName());
		}
	}

	public boolean removeFromCart(int index) {
		if (addedItems != null && addedItems.size() > index) {
			IAllSalesItems item = addedItems.get(index);
			addedItems.remove(index);
			System.out.println(
					"Removed: " + item.getName() + " from the Cart. You have " + addedItems.size() + " item(s):");
			showCurrentCart(addedItems);
			return true;
		}
		System.out.println("Cannot find item with index " + index);
		showCurrentCart(addedItems);
		return false;
	}

	public void reloadCart() {
		addedItems = new LinkedList<>();
	}

	public static List<IAllSalesItems> getAddedItems() {
		return addedItems;
	}

	public void start() {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to Omonjon's Shop");

		boolean flag = true;
		while (flag) {
			System.out.println("0 - Start Shopping    |     1 - Quit");
			int option = s.nextInt();

			if (option == 0) {
				shopItems();
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

	public void shopItems() {

		Scanner s = new Scanner(System.in);
		boolean flag = true;

		while (flag) {

			showAllItems();

			System.out.println("Options:  Add   |   Remove   |   Checkout   |   Cancel");
			String action = s.next();
			if (action.equalsIgnoreCase("add")) {
				System.out.println("Enter index to add to cart");
				
				boolean checkForNumber = true;
				while(checkForNumber) {
					try {
						String inp = s.next();
						int add = Integer.parseInt(inp);
						checkForNumber = false;
						boolean itemExists = addToCart(add);
						if (itemExists)
							continue;
					}catch(NumberFormatException e) {
						System.out.println("Please enter an integer");
					}
				}

			} else if (action.equalsIgnoreCase("remove")) {
				
				if(getAddedItems().size() == 0) {
					System.out.println("Nothing to delete. Cart is empty");
					continue;
				}
				
				System.out.println("Enter index to remove from cart");
				
				boolean checkForNumber = true;
				while(checkForNumber) {
					try {
						String inp = s.next();
						int remove = Integer.parseInt(inp);
						checkForNumber = false;
						boolean itemExists = removeFromCart(remove);
						if(itemExists) 
							continue;
					}catch(NumberFormatException e) {
						System.out.println("Please enter an integer");
					}
				}
	
			} else if (action.equalsIgnoreCase("checkout")) {
				System.out.println("----- This is your cart -------");
				List<IAllSalesItems> checkoutItems = checkout();
				if (checkoutItems == null || checkoutItems.size() == 0) {
					continue;
				} else {
					System.out.println(
							"To complete transaction enter 'Complete'. Enter 'Cancel' to cancel your transcation");
					String finalAction = s.next();

					while (true) {
						if (finalAction.equalsIgnoreCase("complete")) {
							System.out.println("Payment complete. Thank you for shopping with us");
							reloadCart();
							flag = false;
							break;
						} else if (finalAction.equalsIgnoreCase("cancel")) {
							System.out.println("Transaction cancelled successfully.");
							reloadCart();
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
						reloadCart();
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
