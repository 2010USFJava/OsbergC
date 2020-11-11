package com.revature.bank;

import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;

/**
 * The ViewAccountsService class contains the functionality for viewing a user's
 * account information.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class ViewAccountsService extends Service {

	public ViewAccountsService() {
		super();
		serviceName = "View Accounts";
	}

	/**
	 * The performService method overrides the parent method in order to query and
	 * verify user input for further use.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
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

	/**
	 * The showAccounts method reads in all accounts, gets the user's accounts, and
	 * displays them.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return ArrayList<Account> Returns the user's accounts.
	 */
	private ArrayList<Account> showAccounts(Role role, Integer userID) {
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, userID,
				FileManager.ACCOUNTS_FILE);
		MenuFormatter.displayAccountMenu(role, userAccounts);
		BankLogger.logMessage("info", "Viewed accounts for user number " + role.getUserID() + ".\n");

		return userAccounts;
	}
}
