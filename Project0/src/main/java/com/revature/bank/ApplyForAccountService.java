package com.revature.bank;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

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
		String sAccountType = scanner.nextLine();
		Integer iAccountType = InputVerifier.verifyIntegerInput(sAccountType, 0, 3);
		if (iAccountType < 0) {
			return true;
		}
		System.out.println("Is this a join account?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		String sIsJointAccount = scanner.nextLine();
		Integer iIsJointAccount = InputVerifier.verifyIntegerInput(sIsJointAccount, 0, 3);
		if (iIsJointAccount < 0) {
			return true;
		}
		ArrayList<Integer> accountUserIDs = new ArrayList<>();
		accountUserIDs.add(role.getUserID());
		switch (iIsJointAccount) {
		case 1:
			System.out.println("Please enter the user ID of the other user.");
			String sUserID = scanner.nextLine();
			Integer iUserID = InputVerifier.verifyIntegerInput(sUserID, 0, Integer.MAX_VALUE);
			if (iUserID < 0) {
				return true;
			}
			if (iUserID.intValue() == role.getUserID()) {
				System.out.println("Error: Duplicate user ID.");
				return true;
			} 
			if (!userExists(role, iUserID)) {
				return true;
			} else {
				accountUserIDs.add(iUserID);
				createAccountApplication(role, iAccountType, accountUserIDs);
			}
			break;
		case 2:
			createAccountApplication(role, iAccountType, accountUserIDs);
			break;
		default:
			System.out.println("Error: Invalid input");
			break;
		}
		return true;
	}

	private void createAccountApplication(Role role, Integer iAccountType, ArrayList<Integer> userIDs) {
		ArrayList<Account> accountApplications = role.getFileManager()
				.readItemsFromFile(FileManager.ACCOUNT_APPLICATIONS_FILE);
		BankLogger.logReadItems(accountApplications);
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		BankLogger.logReadItems(accounts);
		ArrayList<Integer> accountNumberList = role.getFileManager().getAllAccountNumbers(role, accountApplications);
		BankLogger.logReadItems(accountApplications);
		accountNumberList.addAll(role.getFileManager().getAllAccountNumbers(role, accounts));
		BankLogger.logReadItems(role.getFileManager().getAllAccountNumbers(role, accounts));
		int accountNumber = 1;
		while (accountNumberList.contains(accountNumber)) {
			accountNumber++;
		}
		switch (iAccountType) {
		case 1:
			accountApplications
					.add(new Account(accountNumber, "checking", userIDs, BigDecimal.valueOf(0.00)));
			BankLogger.logMessage("info", "User number(s) " + userIDs.toString()
					+ " applied for checking account number " + accountNumber + ".\n");
			break;
		case 2:
			accountApplications
					.add(new Account(accountNumber, "savings ", userIDs, BigDecimal.valueOf(0.00)));
			BankLogger.logMessage("info", "User number(s) " + userIDs.toString()
					+ " applied for savings account number " + accountNumber + ".\n");
			break;
		default:
			System.out.println("Error: Invalid selection");
			break;
		}
		// Adjusts display mode of the balance
		accountApplications.get(accountApplications.size()-1).getBalance().setScale(2, RoundingMode.HALF_EVEN);
		role.getFileManager().writeItemsToFile(accountApplications, FileManager.ACCOUNT_APPLICATIONS_FILE);
		BankLogger.logWriteItems(accountApplications);
	}
}
