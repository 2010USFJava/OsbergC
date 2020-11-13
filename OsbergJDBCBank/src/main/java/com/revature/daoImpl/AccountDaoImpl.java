package com.revature.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.Account;
import com.revature.dao.AccountDao;
import com.revature.util.DatabaseConnectionFactory;

public class AccountDaoImpl implements AccountDao {

	public static DatabaseConnectionFactory dbConFac = DatabaseConnectionFactory.getInstance();

	@Override
	public List<Account> getAllAccounts() throws SQLException {
		ArrayList<Account> accounts = new ArrayList<>();
		Connection connection = dbConFac.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from accounts");
		Account account = null;
		while (resultSet.next()) {
			account = createAccount(resultSet, connection);
			accounts.add(account);
		}
		return accounts;
	}

	@Override
	public Account getAccountByAccountNumber(Integer accountNumber) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "select * from accounts where accountnumber = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		Account account = null;
		while (resultSet.next()) {
			account = createAccount(resultSet, connection);
		}
		return account;
	}

	private Account createAccount(ResultSet resultSet, Connection connection) throws SQLException {
		Integer accountNumber = resultSet.getInt(1);
		String accountType = resultSet.getString(2);
		ArrayList<Integer> userIds = new ArrayList<>();
		BigDecimal balance = resultSet.getBigDecimal(3);
		String sqlQuery = "select * from accountusers where accountnumber = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, accountNumber);
		ResultSet accountNumberResultSet = preparedStatement.executeQuery();
		while (accountNumberResultSet.next()) {
			Integer iUserId = accountNumberResultSet.getInt(2);
			userIds.add(iUserId);
		}
		return new Account(accountNumber, accountType, userIds, balance);
	}

	@Override
	public void insertAccount(Account account) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "insert into accounts values(?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, account.getAccountNumber());
		preparedStatement.setString(2, account.getAccountType());
		preparedStatement.setBigDecimal(3, account.getBalance());
		preparedStatement.executeUpdate();

		for (Integer iUserId : account.getUserIDs()) {
			String accountUsersSqlQuery = "insert into accountusers values(?,?)";
			preparedStatement = connection.prepareStatement(accountUsersSqlQuery);
			preparedStatement.setInt(1, account.getAccountNumber());
			preparedStatement.setInt(2, iUserId);
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public List<Account> getUserAccounts(Integer iUserId, String status) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "select * from accountusers where userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, iUserId);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<Account> userAccounts = new ArrayList<>();
		while (resultSet.next()) {
			String getAccountsSqlQuery = "select * from accounts where accountnumber = ? and status = ?";
			PreparedStatement getAccountsPreparedStatement = connection.prepareStatement(getAccountsSqlQuery);
			getAccountsPreparedStatement.setInt(1, resultSet.getInt(1));
			getAccountsPreparedStatement.setString(2, status);
			ResultSet getAccountsResultSet = getAccountsPreparedStatement.executeQuery();
			while (getAccountsResultSet.next()) {
				userAccounts.add(createAccount(getAccountsResultSet, connection));
			}
		}
		return userAccounts;
	}

	@Override
	public void deleteAccount(Integer iAccountNumber) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "delete from accounts where accountnumber = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, iAccountNumber);
		preparedStatement.executeUpdate();
	}
}