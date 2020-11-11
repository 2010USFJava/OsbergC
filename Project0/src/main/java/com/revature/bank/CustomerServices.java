package com.revature.bank;

/**
 * The CustomerServices class provides a list of Service subclasses that, as a
 * whole, mimics the command design pattern. The AdminServices list serves as a
 * whitelist for Customer services.
 * <p>
 * 
 * @author Christopher Osberg
 * 
 */
public class CustomerServices extends RoleServices {
	
	private Service[] servicesArray = {
			new ViewAccountsService(),
			new DepositService(),
			new WithdrawService(),
			new TransferService(),
			new ApplyForAccountService(),
			new CloseAccountService(),
			new LogoffService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
}
