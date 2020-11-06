package com.revature.bank;

import java.util.ArrayList;
import java.math.BigDecimal;

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

	private ArrayList<Account> createAccountApplication(Role role, String accountType, ArrayList<Integer> userIDs) {
		ArrayList<Account> accountApplications = role.getFileManager().readItemsFromFile("accountApplications.txt");
		BankLogger.logReadItems(accountApplications);
		int choice;
		try {
			choice = Integer.parseInt(accountType);
		} catch (Exception e) {
			System.out.println("Error: Invalid selection");
			return accountApplications;
		}
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		BankLogger.logReadItems(logins);
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile("accounts.txt");
		BankLogger.logReadItems(accounts);
		ArrayList<Integer> loginUserIDs = role.getFileManager().getAllLoginUserIDs(role, logins);
		for (Integer i : userIDs) {
			if (loginUserIDs.contains(i)) {

			} else {
				System.out.println("Error: user ID " + i + " was not found.");
				return accountApplications;
			}
		}
		ArrayList<Integer> accountNumberList = role.getFileManager().getAllAccountNumbers(role, accountApplications);
		BankLogger.logReadItems(accountApplications);
		accountNumberList.addAll(role.getFileManager().getAllAccountNumbers(role, accounts));
		BankLogger.logReadItems(role.getFileManager().getAllAccountNumbers(role, accounts));
		int accountNumber = 1;
		while (accountNumberList.contains(accountNumber)) {
			accountNumber++;
		}
		switch (choice) {
		case 1:
			accountApplications.add(new Account(new Integer(accountNumber), "checking", userIDs, new BigDecimal(0.0)));
			BankLogger.logMessage("info", "User number(s) " + userIDs.toString()
					+ " applied for checking account number " + accountNumber + ".\n");
			break;
		case 2:
			accountApplications.add(new Account(new Integer(accountNumber), "savings", userIDs, new BigDecimal(0.0)));
			BankLogger.logMessage("info", "User number(s) " + userIDs.toString()
					+ " applied for savings account number " + accountNumber + ".\n");
			break;
		default:
			System.out.println("Error: Invalid selection");
			break;
		}
		role.getFileManager().writeItemsToFile(accountApplications, "accountApplications.txt");
		BankLogger.logWriteItems(accountApplications);
		return accountApplications;
	}
}
