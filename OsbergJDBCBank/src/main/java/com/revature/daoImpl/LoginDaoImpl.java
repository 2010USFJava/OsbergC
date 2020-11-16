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
import com.revature.exception.InvalidInputException;
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

	@Deprecated
	public Login getLoginByUsername(String username) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "select * from logins where username = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		Login login = null;
		while (resultSet.next()) {
			login = createLogin(resultSet);
		}
		return login;
	}

	/**
	 * The getLoginById method returns a login for a specified username or userid.
	 * <p>
	 * 
	 * @param The usernameOrUserid parameter can handle either usernames or userids
	 *            in order to find the login.
	 * @return Returns the login for the specified username or userid.
	 * @throws SQLException
	 * @throws InvalidInputException
	 */
	public Login getLoginById(String usernameOrUserid) throws SQLException {
		Connection connection = dbConFac.getConnection();
		Login login = null;
		try {
			String sqlQuery = "select * from logins where userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			Integer iId = Integer.parseInt(usernameOrUserid);
			preparedStatement.setInt(1, iId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				login = createLogin(resultSet);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String sqlQuery = "select * from logins where username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, usernameOrUserid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				login = createLogin(resultSet);
			}
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
		String sqlQuery = "insert into logins (username, passwrd, rolename, givenname) values (?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, login.getUsername());
		preparedStatement.setString(2, login.getPassword());
		preparedStatement.setString(3, login.getRole().toString());
		preparedStatement.setString(4, login.getGivenName());
		// Use executeUpdate to get number of rows changed
		preparedStatement.executeUpdate();
	}

	@Override
	public boolean deleteUser(Integer iUserId) throws SQLException {
		Connection connection = dbConFac.getConnection();
		AccountDaoImpl adi = new AccountDaoImpl();
		String checkUserSqlQuery = "select * from logins where userid = ?";
		PreparedStatement checkUserPreparedStatement = connection.prepareStatement(checkUserSqlQuery);
		checkUserPreparedStatement.setInt(1, iUserId);
		ResultSet resultSet = checkUserPreparedStatement.executeQuery();
		if (resultSet.isBeforeFirst()) {
			ArrayList<Account> userAccounts = (ArrayList<Account>) adi.getUserAccounts(iUserId, "active");
			userAccounts.addAll((ArrayList<Account>) adi.getUserAccounts(iUserId, "passive"));
			String sqlQuery = "delete from logins where userid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, iUserId);
			preparedStatement.executeUpdate();
			for (Account account : userAccounts) {
				String deleteAccountSqlQuery = "delete from accounts where not exists ( select * from accountusers where accountnumber = ?) and accountnumber = ?";
				PreparedStatement deleteAccountPreparedStatement = connection.prepareStatement(deleteAccountSqlQuery);
				deleteAccountPreparedStatement.setInt(1, account.getAccountNumber());
				deleteAccountPreparedStatement.setInt(2, account.getAccountNumber());
				deleteAccountPreparedStatement.executeUpdate();
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateUser(Integer iUserId, String name) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "update logins set givenname = ? where userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, iUserId);
		preparedStatement.executeUpdate();
	}
}