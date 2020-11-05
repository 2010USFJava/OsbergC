package com.revature.assignment;

import java.io.Serializable;

public class MenuItem implements Serializable {
	
	private static final long serialVersionUID = 2180714852552889066L;
	private String itemName;
	private double price;
		
	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(String itemName, double price) {
		super();
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuItem [itemName=" + itemName + ", price=" + price + "]";
	}
	
	
	
}
