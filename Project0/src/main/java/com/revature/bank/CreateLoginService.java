package com.revature.bank;

import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;

public class CreateLoginService extends Service {
	public CreateLoginService() {
		super();
		serviceName = "Create Login";
	}

	@Override
	public boolean performService(Role role) {
		System.out.println("Please enter a username.");
		String username = scanner.nextLine();
		System.out.println("Please enter a password.");
		String password = scanner.nextLine();
		System.out.println("Please confirm your password.");
		String passwordConfirmation = scanner.nextLine();
		System.out.println("Please enter your real name.");
		String givenName = scanner.nextLine();
		createLogin(role, username, password, passwordConfirmation, givenName);
		return true;
	}

	private ArrayList<Login> createLogin(Role role, String username, String password, String passwordConfirmation,
			String givenName) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		BankLogger.logMessage("info", "Logins read in:\n" + logins + "\n");
		// Default logins
//		logins.add(new Login(1, "lskywalker", "force", roleName.EMPLOYEE, "Luke Skywalker"));
//		logins.add(new Login(2, "lorgana", "alliance", roleName.ADMIN, "Leia Organa"));

		ArrayList<String> usernameList = role.getFileManager().getAllLoginUsernames(role, logins);
		ArrayList<Integer> userIDList = role.getFileManager().getAllLoginUserIDs(role, logins);
		if (usernameList.contains(username)) {
			System.out.println("The username was already taken.");
			return logins;
		} else {
			if (password.equals(passwordConfirmation)) {
				int userID = 1;
				while (userIDList.contains(userID)) {
					userID++;
				}
				logins.add(new Login(new Integer(userID), username, password, roleName.CUSTOMER, givenName));
				BankLogger.logMessage("info",
						"Created a new login with:\nuserID: " + userID + "\nusername: " + username + "\npassword: "
								+ password + "\nroleService: " + roleName.CUSTOMER + "\ngivenName: " + givenName
								+ "\n");
			} else {
				System.out.println("The passwords did not match.");
				return logins;
			}
		}
		role.getFileManager().writeItemsToFile(logins, "logins.txt");
		BankLogger.logMessage("info", "Logins written to file:\n" + logins + "\n");
		return logins;
	}
}
