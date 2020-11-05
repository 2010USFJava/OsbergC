package com.revature.assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	static FileManager fileManager = new FileManager();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
//		OrderLogger.logMessage("info", "Test message");
//		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
//		menuItems.add(new MenuItem("Ham", 10.00));
//		menuItems.add(new MenuItem("Ice Cream", 5.00));
//		menuItems.add(new MenuItem("Sandwich", 4.00));
//		fileManager.writeItemsToFile(menuItems, "menu.txt");
//		System.out.println(fileManager.readItemsFromFile("menu.txt"));
		System.out.println("Hello restauranteur, what would you like to add to your menu?");
		ArrayList<MenuItem> menu = fileManager.readItemsFromFile("menu.txt");
		System.out.println(menu);
		boolean cont = true;
		while (cont) {
			System.out.println("Add the name of the menu item.");
			String itemName = scanner.nextLine();
			System.out.println("Add the price of the item.");
			String itemPrice = scanner.nextLine();
			double dPrice = Double.parseDouble(itemPrice);
			menu.add(new MenuItem(itemName, dPrice));
			OrderLogger.logMessage("info", "Added a new menu item called " + itemName + " for $" + dPrice);
			System.out.println("Would you like to add another item? y/n");
			String choice = scanner.nextLine();
			switch (choice.toLowerCase()) {
			case "y":
				cont = true;
				break;
			case "n":
				cont = false;
				break;
			default:
				System.out.println("Not a choice");
				break;
			}
		}
		System.out.println(menu);
		fileManager.writeItemsToFile(menu, "menu.txt");
		System.out.println("Thanks, bye!");

	}

	public static double addItems(MenuItem... items) {
		double total = 0.0;
		for (MenuItem item : items) {
			total += item.getPrice();
		}
		return total;
	}
}
