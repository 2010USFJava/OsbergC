package com.revature.bank;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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
		}
		return iUserID;
	}
	
	Integer obtainTargetUserAccountNumber(Role role, String instructionForChoosingAccount) {
		Integer iUserID;
		if ((iUserID = obtainUserID(role)) < 0) {
			return -1;
		}
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, iUserID,
				FileManager.ACCOUNTSFILE);
		MenuFormatter.displayAccountMenu(role, userAccounts);
		System.out.println(instructionForChoosingAccount);
		String sAccountSelection = scanner.nextLine();
		Integer iAccountSelection = InputVerifier.verifyIntegerInput(sAccountSelection, 0, userAccounts.size());
		if (iAccountSelection < 0) {
			return -1;
		}
		return obtainAccountNumber(role, iAccountSelection, userAccounts);
	}
	
	Integer obtainAccountNumber (Role role, Integer iAccountSelection, ArrayList<Account> userAccounts) {
		return userAccounts.get(iAccountSelection-1).getAccountNumber();
	}
	
	Integer obtainAccountIndex(Role role, Integer iAccountNumber) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTSFILE);
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
