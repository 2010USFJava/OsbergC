package com.revature.bank;

import com.revature.banklogger.BankLogger;

public class LoginService extends Service {
	
	public LoginService() {
		super();
		serviceName = "Log in";
	}

	public boolean performService(Role role) {
		role.setRoleServices(new CustomerServices());
		BankLogger.LogIt("info", "Logged in as DEFAULT");
		return true;
	}
}
