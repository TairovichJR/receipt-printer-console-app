package com.tairovich.receipt.basket;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tairovich.receipt.cart.ICart;
import com.tairovich.receipt.items.IAllSalesItems;
import com.tairovich.receipt.utils.Constants;
import com.tairovich.receipt.utils.ProjectUtils;

public class ItemBasket implements ICart {

	private static List<IAllSalesItems> availableItems = ItemLoader.getAllAvailableItems();
	private static List<IAllSalesItems> addedItems = new LinkedList<>();
	ProjectUtils projectUtils = new ProjectUtils();

	public void showAllItems() {
		System.out.println(Constants.SHOPPING_MENU);
		System.out.printf("%-16s %-36s %-15s %s %n", "ITEM", " ITEM NAME", "PRICE", "ITEM INDEX");
		for (int i = 0; i < availableItems.size(); i++) {

			System.out.printf("%-10s %-6s %-35s %s %-15f %s %n", availableItems.get(i).getClass().getSimpleName(), "-",
					availableItems.get(i).getName(), "$", projectUtils.formatDouble(availableItems.get(i).getPrice()),
					i);

		}
		System.out.println("\n");
	}

	public List<IAllSalesItems> checkout() {
		if (addedItems == null || addedItems.size() == 0) {
			System.out.println(Constants.EMPTY_CART_CHECKOUT);
			return addedItems;
		}
		double salesTax = 0.0, totalPriceOnAllItems = 0.0;
		for (int i = 0; i < addedItems.size(); i++) {
			System.out.println(addedItems.get(i).getClass().getSimpleName() + " " + addedItems.get(i).getName()
					+ " AT $" + addedItems.get(i).getAllCalculatedCost() + "       |  IMPORTED ITEM: " + "["
					+ addedItems.get(i).isImported() + "]");
			salesTax += addedItems.get(i).getDutyTaxedCost();
			totalPriceOnAllItems += addedItems.get(i).getAllCalculatedCost();
		}
		System.out.println("\n----------------------------------------------------------------------------------");
		System.out.println("YOUR PAYMENT:     TAX: $" + projectUtils.formatDouble(salesTax) + "      |        SALES: $"
				+ projectUtils.formatDouble(totalPriceOnAllItems));
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
			System.out.println("\nADDED " + foundItem.getClass().getSimpleName() + " '" + foundItem.getName() + "' "
					+ " TO BASKET. YOU HAVE " + addedItems.size() + " ITEMS(s):");
			showCurrentCart(addedItems);
			return true;
		} else {
			System.out.println("ITEM AT INDEX " + index + " DOES NOT EXIST");
			showCurrentCart(addedItems);
			return false;
		}

	}

	public void showCurrentCart(List<IAllSalesItems> items) {
		System.out.println(Constants.CURRENT_CART);
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getClass().getSimpleName() + " " + items.get(i).getName());
		}
	}

	public boolean removeFromCart(int index) {
		if (addedItems != null && addedItems.size() > index) {
			IAllSalesItems item = addedItems.get(index);
			addedItems.remove(index);
			System.out
					.println("REMOVED: " + item.getName() + " FROM CART. YOU HAVE " + addedItems.size() + " ITEMS(s):");
			showCurrentCart(addedItems);
			return true;
		}
		System.out.println("CANNOT FIND ITEM AT INDEX " + index);
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
		System.out.println(Constants.WELCOME_MESSAGE);

		boolean flag = true;
		while (flag) {
			System.out.println(Constants.INITIAL_STEPS);
			int option = s.nextInt();

			if (option == 0) {
				shopItems();
				continue;
			} else if (option == 1) {
				System.out.println(Constants.GOODBYE_MESSAGE);
				flag = false;
			} else {
				System.out.println(Constants.INVALID_OPTION);
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

			System.out.println("OPTIONS:  ADD   |   REMOVE   |   CHECKOUT   |   CANCEL");
			String action = s.next();
			if (action.equalsIgnoreCase("add")) {
				System.out.println(Constants.ADD_TO_CART_MESSAGE);

				boolean checkForNumber = true;
				while (checkForNumber) {
					try {
						String inp = s.next();
						int add = Integer.parseInt(inp);
						checkForNumber = false;
						boolean itemExists = addToCart(add);
						if (itemExists)
							continue;
					} catch (NumberFormatException e) {
						System.out.println(Constants.ONLY_INTEGERS);
					}
				}

			} else if (action.equalsIgnoreCase("remove")) {

				if (getAddedItems().size() == 0) {
					System.out.println(Constants.EMPTY_CART);
					continue;
				}

				System.out.println(Constants.REMOVE_MESSAGE);

				boolean checkForNumber = true;
				while (checkForNumber) {
					try {
						String inp = s.next();
						int remove = Integer.parseInt(inp);
						checkForNumber = false;
						boolean itemExists = removeFromCart(remove);
						if (itemExists)
							continue;
					} catch (NumberFormatException e) {
						System.out.println(Constants.ONLY_INTEGERS);
					}
				}

			} else if (action.equalsIgnoreCase("checkout")) {
				System.out.println("**************************** THIS YOUR CURRENT CART ****************************");
				List<IAllSalesItems> checkoutItems = checkout();
				if (checkoutItems == null || checkoutItems.size() == 0) {
					continue;
				} else {
					System.out.println(Constants.COMPLETE_OR_CANCEL);
					while (true) {
						String finalAction = s.next();
						if (finalAction.equalsIgnoreCase("complete")) {
							System.out.println(Constants.PAYMENT_COMPLETE);
							reloadCart();
							flag = false;
							break;
						} else if (finalAction.equalsIgnoreCase("cancel")) {

							System.out.println(Constants.CANCELLING_TRANSACTION);
							System.out.println("OPTION:  YES |  NO");
							String answer = s.next();

							while (true) {
								if (answer.equalsIgnoreCase("yes")) {
									reloadCart();
									flag = false;
									break;
								} else if (answer.equalsIgnoreCase("no")) {
									break;
								} else {
									System.out.println(Constants.INVALID_OPTION);
									continue;
								}
							}

							System.out.println(Constants.TRANSACTION_CANCELLED + "\n");
							reloadCart();
							break;
						} else {
							System.out.println(Constants.INVALID_OPTION);
							continue;
						}
					}
				}
			} else if (action.equalsIgnoreCase("cancel")) {
				System.out.println(Constants.CANCELLING_TRANSACTION);
				System.out.println("OPTION:  YES |  NO");
				String answer = s.next();

				while (true) {
					if (answer.equalsIgnoreCase("yes")) {
						reloadCart();
						flag = false;
						break;
					} else if (answer.equalsIgnoreCase("no")) {
						break;
					} else {
						System.out.println(Constants.INVALID_OPTION);
						continue;
					}
				}
			} else {
				System.out.println(Constants.INVALID_OPTION);
				continue;
			}
		}
	}

}
