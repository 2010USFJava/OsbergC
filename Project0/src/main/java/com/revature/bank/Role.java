package com.revature.bank;

public class Role {
	RoleServices roleServices;
	FileManager fileManager;
	
	public Role() {
		super();
		this.roleServices = new RoleServices();
		this.fileManager = new FileManager();
	}
	
	enum roleName {
		CUSTOMER, EMPLOYEE, ADMIN
	}

	public RoleServices getRoleServices() {
		return roleServices;
	}

	public void setRoleServices(RoleServices roleServices) {
		this.roleServices = roleServices;
	}
}
