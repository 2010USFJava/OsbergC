package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public class DepositService extends Service {

	public DepositService() {
		super();
		serviceName = "Make a Deposit";
	}

	@Override
	public boolean performService(Role role) {
		Integer iUserID;
		if ((iUserID = queryUserID(role)) < 0) {
			return true;
		}
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, iUserID,
				FileManager.ACCOUNTSFILE);
		MenuFormatter.displayAccountMenu(role, userAccounts);
		System.out.println("Into which account would you like to make a deposit?");
		String sAccountSelection = scanner.nextLine();
		Integer iAccountSelection = InputVerifier.verifyIntegerInput(sAccountSelection, 0, userAccounts.size());
		if (iAccountSelection < 0) {
			return true;
		}
		System.out.println("How much would you like to deposit?");
		String sDeposit = scanner.nextLine();
		BigDecimal bdDeposit = InputVerifier.verifyBigDecimalInput(sDeposit, new BigDecimal(0),
				new BigDecimal(Integer.MAX_VALUE));
		if (bdDeposit.intValue() < 0) {
			return true;
		}
		makeDeposit(role, iAccountSelection, bdDeposit, userAccounts);
		return true;
	}

	private BigDecimal makeDeposit(Role role, Integer iAccountSelection, BigDecimal bdDeposit,
			ArrayList<Account> userAccounts) {
		Integer accountNumber = userAccounts.get(iAccountSelection - 1).getAccountNumber();
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTSFILE);
		Account selectedAccount = null;
		for (Account account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				account.setBalance(bdDeposit);
				selectedAccount = account;
			}
		}
		role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTSFILE);
		BankLogger.logMessage("info", "Made a deposit of " + bdDeposit + " into account number "
				+ selectedAccount.getAccountNumber() + ".\n");
		return selectedAccount.getBalance();
	}
}
