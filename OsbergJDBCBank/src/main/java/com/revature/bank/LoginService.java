package com.revature.bank;

import java.sql.SQLException;

import com.revature.role.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.exception.WrongUsernameOrPasswordException;

/**
 * The LoginService class contains the functionality for logging into the bank.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class LoginService extends Service {

	public LoginService() {
		super();
		serviceName = "Log in";
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
	public boolean performService(Role role) {
		System.out.println("Please enter your username.");
		String username = scanner.nextLine();
		System.out.println("Please enter your password.");
		String password = scanner.nextLine();
		try {
			validateLogin(role, username, password);
		} catch (WrongUsernameOrPasswordException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	/**
	 * The validateLogin method reads in all Logins, verifies the username and
	 * password, and initializes role fields.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return Integer Returns the user's ID if successful, or -1 if unsuccessful.
	 */
	public Integer validateLogin(Role role, String username, String password) {
		Login login = null;
		try {
			login = role.getLdi().getLoginById(username);
			if (login == null) {
				BankLogger.logMessage("info", "Attempted login as \"" + username + "\"\n");
				throw new WrongUsernameOrPasswordException("Exception: Username and/or password were incorrect");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (password.equals(login.getPassword())) {
			switch (login.getRole()) {
			case CUSTOMER:
				role.setRoleServices(new CustomerServices());
				role.setUserId(login.getUserId());
				role.setGivenName(login.getGivenName());
				role.setRoleName(roleName.CUSTOMER);
				break;
			case EMPLOYEE:
				role.setRoleServices(new EmployeeServices());
				role.setRoleName(roleName.EMPLOYEE);
				break;
			case ADMIN:
				role.setRoleServices(new AdminServices());
				role.setRoleName(roleName.ADMIN);
				break;
			default:
				break;
			}
			BankLogger.logMessage("info", "Logged in as user number " + role.getUserId() + "\n");
			return role.getUserId();
		} else {
			BankLogger.logMessage("info", "Attempted login as user number " + login.getUserId() + "\n");
			throw new WrongUsernameOrPasswordException("Exception: Username and/or password were incorrect");
		}
	}
}