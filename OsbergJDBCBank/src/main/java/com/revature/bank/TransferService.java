package com.revature.bank;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.revature.banklogger.BankLogger;
import com.revature.exception.InsufficientFundsException;
import com.revature.exception.InvalidInputException;
import com.revature.exception.NoAccountsException;
import com.revature.exception.UserDoesNotExistException;
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
		Integer iWithdrawalAccountNumber = null;
		try {
			iWithdrawalAccountNumber = obtainTargetUserAccountNumber(role,
					"From which account would you like to transfer funds?", "approved");
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
			return true;
		}
		System.out.println("Please enter the user ID for the account you wish you transfer to.");
		String sDepositUserId = scanner.nextLine();
		Integer iDepositUserId = null;
		try {
			iDepositUserId = InputVerifier.verifyIntegerInput(sDepositUserId, 0, Integer.MAX_VALUE);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		}
		Integer iDepositAccountNumber = null;
		try {
			iDepositAccountNumber = useUserIdToGetTargetAccount(role,
					"To which account would you like to transfer funds?", "approved", iDepositUserId);
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
			return true;
		}
		System.out.println("How much would you like to transfer?");
		String sTransfer = scanner.nextLine();
		BigDecimal bdTransfer = null;
		try {
			bdTransfer = InputVerifier.verifyBigDecimalInput(sTransfer, new BigDecimal(0),
					new BigDecimal(Integer.MAX_VALUE));
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		}
		if (bdTransfer.intValue() < 0) {
			return true;
		}
		try {
			if (makeWithdrawal(role, iWithdrawalAccountNumber, bdTransfer).compareTo(BigDecimal.valueOf(0)) > -1) {
				makeDeposit(role, iDepositAccountNumber, bdTransfer);
			}
		} catch (InsufficientFundsException e) {
			System.out.println(e.getMessage());
			return true;
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
	public BigDecimal makeWithdrawal(Role role, Integer iAccountNumber, BigDecimal bdWithdrawal) {
		Account account = null;
		BigDecimal bdDiff = null;
		try {
			account = role.getAdi().getAccountByAccountNumber(iAccountNumber);
			bdDiff = account.getBalance().subtract(bdWithdrawal);
			if (bdDiff.compareTo(BigDecimal.ZERO) >= 0) {
				role.getAdi().updateAccountBalance(iAccountNumber, bdDiff);
				BankLogger.logMessage("info", "Made a withdrawal of $" + bdWithdrawal + " from account number "
						+ iAccountNumber + ". The account now has $" + bdDiff + ".\n");
				role.getTdi().insertTransaction(role.getUserId(), "Made a withdrawal of $" + bdWithdrawal + " from account number "
						+ iAccountNumber + ". The account now has $" + bdDiff + ".\n");
			} else {
				throw new InsufficientFundsException("Exception: Insufficient funds");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public BigDecimal makeDeposit(Role role, Integer iAccountNumber, BigDecimal bdDeposit) {
		Account account = null;
		BigDecimal bdSum = null; 
		try {
			account = role.getAdi().getAccountByAccountNumber(iAccountNumber);
			bdSum = account.getBalance().add(bdDeposit);
			role.getAdi().updateAccountBalance(iAccountNumber, bdSum);
			BankLogger.logMessage("info", "Made a deposit of $" + bdDeposit + " into account number " + iAccountNumber
					+ ". The account now has $" + bdSum + ".\n");
			role.getTdi().insertTransaction(role.getUserId(), "Made a deposit of $" + bdDeposit + " into account number " + iAccountNumber
					+ ". The account now has $" + bdSum + ".\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bdSum;
	}
}
