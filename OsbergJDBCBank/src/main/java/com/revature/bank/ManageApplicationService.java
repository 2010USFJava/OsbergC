package com.revature.bank;

import java.sql.SQLException;

import com.revature.banklogger.BankLogger;
import com.revature.exception.InvalidInputException;
import com.revature.exception.NoAccountsException;
import com.revature.exception.UserDoesNotExistException;
import com.revature.util.InputVerifier;

/**
 * The ManageApplicationService class contains the functionality for approving
 * or denying account applications.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class ManageApplicationService extends Service {

	public ManageApplicationService() {
		super();
		serviceName = "Approve/Deny an Account Application";
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
		Integer iAccountNumber = null;
		try {
			iAccountNumber = obtainTargetUserAccountNumber(role,
					"Please enter the number for the application you would like to manage.", "pending");
			System.out.println("Approve or deny this account?");
			System.out.println("1. Approve");
			System.out.println("2. Deny");
			String sApproveOrDeny = scanner.nextLine();
			Integer iApproveOrDeny = null;
			iApproveOrDeny = InputVerifier.verifyIntegerInput(sApproveOrDeny, 0, 3);
			handleApplication(role, iAccountNumber, iApproveOrDeny);
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Exception: Invalid input");
		}
		return true;
	}

	/**
	 * The handleApplication method reads in all account applications, and either
	 * approves or denies them based on the parameters, then it writes all account
	 * applications to a file.
	 * <p>
	 * 
	 * @param role           The role parameter is the wrapper class identity for
	 *                       the user of the program. It contains references to
	 *                       non-package classes.
	 * @param iApproveOrDeny contains the numeric menu choice for approving or
	 *                       denying the application.
	 */
	private void handleApplication(Role role, Integer iAccountNumber, Integer iApproveOrDeny) {
		try {
			switch (iApproveOrDeny) {
			case 1:
				role.getAdi().approveAccount(iAccountNumber);
				BankLogger.logMessage("info", "Account number " + iAccountNumber + " was approved.\n");
				break;
			case 2:
				role.getAdi().deleteAccount(iAccountNumber);
				BankLogger.logMessage("info", "Account number " + iAccountNumber + " was denied.\n");
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
