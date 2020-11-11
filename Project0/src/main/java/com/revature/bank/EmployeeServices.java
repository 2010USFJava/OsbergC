package com.revature.bank;

/**
 * The EmployeeServices class provides a list of Service subclasses that, as a
 * whole, mimics the command design pattern. The AdminServices list serves as a
 * whitelist for Employee services.
 * <p>
 * 
 * @author Christopher Osberg
 * 
 */
public class EmployeeServices extends RoleServices {
	
	private Service[] servicesArray = {
			new ViewAccountsService(),
			new ManageApplicationService(),
			new LogoffService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
}
