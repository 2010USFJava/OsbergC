package com.revature.bank;

/**
 * The RoleServices class provides a list of Service subclasses that, as a
 * whole, mimics the command design pattern. The RoleServices list serves as a
 * whitelist for the most basic services.
 * <p>
 * 
 * @author Christopher Osberg
 */
public class RoleServices {
	
	private Service[] servicesArray = {
			new LoginService(),
			new CreateLoginService(),
			new QuitService()
			};

	public Service[] getServicesArray() {
		return servicesArray;
	}
	
	public enum roleName {
		CUSTOMER, EMPLOYEE, ADMIN
	}
}
