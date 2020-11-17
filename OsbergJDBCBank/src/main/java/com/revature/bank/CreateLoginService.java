package com.revature.bank;

import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import com.revature.role.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordVerificationException;

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
		String username = null;
		String password = null;
		String passwordConfirmation = null;
		String givenName = null;
		if (role.getRoleName() != roleName.CUSTOMER) {
			System.out.println("Please enter a username.");
			username = scanner.nextLine();
			System.out.println("Please enter a password.");
			password = scanner.nextLine();
			System.out.println("Please confirm the password.");
			passwordConfirmation = scanner.nextLine();
			System.out.println("Please enter the user's real name.");
			givenName = scanner.nextLine();
		} else {
			System.out.println("Please enter a username.");
			username = scanner.nextLine();
			System.out.println("Please enter a password.");
			password = scanner.nextLine();
			System.out.println("Please confirm your password.");
			passwordConfirmation = scanner.nextLine();
			System.out.println("Please enter your real name.");
			givenName = scanner.nextLine();
		}
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
	public Login createLogin(Role role, String username, String password, String passwordConfirmation,
			String givenName) {
		Login login = new Login(null, username, password, roleName.CUSTOMER, givenName);
		if (password.equals(passwordConfirmation)) {
			try {
				role.getLdi().insertLogin(login);
			} catch (PSQLException e) {
				throw new DuplicateUsernameException("Exception: Username taken");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			BankLogger.logMessage("info", "Created a new login with:\nusername: " + username + "\npassword: " + password
					+ "\nroleService: " + roleName.CUSTOMER + "\ngivenName: " + givenName + "\n");
		} else {
			throw new PasswordVerificationException("Exception: Passwords did not match");
		}
		return login;
	}
}
