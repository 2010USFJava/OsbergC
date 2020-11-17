package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.bank.LoginService;
import com.revature.bank.Role;

public class LoginTest {
	
	@Test
	public void validateLoginTest() {
		Role role = new Role();
		LoginService ls = new LoginService();
		String username = "hsolo";
		String password = "falcon";
		Integer iUserId = ls.validateLogin(role, username, password);
		Integer expectediUserId = 3;
		assertEquals(expectediUserId,iUserId);
	}
}
