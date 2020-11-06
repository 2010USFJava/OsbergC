package com.revature.bank;

import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.util.MenuFormatter;

public class ViewAccountsService extends Service {

	public ViewAccountsService() {
		super();
		serviceName = "View Accounts";
	}

	@Override
	public boolean performService(Role role) {

		Integer iUserID = role.getUserID();
		if (role.getRoleName() != roleName.CUSTOMER) {
			System.out.println("Enter the user ID.");
			String sUserID = scanner.nextLine();
			try {
				iUserID = Integer.parseInt(sUserID);
			} catch (Exception e) {
				System.out.println("Error: Invalid input");
				return true;
			}
		}
		showAccounts(role, iUserID);
		System.out.println("Press [Enter] to continue.");
		scanner.nextLine();
		return true;
	}

	private ArrayList<Account> showAccounts(Role role, Integer userID) {
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, userID, "accounts.txt");
		if (userAccounts.size() > 0) {
			System.out.println("User #" + role.getUserID() + " - " + role.getGivenName());
			MenuFormatter.displayAccountMenu(userAccounts);
		}
		BankLogger.logMessage("info", "Viewed accounts for user number " + role.getUserID() + ".\n");

		return userAccounts;
	}
}
