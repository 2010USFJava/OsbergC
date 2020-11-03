package com.revature.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.revature.banklogger.BankLogger;

public class LoginService extends Service {

	public LoginService() {
		super();
		serviceName = "Log in";
	}

	public boolean performService(Role role) {
		role.roleServices = new CustomerServices();
		role.fileManager.readItemsFromFile("logins.txt");
		return true;
		/*Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username.");
		String enteredUsername = scanner.nextLine();
		System.out.println("Please enter your password.");
		String enteredPassword = scanner.nextLine();
		scanner.close();
		File file = new File("login.txt");
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		role.userID = readWord();
		role.username = readWord();
		role.userPassword = readWord();
		role.userRole = readWord();
		
		 * System.out.println("username: "+role.username);
		 * System.out.println("entereredUsername: "+enteredUsername);
		 * System.out.println("password: "+role.userPassword);
		 * System.out.println("entereredPassword: "+enteredPassword);
		 
		if (enteredUsername.equals(role.username)) {
			if (enteredPassword.equals(role.userPassword)) {
				switch (role.userRole) {
				case "customer":
					role.setRoleServices(new CustomerServices());
					BankLogger.LogIt("info", "Logged in as " + enteredUsername);
					return true;
				case "employee":
					role.setRoleServices(new EmployeeServices());
					BankLogger.LogIt("info", "Logged in as " + enteredUsername);
					return true;
				case "admin":
					role.setRoleServices(new AdminServices());
					BankLogger.LogIt("info", "Logged in as " + enteredUsername);
					return true;
				}
			} else {
				System.out.println("Incorrect password");
				return true;
			}
		}
		System.out.println("No profile with that name was found.");
		return true;
	}

	private String readWord() {
		StringBuilder word = new StringBuilder();
		int charValue = 0;
		try {
			charValue = inputStream.read();
			word.append((char) charValue);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (charValue != -1 && (char) charValue != ',' && (char) charValue != '\r') {
			try {
				charValue = inputStream.read();
				word.append((char) charValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		word.deleteCharAt(word.length()-1);
		return word.toString();*/
	}
}
