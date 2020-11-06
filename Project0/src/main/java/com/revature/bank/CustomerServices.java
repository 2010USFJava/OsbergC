package com.revature.bank;

public class CustomerServices extends RoleServices {
	
	private Service[] servicesArray = {
			new ViewAccountsService(),
			new DepositService(),
			new WithdrawService(),
			new TransferService(),
			new ApplyForAccountService(),
			new LogoffService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
}
