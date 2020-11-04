package com.revature.bank;

import java.util.ArrayList;

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

	private boolean validateLogin(Role role, String username, String password) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		for (Login login : logins) {
			if (username.equals(login.getUsername())) {
				if (password.equals(login.getPassword())) {
					switch (login.getRole()) {
					case CUSTOMER:
						role.setRoleServices(new CustomerServices());
						role.setUserID(login.getUserID());
						role.setGivenName(login.getGivenName());
						break;
					case EMPLOYEE:
						role.setRoleServices(new EmployeeServices());
						break;
					case ADMIN:
						role.setRoleServices(new AdminServices());
						break;
					default:
						System.out.println("Error: Unrecognized role");
						break;
					}
					BankLogger.logMessage("info", "Logged in as " + role.getGivenName());
					return true;
				} else {
					System.out.println("Your username and/or password was incorrect.");
					BankLogger.logMessage("info", "Attempted login as " + username);
					return true;
				}
			}
		}
		System.out.println("Your username and/or password was incorrect.");
		return true;
	}
}

/*
 * Scanner scanner = new Scanner(System.in);
 * System.out.println("Please enter your username."); String enteredUsername =
 * scanner.nextLine(); System.out.println("Please enter your password."); String
 * enteredPassword = scanner.nextLine(); scanner.close(); File file = new
 * File("login.txt"); try { inputStream = new FileInputStream(file); } catch
 * (FileNotFoundException e) { e.printStackTrace(); }
 * 
 * role.userID = readWord(); role.username = readWord(); role.userPassword =
 * readWord(); role.userRole = readWord();
 * 
 * System.out.println("username: "+role.username);
 * System.out.println("entereredUsername: "+enteredUsername);
 * System.out.println("password: "+role.userPassword);
 * System.out.println("entereredPassword: "+enteredPassword);
 * 
 * if (enteredUsername.equals(role.username)) { if
 * (enteredPassword.equals(role.userPassword)) { switch (role.userRole) { case
 * "customer": role.setRoleServices(new CustomerServices());
 * BankLogger.LogIt("info", "Logged in as " + enteredUsername); return true;
 * case "employee": role.setRoleServices(new EmployeeServices());
 * BankLogger.LogIt("info", "Logged in as " + enteredUsername); return true;
 * case "admin": role.setRoleServices(new AdminServices());
 * BankLogger.LogIt("info", "Logged in as " + enteredUsername); return true; } }
 * else { System.out.println("Incorrect password"); return true; } }
 * System.out.println("No profile with that name was found."); return true; }
 * 
 * private String readWord() { StringBuilder word = new StringBuilder(); int
 * charValue = 0; try { charValue = inputStream.read(); word.append((char)
 * charValue); } catch (IOException e1) { e1.printStackTrace(); } while
 * (charValue != -1 && (char) charValue != ',' && (char) charValue != '\r') {
 * try { charValue = inputStream.read(); word.append((char) charValue); } catch
 * (IOException e) { e.printStackTrace(); } }
 * word.deleteCharAt(word.length()-1); return word.toString();
 */