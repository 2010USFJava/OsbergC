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
		String line = scanner.nextLine();
		createAccountApplication(role, line);
		return true;
	}
	
	private ArrayList<AccountApplication> createAccountApplication(Role role, String line) {
		ArrayList<AccountApplication> accountApplications =
				role.getFileManager().readItemsFromFile("accountApplication.txt");
		// Default account applications
//		accountApplications.add(new AccountApplication(3, "checking", "approved"));
		System.out.println(accountApplications.toString());
		int choice;
		try {
			choice = Integer.parseInt(line);
		} catch (Exception e) {
			System.out.println("Error: Invalid selection");
			return accountApplications;
		}
		switch (choice) {
		case 1:
			accountApplications.add(new AccountApplication(role.getUserID(), "checking", "pending"));
			BankLogger.logMessage("info", "User number " + role.getUserID() +
					" applied for a checking account.");
			break;
		case 2:
			accountApplications.add(new AccountApplication(role.getUserID(), "savings", "pending"));
			BankLogger.logMessage("info", "User number " + role.getUserID() +
					" applied for a savings account.");
			break;
		default:
			System.out.println("Error: Invalid selection");
			break;
		}
		role.getFileManager().writeItemsToFile(accountApplications, "accountApplications.txt");
		return accountApplications;
	}
}
