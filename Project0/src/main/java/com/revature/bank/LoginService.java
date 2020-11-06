package com.revature.bank;

import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;

public class LoginService extends Service {

	public LoginService() {
		super();
		serviceName = "Log in";
	}

	public boolean performService(Role role) {
		System.out.println("Please enter your username.");
		String username = scanner.nextLine();
		System.out.println("Please enter your password.");
		String password = scanner.nextLine();
		validateLogin(role, username, password);
		return true;
	}

	private int validateLogin(Role role, String username, String password) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		BankLogger.logReadItems(logins);
		for (Login login : logins) {
			if (username.equals(login.getUsername())) {
				if (password.equals(login.getPassword())) {
					switch (login.getRole()) {
					case CUSTOMER:
						role.setRoleServices(new CustomerServices());
						role.setUserID(login.getUserID());
						role.setGivenName(login.getGivenName());
						ArrayList<Account> accounts = role.getFileManager().readItemsFromFile("accounts.txt");
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
					BankLogger.logMessage("info", "Attempted login as user number " + role.getUserID() + "\n");
					return role.getUserID();
				}
			}
		}
		System.out.println("Your username and/or password was incorrect.");
		BankLogger.logMessage("info", "Attempted login as \"" + username + "\"\n");
		return -1;
	}
}