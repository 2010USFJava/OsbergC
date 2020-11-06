package com.revature.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;

public class FileManager {
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;

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

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> readItemsFromFile(String filename) {
		File file = new File(filename);
		ArrayList<T> items = new ArrayList<T>();
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
	
	public ArrayList<String> getAllLoginUsernames(Role role, ArrayList<Login> logins) {
		//ArrayList<Login> logins = role.getFileManager().readItemsFromFile("logins.txt");
		ArrayList<String> usernameList = new ArrayList<String>();
		for(Login l : logins) {
			usernameList.add(l.getUsername());
		}
		return usernameList;
	}
	
	public ArrayList<Integer> getAllLoginUserIDs(Role role, ArrayList<Login> logins) {
		ArrayList<Integer> userIDList = new ArrayList<Integer>();
		for(Login l : logins) {
			userIDList.add(new Integer(l.getUserID()));
		}
		return userIDList;
	}
	
	public ArrayList<Integer> getAllAccountNumbers(Role role, ArrayList<Account> accounts) {
		ArrayList<Integer> accountNumberList = new ArrayList<Integer>();
		for(Account a : accounts) {
			accountNumberList.add(new Integer(a.getAccountNumber()));
		}
		return accountNumberList;
	}
	
	public ArrayList<Account> getUserAccounts(Role role, Integer userID) {
		ArrayList<Account> accounts = role.getFileManager().readItemsFromFile("accounts.txt");
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
