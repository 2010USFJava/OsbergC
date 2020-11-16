package com.revature.bank;

import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * The MenuFormatter class contains the functionality for formatting menus.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class MenuFormatter {

	private MenuFormatter() {
		super();
	}

	/**
	 * The displayAccountMenu method displays the user ID, name, and their accounts
	 * with numeric menu inputs.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 */
	public static void displayAccountMenu(Role role, ArrayList<Account> accounts) {
		if (!accounts.isEmpty()) {
			System.out.println("User #" + role.getUserId() + " - " + role.getGivenName());
			System.out.println("\tAccount Number\tAccount Type\tBalance");
			for (Account account : accounts) {
				System.out.println((accounts.indexOf(account) + 1) + ".\t" + account.getAccountNumber() + "\t\t"
						+ account.getAccountType() + "\t" + account.getBalance().setScale(2, RoundingMode.HALF_EVEN));
			}
		}
	}

	/**
	 * The displayMainMenu method displays all of the services available to the
	 * current role with numeric menu inputs.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return Service[] Returns an array of services available to the role.
	 */
	public static Service[] displayMainMenu(Role role) {
		System.out.println("Please enter the number of the desired transaction.");
		Service[] servicesArray = role.getRoleServices().getServicesArray();
		for (int i = 0; i < role.getRoleServices().getServicesArray().length; i++) {
			System.out.println((i + 1) + ".\t" + servicesArray[i].getServiceName());
		}
		return servicesArray;
	}
}
