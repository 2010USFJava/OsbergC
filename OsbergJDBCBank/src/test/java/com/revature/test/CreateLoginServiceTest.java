package com.revature.test;

import org.junit.Test;

import com.revature.bank.CreateLoginService;
import com.revature.bank.Login;
import com.revature.bank.Role;
import com.revature.role.roleName;

public class CreateLoginServiceTest {
	
	@Test
	public void createLoginTest() {
		Role role = new Role();
		CreateLoginService cls = new CreateLoginService();
		String username = "jsmith";
		String password = "hellowworld";
		String givenName = "John Smith";
		Login login = cls.createLogin(role, username, password, password, givenName);
		Login expectedLogin = new Login(null, username, password, roleName.CUSTOMER, givenName);
		equals(expectedLogin.equals(login));
	}
}
