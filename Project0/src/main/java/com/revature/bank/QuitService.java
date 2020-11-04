package com.revature.bank;

import com.revature.banklogger.BankLogger;

public class QuitService extends Service {
	
	public QuitService() {
		super();
		serviceName = "Quit";
	}
	
	@Override
	public boolean performService(Role role) {
		BankLogger.logMessage("info", "The user quit the application.");
		return false;
	}

}
