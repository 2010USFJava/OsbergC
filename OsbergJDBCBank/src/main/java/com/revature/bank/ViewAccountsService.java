package com.revature.bank;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.exception.NoAccountsException;
import com.revature.exception.UserDoesNotExistException;

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
		Integer iUserID = null;
		try {
			iUserID = obtainUserId(role);
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
			return true;
		}
		try {
			showAccounts(role, iUserID);
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
			return true;
		}
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
	private ArrayList<Account> showAccounts(Role role, Integer userId) {
		ArrayList<Account> userAccounts = new ArrayList<>();
		try {
			userAccounts = (ArrayList<Account>) role.getAdi().getUserAccounts(userId, "approved");
			if (userAccounts.isEmpty()) {
				throw new NoAccountsException("Exception: User has no accounts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MenuFormatter.displayAccountMenu(role, userAccounts);
		BankLogger.logMessage("info", "Viewed accounts for user number " + role.getUserId() + ".\n");
		return userAccounts;
	}
}
