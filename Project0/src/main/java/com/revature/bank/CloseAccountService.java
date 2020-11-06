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
		Integer iAccountNumber = obtainTargetUserAccount(role, "Please enter the number for the account you wish to close.");
		closeAccount(role, iAccountNumber);
		return true;
	}

	private void closeAccount(Role role, Integer iAccountNumber) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTSFILE);
		BankLogger.logReadItems(accounts);
		Account selectedAccount = null;
		for (Account account : accounts) {
			if (account.getAccountNumber().equals(iAccountNumber)) {
				selectedAccount = account;
			}
		}
		accounts.remove(selectedAccount);
		BankLogger.logMessage("info", "Closed account number " + iAccountNumber + ".\n");
		role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTSFILE);
		BankLogger.logWriteItems(accounts);
	}

}
