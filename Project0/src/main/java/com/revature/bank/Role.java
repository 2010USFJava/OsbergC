package com.revature.bank;

public class Role {
	RoleServices roleServices;
	FileManager fileManager;
	/*String userID;
	String username;
	String userPassword;
	String userRole;*/
	
	public Role() {
		super();
		this.roleServices = new RoleServices();
		this.fileManager = new FileManager();
	}

	public RoleServices getRoleServices() {
		return roleServices;
	}

	public void setRoleServices(RoleServices roleServices) {
		this.roleServices = roleServices;
	}
}
