package com.revature.bank;

import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;

public class CloseAccountService extends Service {

	public CloseAccountService() {
		super();
		serviceName = "Close an Account";
	}

	@Override
	public boolean performService(Role role) {
		Integer iAccountNumber = obtainTargetUserAccountNumber(role,
				"Please enter the number for the account you wish to close.", FileManager.ACCOUNTS_FILE);
		if(iAccountNumber < 0) {
			return true;
		}
		closeAccount(role, iAccountNumber);
		return true;
	}

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
