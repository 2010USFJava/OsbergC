package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

/**
 * The TransferService class contains the functionality for transferring money
 * from one account to another.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class TransferService extends Service {

	public TransferService() {
		super();
		serviceName = "Make a Transfer";
	}

	/**
	 * The performService method overrides the parent method in order to query and
	 * verify user input for further use. The method then removes money from one
	 * account and places it into another.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
	@Override
	public boolean performService(Role role) {
		Integer iWithdrawalAccountNumber = obtainTargetUserAccountNumber(role,
				"From which account would you like to transfer funds?", FileManager.ACCOUNTS_FILE);
		if (iWithdrawalAccountNumber < 0) {
			return true;
		}
//		Integer iDepositAccountNumber = obtainTargetUserAccountNumber(role,
//				"To which account would you like to transfer funds?", FileManager.ACCOUNTS_FILE);
		System.out.println("Please enter the user ID for the account you wish you transfer to.");
		String sDepositUserID = scanner.nextLine();
		Integer iDepositUserID = InputVerifier.verifyIntegerInput(sDepositUserID, 0, Integer.MAX_VALUE);
		if (iDepositUserID < 0) {
			return true;
		}
		Integer iDepositAccountNumber = useUserIDToGetTargetAccount(role,
				"To which account would you like to transfer funds?", FileManager.ACCOUNTS_FILE, iDepositUserID);
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

	/**
	 * The makeWithdrawal method reads in all accounts, gets the accounts associated
	 * with the user ID, verifies whether the account has enough funds, and removes
	 * the funds from the account, then it writes the accounts to a file.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return BigDecimal Returns the amount of money in the account.
	 */
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

	/**
	 * The makeDeposit method reads in all accounts, gets the accounts associated
	 * with the user, and adds funds to the account, and writes the accounts to a
	 * file.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
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
