package com.revature.bank;

import java.math.BigDecimal;

import com.revature.exception.InsufficientFundsException;
import com.revature.exception.InvalidInputException;
import com.revature.exception.NoAccountsException;
import com.revature.exception.UserDoesNotExistException;
import com.revature.util.InputVerifier;

/**
 * The WithdrawService class contains the functionality for making withdrawals
 * from a bank account.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class WithdrawService extends TransferService {

	public WithdrawService() {
		super();
		serviceName = "Make a Withdrawal";
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
					"From which account would you like to make a withdrawal?", "approved");
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return true;
		}
		System.out.println("How much would you like to withdraw?");
		String sWithdrawal = scanner.nextLine();
		BigDecimal bdWithdrawal = null;
		try {
			bdWithdrawal = InputVerifier.verifyBigDecimalInput(sWithdrawal, new BigDecimal(0),
					new BigDecimal(Integer.MAX_VALUE));
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Exception: Invalid input");
			return true;
		}
		try {
			makeWithdrawal(role, iAccountNumber, bdWithdrawal);
		} catch (InsufficientFundsException e) {
			System.out.println(e.getMessage());
			return true;
		}
		return true;
	}

}
