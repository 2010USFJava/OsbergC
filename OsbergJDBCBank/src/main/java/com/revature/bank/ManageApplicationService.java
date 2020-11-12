package com.revature.bank;

import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.exception.InvalidInputException;
import com.revature.exception.NoAccountsException;
import com.revature.exception.UserDoesNotExistException;
import com.revature.util.FileManager;
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
					"Please enter the number for the application you would like to manage.",
					FileManager.ACCOUNT_APPLICATIONS_FILE);
			Integer iAccountIndex = obtainAccountIndex(role, iAccountNumber, FileManager.ACCOUNT_APPLICATIONS_FILE);
			System.out.println("Approve or deny this account?");
			System.out.println("1. Approve");
			System.out.println("2. Deny");
			String sApproveOrDeny = scanner.nextLine();
			Integer iApproveOrDeny = null;
			iApproveOrDeny = InputVerifier.verifyIntegerInput(sApproveOrDeny, 0, 3);
			handleApplication(role, iAccountIndex, iApproveOrDeny);
		} catch (UserDoesNotExistException e) {
			System.out.println(e.getMessage());
		} catch (NoAccountsException e) {
			System.out.println(e.getMessage());
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
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
	private void handleApplication(Role role, Integer iAccountIndex, Integer iApproveOrDeny) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		BankLogger.logReadItems(accounts);
		ArrayList<Account> accountApplications = role.getFileManager()
				.readItemsFromFile(FileManager.ACCOUNT_APPLICATIONS_FILE);
		BankLogger.logReadItems(accountApplications);
		Integer accountNumber = accountApplications.get(iAccountIndex).getAccountNumber();
		switch (iApproveOrDeny) {
		case 1:
			accounts.add(accountApplications.get(iAccountIndex));
			accountApplications.remove(accountApplications.get(iAccountIndex));
			role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTS_FILE);
			role.getFileManager().writeItemsToFile(accountApplications, FileManager.ACCOUNT_APPLICATIONS_FILE);
			BankLogger.logMessage("info", "Account number " + accountNumber + " was approved.\n");
			BankLogger.logWriteItems(accounts);
			break;
		case 2:
			accountApplications.remove(accountApplications.get(iAccountIndex));
			role.getFileManager().writeItemsToFile(accountApplications, FileManager.ACCOUNT_APPLICATIONS_FILE);
			BankLogger.logMessage("info", "Account number " + accountNumber + " was denied.\n");
			break;
		default:
			break;
		}
	}
}
