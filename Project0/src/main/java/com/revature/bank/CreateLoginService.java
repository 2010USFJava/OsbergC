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
		return createLogin(role, username, password, passwordConfirmation, givenName);
	}
	
	private boolean createLogin(Role role, String username, String password, String passwordConfirmation, String givenName) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		// Default logins
//		logins.add(new Login(1, "lskywalker", "force", roleName.EMPLOYEE, "Luke Skywalker"));
//		logins.add(new Login(2, "lorgana", "alliance", roleName.ADMIN, "Leia Organa"));
		
		System.out.println(logins.toString());
		ArrayList<String> usernameList = new ArrayList<String>();
		ArrayList<Integer> userIDList = new ArrayList<Integer>();
		for(Login l : logins) {
			userIDList.add(new Integer(l.getUserID()));
			usernameList.add(l.getUsername());
		}
		if(usernameList.contains(username)) {
			System.out.println("The username was already taken.");
			return true;
		}
		else {
			if(password.equals(passwordConfirmation)) {
				int userID = 1;
				while(userIDList.contains(userID)) {
					userID++;
				}
				logins.add(new Login(userID, username, password, roleName.CUSTOMER, givenName));
				BankLogger.logMessage("info", "Created a new login with:\nuserID: " + userID +
						"\nusername: " + username + "\npassword: " + password + "\nroleService: " +
						roleName.CUSTOMER + "\ngivenName: " + givenName);
			}
			else {
				System.out.println("The passwords did not match.");
				return true;
			}
		}
		role.getFileManager().writeItemsToFile(logins, "logins.txt");
		return true;		
	}
}
