package com.revature.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import com.revature.bank.Account;
import com.revature.bank.ApplyForAccountService;
import com.revature.bank.Role;
import com.revature.role.roleName;

public class ApplyForAccountServiceTest {
	
	@Test
	public void createAccountApplicationTest() {
		Role role = new Role();
		role.setRoleName(roleName.CUSTOMER);
		ApplyForAccountService afas = new ApplyForAccountService();
		Integer iAccountType = 1;
		ArrayList<Integer> userIds = new ArrayList<Integer>();
		userIds.add(1);
		Account account = afas.createAccountApplication(role, iAccountType, userIds);
		Account expectedAccount = new Account(null, "checking", userIds, BigDecimal.ZERO);
		equals(expectedAccount.equals(account));
	}
}
