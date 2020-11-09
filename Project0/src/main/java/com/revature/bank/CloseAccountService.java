package com.revature.bank;

import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;

/**
 * The CloseAccountService class contains the functionality for closing bank
 * accounts.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class CloseAccountService extends Service {

	public CloseAccountService() {
		super();
		serviceName = "Close an Account";
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
		Integer iAccountNumber = obtainTargetUserAccountNumber(role,
				"Please enter the number for the account you wish to close.", FileManager.ACCOUNTS_FILE);
		if (iAccountNumber < 0) {
			return true;
		}
		closeAccount(role, iAccountNumber);
		return true;
	}

	/**
	 * The closeAccount method reads in all accounts as an ArrayList, finds the
	 * index of the specified account, and removes it from the list.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 */
	private void closeAccount(Role role, Integer iAccountNumber) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		BankLogger.logReadItems(accounts);
		Integer selectedAccountIndex = obtainAccountIndex(role, iAccountNumber, FileManager.ACCOUNTS_FILE);
		accounts.remove(accounts.get(selectedAccountIndex));
		BankLogger.logMessage("info", "Closed account number " + iAccountNumber + ".\n");
		role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTS_FILE);
		BankLogger.logWriteItems(accounts);
	}

}
