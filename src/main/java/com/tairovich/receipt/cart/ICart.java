package com.tairovich.receipt.cart;

import java.util.List;

import com.tairovich.receipt.items.Items;

public interface ICart {

	public List<Items> checkout();

	public void showAllItems();

	public boolean addToCart(int index);

	public boolean removeFromCart(int index);

	public void reloadCart();

	public void showCurrentCart(List<Items> items);

}
