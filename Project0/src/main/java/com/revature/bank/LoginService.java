package com.revature.bank;

import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;

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
		validateLogin(role, username, password);
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
	private Integer validateLogin(Role role, String username, String password) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile(FileManager.LOGINS_FILE);
		BankLogger.logReadItems(logins);
		for (Login login : logins) {
			if (username.equals(login.getUsername())) {
				if (password.equals(login.getPassword())) {
					switch (login.getRole()) {
					case CUSTOMER:
						role.setRoleServices(new CustomerServices());
						role.setUserID(login.getUserID());
						role.setGivenName(login.getGivenName());
						ArrayList<Account> accounts = role.getFileManager()
								.readItemsFromFile(FileManager.ACCOUNTS_FILE);
						BankLogger.logReadItems(accounts);
						role.setAccountNumbers(role.getFileManager().getAllAccountNumbers(role, accounts));
						role.setRoleName(roleName.CUSTOMER);
						break;
					case EMPLOYEE:
						role.setRoleServices(new EmployeeServices());
						role.setUserID(login.getUserID());
						role.setRoleName(roleName.EMPLOYEE);
						break;
					case ADMIN:
						role.setRoleServices(new AdminServices());
						role.setUserID(login.getUserID());
						role.setRoleName(roleName.ADMIN);
						break;
					default:
						System.out.println("Error: Unrecognized role");
						break;
					}
					BankLogger.logMessage("info", "Logged in as user number " + role.getUserID() + "\n");
					return role.getUserID();
				} else {
					System.out.println("Your username and/or password was incorrect.");
					BankLogger.logMessage("info", "Attempted login as user number " + login.getUserID() + "\n");
					return login.getUserID();
				}
			}
		}
		System.out.println("Your username and/or password was incorrect.");
		BankLogger.logMessage("info", "Attempted login as \"" + username + "\"\n");
		return -1;
	}
}