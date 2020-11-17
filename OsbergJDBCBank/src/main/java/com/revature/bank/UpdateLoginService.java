package com.revature.bank;

import java.sql.SQLException;

import com.revature.exception.InvalidInputException;
import com.revature.util.InputVerifier;

public class UpdateLoginService extends Service {
	
	public UpdateLoginService() {
		super();
		serviceName = "Update a user's name";
	}

	@Override
	public boolean performService(Role role) {
		System.out.println("Please enter the user ID for the user being updated.");
		String sUserId = scanner.nextLine();
		System.out.println("Please enter a new name.");
		String sNewName = scanner.nextLine();
		Integer iUserId = null;
		try {
			iUserId = InputVerifier.verifyIntegerInput(sUserId, 0, Integer.MAX_VALUE);
			role.getLdi().updateUser(iUserId, sNewName);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Exception: Invalid input");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
