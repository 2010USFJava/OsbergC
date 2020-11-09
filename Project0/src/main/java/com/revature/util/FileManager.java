package com.revature.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.bank.Login;
import com.revature.bank.Role;
import com.revature.banklogger.BankLogger;

/**
 * The FileManager class contains the functionality for reading and writing to a
 * file.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class FileManager {
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	public static final String ACCOUNTS_FILE = "accounts.txt";
	public static final String ACCOUNT_APPLICATIONS_FILE = "accountApplications.txt";
	public static final String LOGINS_FILE = "logins.txt";

	/**
	 * The writeItemsToFile method writes an ArrayList to a file.
	 */
	public <T> void writeItemsToFile(ArrayList<T> items, String filename) {
		File file = new File(filename);
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(items);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The readItemsFromFile method reads an ArrayList from a file.
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> readItemsFromFile(String filename) {
		File file = new File(filename);
		ArrayList<T> items = new ArrayList<>();
		if (file.exists()) {
			try {
				objectInputStream = new ObjectInputStream(new FileInputStream(file));
				items = (ArrayList<T>) objectInputStream.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	/**
	 * The getAllLoginUserNames method reads in all Logins and gets all of the
	 * usernames associated with them.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user
	 * of the program. It contains references to non-package classes.
	 * @return ArrayList Returns an ArrayList of all the usernames.
	 */
	public ArrayList<String> getAllLoginUsernames(Role role, ArrayList<Login> logins) {
		ArrayList<String> userNameList = new ArrayList<>();
		for (Login login : logins) {
			userNameList.add(login.getUsername());
		}
		BankLogger.logReadItems(userNameList);
		return userNameList;
	}

	/**
	 * The getAllLoginUserIDs method reads in all Logins and gets all of the
	 * user IDs associated with them.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return ArrayList Returns an ArrayList of all the Login user IDs.
	 */
	public ArrayList<Integer> getAllLoginUserIDs(Role role, ArrayList<Login> logins) {
		ArrayList<Integer> userIDList = new ArrayList<>();
		for (Login login : logins) {
			userIDList.add(new Integer(login.getUserID()));
		}
		BankLogger.logReadItems(userIDList);
		return userIDList;
	}

	/**
	 * The getAllAccountNumbers method reads in all Accounts and gets all of the
	 * numbers associated with them.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return ArrayList Returns an ArrayList of the account numbers.
	 */
	public ArrayList<Integer> getAllAccountNumbers(Role role, ArrayList<Account> accounts) {
		ArrayList<Integer> accountNumberList = new ArrayList<>();
		for (Account account : accounts) {
			accountNumberList.add(account.getAccountNumber());
		}
		return accountNumberList;
	}

//	public <T> ArrayList<Integer> getAllItemNumbers(Role role, ArrayList<T> items){
//		ArrayList<Integer> itemNumberList = new ArrayList<>();
//		for(T item : items) {
//			itemNumberList.add(item.get)
//		}
//	}

	/**
	 * The getUserAccounts method reads in all Accounts and gets all of the
	 * Accounts associated with the specified user.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return ArrayList Returns an ArrayList of Accounts belonging to the user ID.
	 */
	public ArrayList<Account> getUserAccounts(Role role, Integer userID, String fileName) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile(fileName);
		BankLogger.logReadItems(accounts);
		ArrayList<Account> userAccounts = new ArrayList<>();
		for (Account account : accounts) {
			if (account.getUserIDs().contains(userID)) {
				userAccounts.add(account);
			}
		}
		return userAccounts;
	}
}
