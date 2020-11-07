package com.revature.bank;

import java.math.BigDecimal;

import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public class DepositService extends TransferService {

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

	
}
