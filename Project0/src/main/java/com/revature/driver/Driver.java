package com.revature.driver;

import java.util.Scanner;

import com.revature.bank.Role;
import com.revature.bank.Service;
import com.revature.util.MenuFormatter;

public class Driver {
	private static Role role = new Role();
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to JavaBank Online");
		while(queryService()) {}
		System.out.println("Thank you for using JavaBank Online. Have a nice day!");
	}

	public static boolean queryService() {
		Service[] servicesArray = MenuFormatter.displayMenu(role);
		String line = scanner.nextLine();
		return useInput(line, servicesArray);
	}
	
	private static boolean useInput(String line, Service[] servicesArray) {
		boolean continueServices = true;
		int choice;
		try{
			choice = Integer.parseInt(line)-1;
		}
		catch(Exception e) {
			System.out.println("Error: Please enter a valid number.");
			return continueServices;
		}
		if(-1<choice && choice<servicesArray.length) {
			continueServices = servicesArray[choice].performService(role);
			return continueServices;
		}
		else {
			System.out.println("Error: Please enter a valid number.");
			return continueServices;
		}
	}
}