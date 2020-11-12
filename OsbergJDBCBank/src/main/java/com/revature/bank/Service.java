package com.revature.bank;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bank.RoleServices.roleName;
import com.revature.banklogger.BankLogger;
import com.revature.exception.InvalidInputException;
import com.revature.exception.NoAccountsException;
import com.revature.exception.UserDoesNotExistException;
import com.revature.util.FileManager;
import com.revature.util.InputVerifier;

/**
 * The Service class is a parent class, and follows the command design pattern.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public abstract class Service {

	String serviceName;
	static InputStream inputStream;
	static OutputStream outputStream;
	Scanner scanner = new Scanner(System.in);

	/**
	 * The performService method is abstract in order to enforce sub-classes to
	 * dynamically perform different services when called as part of the command
	 * design pattern.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
	public abstract boolean performService(Role role);

	public String getServiceName() {
		return serviceName;
	}

	Integer obtainUserID(Role role) {
		Integer iUserID = null;
		if (role.getRoleName() == roleName.CUSTOMER) {
			iUserID = role.getUserID();
		} else {
			System.out.println("Please enter the user's ID.");
			String sUserID = scanner.nextLine();
			try {
				iUserID = InputVerifier.verifyIntegerInput(sUserID, 0, Integer.MAX_VALUE);
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
				return -1;
			}
			if (!userExists(role, iUserID)) {
				throw new UserDoesNotExistException("Exception: User does not exist");
			}
		}
		return iUserID;
	}

	/**
	 * The userExists method reads in all Logins into an ArrayList, and compares the
	 * input user ID to each database user ID.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean True is returned if a match is found.
	 */
	boolean userExists(Role role, Integer iUserID) {
		ArrayList<Login> logins = role.getFileManager().readItemsFromFile(FileManager.LOGINS_FILE);
		ArrayList<Integer> userIDList = role.getFileManager().getAllLoginUserIDs(role, logins);
		return userIDList.contains(iUserID);
	}

	/**
	 * The accountExists method reads in all Accounts into an ArrayList, and
	 * compares the input account number to each database account number.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean True is returned if a match is found.
	 */
	boolean accountExists(Role role, Integer iAccountNumber) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(FileManager.ACCOUNTS_FILE);
		ArrayList<Integer> accountNumberList = role.getFileManager().getAllAccountNumbers(role, accounts);
		return accountNumberList.contains(iAccountNumber);
	}

//	<T> boolean itemOfNumberExists(Role role, Integer iItemNumber, String fileName) {
//		ArrayList<T> items = role.getFileManager().readItemsFromFile(fileName);
//		ArrayList<Integer> itemNumberList = role.getFileManager().getAllLoginUserIDs(role, items);
//	}

	/**
	 * The obtainTargetUserAccountNumber method is used to created a menu of
	 * accounts belonging to a specific customer. The user specifies which account
	 * is desired.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return Integer Returns the chosen account's number.
	 */
	Integer obtainTargetUserAccountNumber(Role role, String instructionForChoosingAccount, String fileName) {
		Integer iUserID = obtainUserID(role);
		return useUserIDToGetTargetAccount(role, instructionForChoosingAccount, fileName, iUserID);
	}

	/**
	 * The useUserIDToGetTargetAccount method reads in all accounts, finds those
	 * belonging to the iUserID, displays the accounts, and allows the user to
	 * choose one for service.
	 * <p>
	 * 
	 * @param role                          The role parameter is the wrapper class
	 *                                      identity for the user of the program. It
	 *                                      contains references to non-package
	 *                                      classes.
	 * @param instructionForChoosingAccount The instructionForChoosingAccount
	 *                                      parameter is the instruction given to
	 *                                      the user for selecting an account from
	 *                                      the displayed menu.
	 * @return Integer Returns the chosen account's number.
	 */
	Integer useUserIDToGetTargetAccount(Role role, String instructionForChoosingAccount, String fileName,
			Integer iUserID) {
		ArrayList<Account> userAccounts = role.getFileManager().getUserAccounts(role, iUserID, fileName);
		MenuFormatter.displayAccountMenu(role, userAccounts);
		if (userAccounts.isEmpty()) {
			throw new NoAccountsException("Exception: User has no accounts");
		} else {
			System.out.println(instructionForChoosingAccount);
			String sAccountSelection = scanner.nextLine();
			Integer iAccountSelection = null;
			try {
				iAccountSelection = InputVerifier.verifyIntegerInput(sAccountSelection, 0, userAccounts.size());
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
				return -1;
			}
			return userAccounts.get(iAccountSelection - 1).getAccountNumber();
		}
	}

	/**
	 * The obtainAccountIndex method reads in all accounts, and finds the account
	 * with the specified account number.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return Integer Returns the index of the account specified.
	 */
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
