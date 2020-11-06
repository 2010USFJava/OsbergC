package com.revature.bank;

import java.util.ArrayList;

import com.revature.banklogger.BankLogger;

public class ApplyForAccountService extends Service {

	public ApplyForAccountService() {
		super();
		serviceName = "Apply for an Account";
	}

	@Override
	public boolean performService(Role role) {
		System.out.println("Please specify what kind of account you would like:");
		System.out.println("1. Checking Account");
		System.out.println("2. Savings Account");
		String accountType = scanner.nextLine();
		System.out.println("Is this a join account?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		String isJointAccount = scanner.nextLine();
		ArrayList<Integer> userIDs = new ArrayList<>();
		Integer iIsJointAccount = -1;
		try {
			iIsJointAccount = Integer.parseInt(isJointAccount);
		} catch (Exception e) {
			System.out.println("Error: Invalid input");
			return true;
		}
		switch (iIsJointAccount) {
		case 1:
			userIDs.add(role.getUserID());
			System.out.println("Please enter the user ID of the other user.");
			String userIDString = scanner.nextLine();
			Integer userID;
			try {
				userID = Integer.parseInt(userIDString);
			} catch (Exception e) {
				System.out.println("Error: Invalid input");
				return true;
			}
			if (userID.intValue() == role.getUserID()) {
				System.out.println("Error: Duplicate user ID.");
				return true;
			}
			userIDs.add(userID);
			createAccountApplication(role, accountType, userIDs);
			break;
		case 2:
			userIDs.add(role.getUserID());
			createAccountApplication(role, accountType, userIDs);
			break;
		default:
			System.out.println("Error: Invalid input");
			break;
		}
		return true;
	}

	private ArrayList<AccountApplication> createAccountApplication(Role role, String accountType,
			ArrayList<Integer> userIDs) {
		ArrayList<AccountApplication> accountApplications = role.getFileManager()
				.readItemsFromFile("accountApplications.txt");
		BankLogger.logMessage("info", "Account Applications read in:\n" + accountApplications + "\n");
		int choice;
		try {
			choice = Integer.parseInt(accountType);
		} catch (Exception e) {
			System.out.println("Error: Invalid selection");
			return accountApplications;
		}
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		BankLogger.logMessage("info", "Logins read in:\n" + logins + "\n");
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile("accounts.txt");
		BankLogger.logMessage("info", "Accounts read in:\n" + accounts + "\n");
		ArrayList<Integer> loginUserIDs = role.getFileManager().getAllLoginUserIDs(role, logins);
		for (Integer i : userIDs) {
			if (loginUserIDs.contains(i)) {

			} else {
				System.out.println("Error: user ID " + i + " was not found.");
				return accountApplications;
			}
		}
		ArrayList<Integer> accountNumberList = role.getFileManager().getAllApplicationAccountNumbers(role,
				accountApplications);
		BankLogger.logMessage("info", "Account applications read in:\n" + accountApplications + "\n");
		accountNumberList.addAll(role.getFileManager().getAllAccountNumbers(role, accounts));
		BankLogger.logMessage("info",
				"Accounts read in:\n" + role.getFileManager().getAllAccountNumbers(role, accounts) + "\n");
		int accountNumber = 1;
		while (accountNumberList.contains(accountNumber)) {
			accountNumber++;
		}
		switch (choice) {
		case 1:
			accountApplications.add(new AccountApplication(userIDs, "checking", new Integer(accountNumber)));
			BankLogger.logMessage("info", "User number(s) " + userIDs.toString()
					+ " applied for checking account number " + accountNumber + ".\n");
			break;
		case 2:
			accountApplications.add(new AccountApplication(userIDs, "savings", new Integer(accountNumber)));
			BankLogger.logMessage("info", "User number(s) " + userIDs.toString()
					+ " applied for savings account number " + accountNumber + ".\n");
			break;
		default:
			System.out.println("Error: Invalid selection");
			break;
		}
		role.getFileManager().writeItemsToFile(accountApplications, "accountApplications.txt");
		BankLogger.logMessage("info", "Account Applications written to file:\n" + accountApplications + "\n");
		return accountApplications;
	}
}
