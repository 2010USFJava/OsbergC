package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.bank.LoginService;
import com.revature.bank.Role;
import com.revature.role.roleName;

public class ServiceTest {
	
	@Test
	public void obtainUserIdTest() {
		Role customerRole = new Role();
		customerRole.setRoleName(roleName.CUSTOMER);
		customerRole.setUserId(1);
		LoginService ls = new LoginService();
		int iUserId = ls.obtainUserId(customerRole);
		assertEquals(1, iUserId);
	}
}
