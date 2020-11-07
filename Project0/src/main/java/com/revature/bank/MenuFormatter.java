package com.revature.bank;

import java.util.ArrayList;

public class MenuFormatter {

	private MenuFormatter() {
		super();
	}

	public static void displayAccountMenu(Role role, ArrayList<Account> accounts) {
		if (!accounts.isEmpty()) {
			System.out.println("User #" + role.getUserID() + " - " + role.getGivenName());
			System.out.println("\tAccount Number\tAccount Type\tBalance");
			for (Account account : accounts) {
				System.out.println((accounts.indexOf(account) + 1) + ".\t" + account.getAccountNumber() + "\t\t"
						+ account.getAccountType() + "\t" + account.getBalance());
			}
		}
	}

	public static Service[] displayMainMenu(Role role) {
		System.out.println("Please enter the number of the desired transaction.");
		Service[] servicesArray = role.getRoleServices().getServicesArray();
		for (int i = 0; i < role.getRoleServices().getServicesArray().length; i++) {
			System.out.println((i + 1) + ". " + servicesArray[i].getServiceName());
		}
		return servicesArray;
	}
}
