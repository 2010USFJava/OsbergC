package com.revature.driver;

import java.util.Scanner;

import com.revature.bank.Role;
import com.revature.bank.Service;
import com.revature.banklogger.BankLogger;

public class Driver {
	private static Role role = new Role();
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to JavaBank Online");
		while(queryService()) {}
		System.out.println("Thank you for using JavaBank Online. Have a nice day!");
	}

	public static boolean queryService() {
		Service[] servicesArray = displayMenu();
		String line = scanner.nextLine();
		BankLogger.logMessage("info", "User input was " + line);
		return useInput(line, servicesArray);
	}
	
	private static Service[] displayMenu() {
		System.out.println("Please enter the number of the desired transaction.");
		Service[] servicesArray = role.getRoleServices().getServicesArray();
		for(int i=0; i<role.getRoleServices().getServicesArray().length; i++) {
			System.out.println((i+1) + ". " + servicesArray[i].getServiceName());
		}
		return servicesArray;
	}
	
	private static boolean useInput(String line, Service[] servicesArray) {
		boolean continueServices;
		int choice;
		try{
			choice = Integer.parseInt(line)-1;
		}
		catch(Exception e) {
			System.out.println("Error: Please enter a valid number.");
			return true;
		}
		if(-1<choice && choice<servicesArray.length) {
			continueServices = servicesArray[choice].performService(role);
			return continueServices;
		}
		else {
			System.out.println("Error: Please enter a valid number.");
			return true;
		}
	}
}