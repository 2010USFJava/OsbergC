package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.Transaction;
import com.revature.dao.TransactionDao;
import com.revature.util.DatabaseConnectionFactory;

public class TransactionDaoImpl implements TransactionDao {

	public static DatabaseConnectionFactory dbConFac = DatabaseConnectionFactory.getInstance();

	@Override
	public void insertTransaction(Integer userId, String message) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "insert into transactions (userid,message) values (?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, userId);
		preparedStatement.setString(2, message.substring(0, message.length() - 1));
		preparedStatement.executeUpdate();
	}

	@Override
	public List<Transaction> getUserTransactions(Integer iUserId) throws SQLException {
		ArrayList<Transaction> transactions = new ArrayList<>();
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "select * from transactions where userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, iUserId);
		ResultSet resultSet = preparedStatement.executeQuery();
		Transaction transaction = null;
		while (resultSet.next()) {
			transaction = new Transaction(resultSet.getTimestamp(2), resultSet.getInt(3), resultSet.getString(4));
			transactions.add(transaction);
		}
		return transactions;
	}
}
