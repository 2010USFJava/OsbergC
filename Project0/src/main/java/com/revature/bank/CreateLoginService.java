package com.revature.bank;

import java.util.ArrayList;

public class CreateLoginService extends Service {
	public CreateLoginService() {
		super();
		serviceName = "Create Login";
	}

	@Override
	public boolean performService(Role role) {
		String username;
		String password;
		String passwordConfirmation;
		ArrayList<Login> logins = new ArrayList<Login>();
		logins = role.fileManager.readItemsFromFile("logins.txt");
		System.out.println(logins.toString());
		System.out.println("Please enter a username.");
		username = scanner.nextLine();
		System.out.println("Please enter a password.");
		password = scanner.nextLine();
		System.out.println("Please confirm your password.");
		passwordConfirmation = scanner.nextLine();
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
				logins.add(new Login(userID, username, password, "customer"));
			}
			else {
				System.out.println("The passwords did not match.");
				return true;
			}
		}
		role.fileManager.writeItemsToFile(logins, "logins.txt");
		return true;
	}
}
