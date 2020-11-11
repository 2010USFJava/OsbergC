package com.revature.bank;

/**
 * The AdminServices class provides a list of Service subclasses that, as a
 * whole, mimics the command design pattern. The AdminServices list serves as a
 * whitelist for Admin services.
 * <p>
 * 
 * @author Christopher Osberg
 */

public class AdminServices extends RoleServices {

	private Service[] servicesArray = {
			new ViewAccountsService(),
			new DepositService(),
			new WithdrawService(),
			new TransferService(),
			new ManageApplicationService(),
			new CloseAccountService(),
			new LogoffService(),
			new QuitService() };

	public Service[] getServicesArray() {
		return servicesArray;
	}
}
