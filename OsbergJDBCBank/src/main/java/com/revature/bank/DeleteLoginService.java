package com.revature.bank;

import java.sql.SQLException;

import com.revature.banklogger.BankLogger;
import com.revature.exception.InvalidInputException;
import com.revature.exception.UserDoesNotExistException;
import com.revature.util.InputVerifier;

public class DeleteLoginService extends Service {

	public DeleteLoginService() {
		super();
		serviceName = "Delete a User";
	}

	@Override
	public boolean performService(Role role) {
		System.out.println("Please enter the user ID for the user being deleted.");
		String sUserId = scanner.nextLine();
		Integer iUserId = null;
		try {
			iUserId = InputVerifier.verifyIntegerInput(sUserId, 0, Integer.MAX_VALUE);
			if (!role.getLdi().deleteUser(iUserId)) {
				throw new UserDoesNotExistException("Exception: User does not exist");
			}
			BankLogger.logMessage("info", "The user with user ID " + iUserId + " was deleted.\n");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
