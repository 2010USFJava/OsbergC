package com.revature.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;

public class DecideAccountService extends Service {

	public DecideAccountService() {
		super();
		serviceName = "Approve/Deny an Account Application";
	}

	@Override
	public boolean performService(Role role) {
		System.out.println("Please enter the user's ID number.");
		String userIDString = scanner.nextLine();
		ArrayList<AccountApplication> userApplications = decideApplication(role, userIDString);
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

	private ArrayList<AccountApplication> decideApplication(Role role, String userIDString) {
		Integer userID = new Integer(0);
		try {
			userID = new Integer(Integer.parseInt(userIDString));
		} catch (Exception e) {
			System.out.println("Error: Invalid input");
			return null;
		}
		System.out.println("User ID: " + userID);
		ArrayList<AccountApplication> accountApplications = role.getFileManager()
				.readItemsFromFile("accountApplications.txt");
		BankLogger.logMessage("info", "Account applications read in:\n" + accountApplications + "\n");
		ArrayList<AccountApplication> userAccountApplications = new ArrayList<AccountApplication>();
		for (AccountApplication accountApplication : accountApplications) {
			if (accountApplication.getUserIDs().contains(userID)) {
				userAccountApplications.add(accountApplication);
			}
		}
		System.out.println("Which application would you like to manage?");
		System.out.println("\tAccount Number\tAccount Type");
		for (AccountApplication application : userAccountApplications) {
			System.out.println((userAccountApplications.indexOf(application) + 1) + ".\t"
					+ application.getAccountNumber() + "\t\t" + application.getAccountType());
		}
		return userAccountApplications;
	}

	private Account handleApplication(Role role, Integer iSelectedApplication,
			ArrayList<AccountApplication> userApplications, Integer iApproveOrDeny) {
		switch (iApproveOrDeny) {
		case 1:
			ArrayList<Account> accounts = role.getFileManager().readItemsFromFile("accounts.txt");
			Account account = new Account(userApplications.get(iSelectedApplication).getAccountNumber(),
					userApplications.get(iSelectedApplication).getAccountType(),
					userApplications.get(iSelectedApplication).getUserIDs(), new BigDecimal(0.0));
			accounts.add(account);
			role.getFileManager().writeItemsToFile(accounts, "accounts.txt");
			removeApplication(role, userApplications.get(iSelectedApplication).getAccountNumber());
			BankLogger.logMessage("info", "Account number " + account.getAccountNumber() + " was approved.\n");
			BankLogger.logMessage("info", "Accounts written to file:\n" + accounts + "\n");
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
		ArrayList<AccountApplication> accountApplications = role.getFileManager()
				.readItemsFromFile("accountApplications.txt");
		BankLogger.logMessage("info", "Account applications read in:\n" + accountApplications + "\n");
		Integer applicationIndex = new Integer(-1);
		for (AccountApplication accountApplication : accountApplications) {
			if (accountApplication.getAccountNumber().equals(accountNumber)) {
				applicationIndex = new Integer(accountApplications.indexOf(accountApplication));
			}
		}
		if (applicationIndex.intValue() > -1) {
			accountApplications.remove(applicationIndex.intValue());
		}
		role.getFileManager().writeItemsToFile(accountApplications, "accountApplications.txt");
		BankLogger.logMessage("info", "Account applications written to file:\n" + accountApplications + "\n");
	}
}
