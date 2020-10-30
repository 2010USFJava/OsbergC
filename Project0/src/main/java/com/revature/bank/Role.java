package com.revature.bank;

public class Role {
	RoleServices roleServices;
	
	public Role(RoleServices roleServices) {
		this.roleServices = roleServices;
	}

	public RoleServices getRoleServices() {
		return roleServices;
	}

	public void setRoleServices(RoleServices roleServices) {
		this.roleServices = roleServices;
	}
}
