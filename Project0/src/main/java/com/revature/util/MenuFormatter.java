package com.revature.util;

import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.bank.Role;
import com.revature.bank.Service;

public class MenuFormatter {

	private MenuFormatter() {
		super();
	}

	public static void displayAccountMenu(ArrayList<Account> accounts) {
		System.out.println("\tAccount Number\tAccount Type");
		for (Account account : accounts) {
			System.out.println((accounts.indexOf(account) + 1) + ".\t" + account.getAccountNumber() + "\t\t"
					+ account.getAccountType());
		}
	}
	
	public static Service[] displayMenu(Role role) {
		System.out.println("Please enter the number of the desired transaction.");
		Service[] servicesArray = role.getRoleServices().getServicesArray();
		for(int i=0; i<role.getRoleServices().getServicesArray().length; i++) {
			System.out.println((i+1) + ". " + servicesArray[i].getServiceName());
		}
		return servicesArray;
	}
}
