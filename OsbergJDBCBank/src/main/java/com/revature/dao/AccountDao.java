package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bank.Account;

public interface AccountDao {
	
	public List<Account> getAllAccounts() throws SQLException;

	public void insertAccount(Account Account) throws SQLException;

	public Account getAccountByAccountNumber(Integer accountNumber) throws SQLException;

	public List<Account> getUserAccounts(Integer iUserId, String status) throws SQLException;
	
	public void deleteAccount(Integer iAccountNumber) throws SQLException;
}
