package com.revature.bank;

public class RoleServices {
	
	private Service[] servicesArray = {
			new LoginService(),
			new CreateLoginService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
	
	enum roleName {
		CUSTOMER, EMPLOYEE, ADMIN
	}
}
