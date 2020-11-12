package com.revature.bank;

import java.util.ArrayList;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordVerificationException;
import com.revature.util.FileManager;

/**
 * The CreateLogin Service class contains the functionality for creating a
 * login.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class CreateLoginService extends Service {
	public CreateLoginService() {
		super();
		serviceName = "Create Login";
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
		System.out.println("Please enter a username.");
		String username = scanner.nextLine();
		System.out.println("Please enter a password.");
		String password = scanner.nextLine();
		System.out.println("Please confirm your password.");
		String passwordConfirmation = scanner.nextLine();
		System.out.println("Please enter your real name.");
		String givenName = scanner.nextLine();
		try {
			createLogin(role, username, password, passwordConfirmation, givenName);
		} catch (DuplicateUsernameException e) {
			System.out.println(e.getMessage());
		} catch (PasswordVerificationException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	/**
	 * The createLogin method reads in all logins as an ArrayList, compares the
	 * parameters with the database for duplicates, instantiates a new login, adds
	 * it to the list, and writes all logins to a file.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return ArrayList<Login> The return statement returns the new list of Login
	 *         objects.
	 */
	private ArrayList<Login> createLogin(Role role, String username, String password, String passwordConfirmation,
			String givenName) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile(FileManager.LOGINS_FILE);
		BankLogger.logReadItems(logins);
		// Default logins
//		logins.add(new Login(1, "lskywalker", "force", roleName.EMPLOYEE, "Luke Skywalker"));
//		logins.add(new Login(2, "lorgana", "alliance", roleName.ADMIN, "Leia Organa"));

		ArrayList<String> usernameList = role.getFileManager().getAllLoginUsernames(role, logins);
		ArrayList<Integer> userIDList = role.getFileManager().getAllLoginUserIDs(role, logins);
		if (usernameList.contains(username)) {
			throw new DuplicateUsernameException("Exception: Username taken");
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
				throw new PasswordVerificationException("Exception: Passwords did not match");
			}
		}
		role.getFileManager().writeItemsToFile(logins, FileManager.LOGINS_FILE);
		BankLogger.logWriteItems(logins);
		return logins;
	}
}
