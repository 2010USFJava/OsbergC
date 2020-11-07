package com.revature.bank;

public class AdminServices extends RoleServices {
	
	private Service[] servicesArray = {
			new ViewAccountsService(),
			new DepositService(),
			new WithdrawService(),
			new TransferService(),
			new ManageApplicationService(),
			new CloseAccountService(),
			new LogoffService(),
			new QuitService()
			};
	
	public Service[] getServicesArray() {
		return servicesArray;
	}
}
