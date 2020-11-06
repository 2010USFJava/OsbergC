package com.revature.bank;

import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;

public class ViewAccountsService extends Service {

	public ViewAccountsService() {
		super();
		serviceName = "View Accounts";
	}

	@Override
	public boolean performService(Role role) {
		Integer iUserID;
		if ((iUserID = obtainUserID(role)) < 0) {
			return true;
		}
		showAccounts(role, iUserID);
		System.out.println("Press [Enter] to continue.");
		scanner.nextLine();
		return true;
	}

	private ArrayList<Account> showAccounts(Role role, Integer userID) {
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, userID, FileManager.ACCOUNTSFILE);
		if (!userAccounts.isEmpty()) {
			MenuFormatter.displayAccountMenu(role, userAccounts);
		}
		BankLogger.logMessage("info", "Viewed accounts for user number " + role.getUserID() + ".\n");

		return userAccounts;
	}
}
