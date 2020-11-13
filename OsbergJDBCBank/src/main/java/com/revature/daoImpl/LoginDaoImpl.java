package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.Account;
import com.revature.bank.Login;
import com.revature.bank.RoleServices.roleName;
import com.revature.dao.LoginDao;
import com.revature.util.DatabaseConnectionFactory;

public class LoginDaoImpl implements LoginDao {

	public static DatabaseConnectionFactory dbConFac = DatabaseConnectionFactory.getInstance();

	public List<Login> getAllLogins() throws SQLException {
		List<Login> logins = new ArrayList<Login>();
		Connection connection = dbConFac.getConnection();
		Statement statement = connection.createStatement();
		// Use executeQuery to get resultSet
		ResultSet resultSet = statement.executeQuery("select * from logins");
		Login login = null;
		while (resultSet.next()) {
			login = createLogin(resultSet);
			logins.add(login);
		}
		return logins;
	}

	public Login getLoginByUserId(Integer userID) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "select * from logins where userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, userID);
		ResultSet resultSet = preparedStatement.executeQuery();
		Login login = null;
		while (resultSet.next()) {
			login = createLogin(resultSet);
		}
		return login;
	}

	private Login createLogin(ResultSet resultSet) throws SQLException {
		Integer dbUserId = resultSet.getInt(1);
		String username = resultSet.getString(2);
		String password = resultSet.getString(3);
		roleName role = null;
		switch (resultSet.getString(4)) {
		case "CUSTOMER":
			role = roleName.CUSTOMER;
			break;
		case "EMPLOYEE":
			role = roleName.EMPLOYEE;
			break;
		case "ADMIN":
			role = roleName.ADMIN;
			break;
		default:
			break;
		}
		String givenName = resultSet.getString(5);
		return new Login(dbUserId, username, password, role, givenName);
	}

	public void insertLogin(Login login) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "insert into logins values(?,?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, login.getUserId());
		preparedStatement.setString(2, login.getUsername());
		preparedStatement.setString(3, login.getPassword());
		preparedStatement.setString(4, login.getRole().toString());
		preparedStatement.setString(5, login.getGivenName());
		// Use executeUpdate to get number of rows changed
		preparedStatement.executeUpdate();
	}

	@Override
	public void deleteUser(Integer iUserId) throws SQLException {
		Connection connection = dbConFac.getConnection();
		AccountDaoImpl adi = new AccountDaoImpl();
		ArrayList<Account> userAccounts = (ArrayList<Account>) adi.getUserAccounts(iUserId, "active");
		userAccounts.addAll((ArrayList<Account>) adi.getUserAccounts(iUserId, "passive"));
		String sqlQuery = "delete from logins where userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, iUserId);
		preparedStatement.executeUpdate();
		for (Account account : userAccounts) {
			String deleteAccountSqlQuery = "delete from accounts where not exists ( select * from accountusers where accountnumber = ?) and accountnumber = ?";
			PreparedStatement deleteAccountPreparedStatement = connection
					.prepareStatement(deleteAccountSqlQuery);
			deleteAccountPreparedStatement.setInt(1, account.getAccountNumber());
			deleteAccountPreparedStatement.setInt(2, account.getAccountNumber());
			deleteAccountPreparedStatement.executeUpdate();
		}
	}
}