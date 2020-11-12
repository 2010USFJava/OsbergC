package com.revature.driver;

import java.util.Scanner;

import com.revature.bank.MenuFormatter;
import com.revature.bank.Role;
import com.revature.bank.Service;
import com.revature.exception.InvalidInputException;

public class Driver {
	private static Role role = new Role();
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to JavaBank Online");
		while(queryService()) {}
		System.out.println("Thank you for using JavaBank Online. Have a nice day!");
	}

	public static boolean queryService() {
		Service[] servicesArray = MenuFormatter.displayMainMenu(role);
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
			throw new InvalidInputException("Exception: Invalid input");
		}
		if(-1<choice && choice<servicesArray.length) {
			continueServices = servicesArray[choice].performService(role);
			return continueServices;
		}
		else {
			throw new InvalidInputException("Exception: Invalid input");
		}
	}
}