package com.revature.bank;

public abstract class Service {

	String serviceName;
	
	public boolean performService(Role role) {
		return true;
	}
	
	public String getServiceName() {
		return serviceName;
	}
}
