package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public class TransferService extends Service {

	public TransferService() {
		super();
		serviceName = "Make a Transfer";
	}

	@Override
	public boolean performService(Role role) {
		Integer iWithdrawalAccountNumber = obtainTargetUserAccountNumber(role,
				"From which account would you like to transfer funds?", FileManager.ACCOUNTS_FILE);
		if (iWithdrawalAccountNumber < 0) {
			return true;
		}
		Integer iDepositAccountNumber = obtainTargetUserAccountNumber(role,
				"To which account would you like to transfer funds?", FileManager.ACCOUNTS_FILE);
		if (iDepositAccountNumber < 0) {
			return true;
		}
		System.out.println("How much would you like to transfer?");
		String sTransfer = scanner.nextLine();
		BigDecimal bdTransfer = InputVerifier.verifyBigDecimalInput(sTransfer, new BigDecimal(0),
				new BigDecimal(Integer.MAX_VALUE));
		if (bdTransfer.intValue() < 0) {
			return true;
		}
		if (makeWithdrawal(role, iWithdrawalAccountNumber, bdTransfer).compareTo(BigDecimal.valueOf(0)) > -1) {
			makeDeposit(role, iDepositAccountNumber, bdTransfer);
		}
		return true;
	}

	BigDecimal makeWithdrawal(Role role, Integer iAccountNumber, BigDecimal bdWithdrawal) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		Integer selectedAccountIndex = obtainAccountIndex(role, iAccountNumber, FileManager.ACCOUNTS_FILE);
		BigDecimal bdDiff = accounts.get(selectedAccountIndex).getBalance().subtract(bdWithdrawal);
		if (bdDiff.compareTo(BigDecimal.valueOf(-0.005)) > 0) {
			accounts.get(selectedAccountIndex).setBalance(bdDiff);
		} else {
			System.out.println("Error: Insufficient funds");
			return BigDecimal.valueOf(-1);
		}
		role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTS_FILE);
		BankLogger.logMessage("info", "Made a withdrawal of " + bdWithdrawal + " from account number " + iAccountNumber
				+ ". The account now has $" + bdDiff + ".\n");
		return bdDiff;
	}

	BigDecimal makeDeposit(Role role, Integer iAccountNumber, BigDecimal bdDeposit) {
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
