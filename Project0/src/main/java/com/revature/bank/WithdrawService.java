package com.revature.bank;

import java.math.BigDecimal;

import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public class WithdrawService extends TransferService {

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

	
}
