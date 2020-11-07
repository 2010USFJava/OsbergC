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
		Integer iAccountNumber = obtainTargetUserAccountNumber(role,
				"Into which account would you like to make a deposit?", FileManager.ACCOUNTS_FILE);
		if(iAccountNumber < 0) {
			return true;
		}
		System.out.println("How much would you like to deposit?");
		String sDeposit = scanner.nextLine();
		BigDecimal bdDeposit = InputVerifier.verifyBigDecimalInput(sDeposit, new BigDecimal(0),
				new BigDecimal(Integer.MAX_VALUE));
		if (bdDeposit.intValue() < 0) {
			return true;
		}
		makeDeposit(role, iAccountNumber, bdDeposit);
		return true;
	}

	private BigDecimal makeDeposit(Role role, Integer iAccountNumber, BigDecimal bdDeposit) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		Integer selectedAccountIndex = obtainAccountIndex(role, iAccountNumber, FileManager.ACCOUNTS_FILE);
		BigDecimal bdSum = accounts.get(selectedAccountIndex).getBalance().add(bdDeposit);
		accounts.get(selectedAccountIndex).setBalance(bdSum);
		role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTS_FILE);
		BankLogger.logMessage("info", "Made a deposit of " + bdDeposit + " into account number " + iAccountNumber
				+ ". The account now has $" + bdSum + ".\n");
		return bdSum;
	}
}
