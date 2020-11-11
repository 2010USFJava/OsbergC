package com.revature.bank;

import java.math.BigDecimal;

import com.revature.util.FileManager;
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

}
