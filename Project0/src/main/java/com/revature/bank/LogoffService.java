package com.revature.bank;

import com.revature.banklogger.BankLogger;

public class LogoffService extends Service {

	public LogoffService() {
		super();
		serviceName = "Log off";
	}

	@Override
	public boolean performService(Role role) {
		resetRole(role);
		return true;
	}
	
	private int resetRole(Role role) {
		System.out.println("You have been logged off.");
		BankLogger.logMessage("info", "User number " + role.getUserID() + " logged off.");
		role.setRoleServices(new RoleServices());
		return role.getUserID();
	}

}
