package com.revature.bank;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

public abstract class Service {

	String serviceName;
	static InputStream inputStream;
	static OutputStream outputStream;
	Scanner scanner = new Scanner(System.in);

	public abstract boolean performService(Role role);

	public String getServiceName() {
		return serviceName;
	}

	Integer obtainUserID(Role role) {
		Integer iUserID;
		if (role.getRoleName() == roleName.CUSTOMER) {
			iUserID = role.getUserID();
		} else {
			System.out.println("Please enter the user's ID.");
			String sUserID = scanner.nextLine();
			try {
				iUserID = Integer.parseInt(sUserID);
			} catch (Exception e) {
				System.out.println("Error: Invalid input");
				return -1;
			}
			if (!userExists(role, iUserID)) {
				System.out.println("Error: User does not exist");
				return -1;
			}
		}
		return iUserID;
	}

	boolean userExists(Role role, Integer iUserID) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile(FileManager.LOGINS_FILE);
		ArrayList<Integer> userIDList = role.getFileManager().getAllLoginUserIDs(role, logins);
		return userIDList.contains(iUserID);
	}

	boolean accountExists(Role role, Integer iAccountNumber) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		ArrayList<Integer> accountNumberList = role.getFileManager().getAllAccountNumbers(role, accounts);
		return accountNumberList.contains(iAccountNumber);
	}

//	<T> boolean itemOfNumberExists(Role role, Integer iItemNumber, String fileName) {
//		ArrayList<T> items = role.getFileManager().readItemsFromFile(fileName);
//		ArrayList<Integer> itemNumberList = role.getFileManager().getAllLoginUserIDs(role, items);
//	}

	Integer obtainTargetUserAccountNumber(Role role, String instructionForChoosingAccount, String fileName) {
		Integer iUserID;
		if ((iUserID = obtainUserID(role)) < 0) {
			return -1;
		}
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, iUserID, fileName);
		MenuFormatter.displayAccountMenu(role, userAccounts);
		if (userAccounts.isEmpty()) {
			System.out.println("The user has no accounts.");
			return -1;
		} else {
			System.out.println(instructionForChoosingAccount);
			String sAccountSelection = scanner.nextLine();
			Integer iAccountSelection = InputVerifier.verifyIntegerInput(sAccountSelection, 0, userAccounts.size());
			if (iAccountSelection < 0) {
				return -1;
			}
			return userAccounts.get(iAccountSelection - 1).getAccountNumber();
		}
	}

	Integer obtainAccountIndex(Role role, Integer iAccountNumber, String fileName) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(fileName);
		BankLogger.logReadItems(accounts);
		Integer selectedAccountIndex = null;
		for (Account account : accounts) {
			if (account.getAccountNumber().equals(iAccountNumber)) {
				selectedAccountIndex = accounts.indexOf(account);
			}
		}
		return selectedAccountIndex;
	}
}
