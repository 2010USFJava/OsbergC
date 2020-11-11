package com.revature.bank;

import java.math.BigDecimal;

import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

/**
 * The DepositService class contains the functionality for making deposits into
 * bank accounts.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class DepositService extends TransferService {

	public DepositService() {
		super();
		serviceName = "Make a Deposit";
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
				"Into which account would you like to make a deposit?", FileManager.ACCOUNTS_FILE);
		if (iAccountNumber < 0) {
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

}
