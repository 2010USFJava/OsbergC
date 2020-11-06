package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;

public class ManageAccountService extends Service {

	public ManageAccountService() {
		super();
		serviceName = "Approve/Deny an Account Application";
	}

	@Override
	public boolean performService(Role role) {
		System.out.println("Please enter the user's ID number.");
		String userIDString = scanner.nextLine();
		ArrayList<Account> userApplications = decideApplication(role, userIDString);
		if (userApplications.size() > 0) {
			String selectedApplication = scanner.nextLine();
			Integer iSelectedApplication = -1;
			try {
				iSelectedApplication = Integer.parseInt(selectedApplication);
				--iSelectedApplication;
			} catch (Exception e) {
				System.out.println("Error: Invalid input");
				return true;
			}
			System.out.println("Approve or deny this account?");
			System.out.println("1. Approve");
			System.out.println("2. Deny");
			String approveOrDeny = scanner.nextLine();
			Integer iApproveOrDeny = 0;
			try {
				iApproveOrDeny = Integer.parseInt(approveOrDeny);
			} catch (Exception e) {
				System.out.println("Error: Invalid input");
				return true;
			}
			handleApplication(role, iSelectedApplication, userApplications, iApproveOrDeny);
			return true;
		}
		return true;
	}

	private ArrayList<Account> decideApplication(Role role, String userIDString) {
		Integer userID = 0;
		try {
			userID = Integer.parseInt(userIDString);
		} catch (Exception e) {
			System.out.println("Error: Invalid input");
			return null;
		}
		System.out.println("User ID: " + userID);
		ArrayList<Account> userAccountApplications = role.getFileManager().getUserAccounts(role, userID,
				FileManager.ACCOUNTAPPLICATIONSFILE);
		if (userAccountApplications.size() > 0) {
			System.out.println("Which application would you like to manage?");
			MenuFormatter.displayAccountMenu(role, userAccountApplications);
		}
		return userAccountApplications;
	}

	private Account handleApplication(Role role, Integer iSelectedApplication, ArrayList<Account> userApplications,
			Integer iApproveOrDeny) {
		switch (iApproveOrDeny) {
		case 1:
			ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTSFILE);
			Account account = new Account(userApplications.get(iSelectedApplication).getAccountNumber(),
					userApplications.get(iSelectedApplication).getAccountType(),
					userApplications.get(iSelectedApplication).getUserIDs(), BigDecimal.valueOf(0.0));
			accounts.add(account);
			role.getFileManager().writeItemsToFile(accounts, FileManager.ACCOUNTSFILE);
			removeApplication(role, userApplications.get(iSelectedApplication).getAccountNumber());
			BankLogger.logMessage("info", "Account number " + account.getAccountNumber() + " was approved.\n");
			BankLogger.logWriteItems(accounts);
			return account;
		case 2:
			removeApplication(role, userApplications.get(iSelectedApplication).getAccountNumber());
			BankLogger.logMessage("info", "Account number "
					+ userApplications.get(iSelectedApplication).getAccountNumber() + " was denied.\n");
			break;
		default:
			System.out.println("Error: Invalid input");
			break;
		}
		return null;
	}

	private void removeApplication(Role role, Integer accountNumber) {
		ArrayList<Account> accountApplications = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTAPPLICATIONSFILE);
		BankLogger.logReadItems(accountApplications);
		Integer applicationIndex = -1;
		for (Account accountApplication : accountApplications) {
			if (accountApplication.getAccountNumber().equals(accountNumber)) {
				applicationIndex = accountApplications.indexOf(accountApplication);
			}
		}
		if (applicationIndex.intValue() > -1) {
			accountApplications.remove(applicationIndex.intValue());
		}
		role.getFileManager().writeItemsToFile(accountApplications, FileManager.ACCOUNTAPPLICATIONSFILE);
		BankLogger.logWriteItems(accountApplications);
	}
}