package com.revature.bank;

public class EmployeeServices extends RoleServices {
	
	private Service[] servicesArray = {
			new ViewAccountsService(),
			new ManageAccountService(),
			new LogoffService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
}
