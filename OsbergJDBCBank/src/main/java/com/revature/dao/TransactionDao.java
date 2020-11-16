package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bank.Transaction;

public interface TransactionDao {
	
	public void insertTransaction(Integer userId, String message) throws SQLException;
	
	public List<Transaction> getUserTransactions(Integer iUserId) throws SQLException;
}
