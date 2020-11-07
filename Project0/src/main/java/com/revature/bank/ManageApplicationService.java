package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public class ManageApplicationService extends Service {

	public ManageApplicationService() {
		super();
		serviceName = "Approve/Deny an Account Application";
	}

	@Override
	public boolean performService(Role role) {
		Integer iAccountNumber = obtainTargetUserAccountNumber(role,
				"Please enter the number for the application you would like to manage.",
				FileManager.ACCOUNT_APPLICATIONS_FILE);
		Integer iAccountIndex = obtainAccountIndex(role, iAccountNumber, FileManager.ACCOUNT_APPLICATIONS_FILE);
		System.out.println("Approve or deny this account?");
		System.out.println("1. Approve");
		System.out.println("2. Deny");
		String sApproveOrDeny = scanner.nextLine();
		Integer iApproveOrDeny = InputVerifier.verifyIntegerInput(sApproveOrDeny, 0, 3);
		handleApplication(role, iAccountIndex, iApproveOrDeny);
		return true;
	}

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
			System.out.println("Error: Invalid input");
			break;
		}
	}
}
