package com.revature.bank;

import com.revature.banklogger.BankLogger;

/**
 * The LogoffService class contains the functionality for logging the user off.
 * <p>
 * @author Christopher Osberg
 *
 */
public class LogoffService extends Service {

	public LogoffService() {
		super();
		serviceName = "Log off";
	}

	/**
	 * The performService method overrides the parent method in order to query and
	 * verify user input for further use.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
	@Override
	public boolean performService(Role role) {
		resetRole(role);
		return true;
	}
	
	/**
	 * The resetRole method sets the role's RoleService field to a basic RoleService instance.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 */
	private void resetRole(Role role) {
		System.out.println("You have been logged off.");
		BankLogger.logMessage("info", "User number " + role.getUserId() + " logged off.");
		role.setRoleServices(new RoleServices());
	}

}
