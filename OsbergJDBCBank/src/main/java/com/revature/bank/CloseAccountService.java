package com.revature.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.exception.NoAccountsException;
import com.revature.exception.NonZeroBalanceException;
import com.revature.exception.UserDoesNotExistException;
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
		Integer iAccountNumber = null;
		try {
			iAccountNumber = obtainTargetUserAccountNumber(role,
					"Please enter the number for the account you wish to close.", FileManager.ACCOUNTS_FILE);
			closeAccount(role, iAccountNumber);
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
		} catch (NonZeroBalanceException e) {
			System.out.println(e.getMessage());
		}
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
		if (role.getRoleName() != roleName.CUSTOMER || accounts.get(selectedAccountIndex).getBalance()
				.setScale(2, RoundingMode.HALF_EVEN).compareTo(BigDecimal.ZERO) == 0) {
			accounts.remove(accounts.get(selectedAccountIndex));
			BankLogger.logMessage("info", "Closed account number " + iAccountNumber + ".\n");
			role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTS_FILE);
			BankLogger.logWriteItems(accounts);
		} else {
			throw new NonZeroBalanceException("Exception: Non-zero balance");
		}
	}

}
