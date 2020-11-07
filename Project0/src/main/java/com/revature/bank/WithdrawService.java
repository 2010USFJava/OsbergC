package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public class WithdrawService extends Service {

	public WithdrawService() {
		super();
		serviceName = "Make a Withdrawal";
	}

	@Override
	public boolean performService(Role role) {
		Integer iAccountNumber = obtainTargetUserAccountNumber(role,
				"From which account would you like to make a withdrawal?", FileManager.ACCOUNTS_FILE);
		if (iAccountNumber < 0) {
			return true;
		}
		System.out.println("How much would you like to withdraw?");
		String sWithdrawal = scanner.nextLine();
		BigDecimal bdWithdrawal = InputVerifier.verifyBigDecimalInput(sWithdrawal, new BigDecimal(0),
				new BigDecimal(Integer.MAX_VALUE));
		if (bdWithdrawal.intValue() < 0) {
			return true;
		}
		makeWithdrawal(role, iAccountNumber, bdWithdrawal);
		return true;
	}

	private BigDecimal makeWithdrawal(Role role, Integer iAccountNumber, BigDecimal bdWithdrawal) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		Integer selectedAccountIndex = obtainAccountIndex(role, iAccountNumber, FileManager.ACCOUNTS_FILE);
		BigDecimal bdDiff = accounts.get(selectedAccountIndex).getBalance().subtract(bdWithdrawal);
		if (bdDiff.compareTo(BigDecimal.valueOf(-0.005)) > 0) {
			accounts.get(selectedAccountIndex).setBalance(bdDiff);
		}else {
			System.out.println("Error: Insufficient funds");
			return BigDecimal.valueOf(-1);
		}
		role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTS_FILE);
		BankLogger.logMessage("info", "Made a withdrawal of " + bdWithdrawal + " from account number " + iAccountNumber
				+ ". The account now has $" + bdDiff + ".\n");
		return bdDiff;
	}
}
