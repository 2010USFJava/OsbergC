package com.revature.bank;

public class LogoffService extends Service {

	public LogoffService() {
		super();
		serviceName = "Log off";
	}

	@Override
	public boolean performService(Role role) {
		role.setRoleServices(new RoleServices());
		return true;
	}

}
