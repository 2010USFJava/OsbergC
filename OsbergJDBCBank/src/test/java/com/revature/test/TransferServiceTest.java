package com.revature.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.revature.bank.Role;
import com.revature.bank.TransferService;

public class TransferServiceTest {

	@Test
	public void makeWithdrawalTest() {
		Role role = new Role();
		TransferService ts = new TransferService();
		Integer iWithdrawalAccountNumber = 1;
		BigDecimal bdWithdrawal = new BigDecimal("1.00");
		bdWithdrawal.setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal balance = ts.makeWithdrawal(role, iWithdrawalAccountNumber, bdWithdrawal);
		balance.setScale(2, RoundingMode.HALF_EVEN);
		System.out.println(balance);
		equals(balance.equals(BigDecimal.valueOf(373.84)));
	}

	@Test
	public void makeDepositTest() {
		Role role = new Role();
		TransferService ts = new TransferService();
		Integer iDepositAccountNumber = 4;
		BigDecimal bdDeposit = new BigDecimal("1.00");
		bdDeposit.setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal balance = ts.makeDeposit(role, iDepositAccountNumber, bdDeposit);
		balance.setScale(2, RoundingMode.HALF_EVEN);
		System.out.println(balance);
		equals(balance.equals(BigDecimal.valueOf(1.00)));
	}
}
