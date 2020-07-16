package com.tairovich.receipt.cart;

import java.util.List;

import com.tairovich.receipt.items.IAllSalesItems;

public interface ICart {

	public List<IAllSalesItems> checkout();

	public void showAllItems();

	public boolean addToCart(int index);

	public boolean removeFromCart(int index);

	public void reloadCart();

	public IAllSalesItems findItem(int index);
	
	public void showCurrentCart(List<IAllSalesItems> items);

	public void shopItems();
}
