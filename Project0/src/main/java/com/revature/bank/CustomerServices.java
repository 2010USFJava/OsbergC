package com.revature.bank;

public class CustomerServices extends RoleServices {
	private Service[] servicesArray = {
			new ApplyForAccountService(),
			new DepositService(),
			new WithdrawService(),
			new ViewAccountsService(),
			new TransferService(),
			new LogoffService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
}
