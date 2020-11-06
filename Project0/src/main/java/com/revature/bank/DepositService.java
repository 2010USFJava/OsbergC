package com.revature.bank;

public class DepositService extends Service {

	public DepositService() {
		super();
		serviceName = "Make a Deposit";
	}

	@Override
	public boolean performService(Role role) {
		
		return true;
	}

}
