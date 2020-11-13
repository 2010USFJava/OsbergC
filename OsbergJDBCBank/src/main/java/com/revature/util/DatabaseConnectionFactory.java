package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {

	private static DatabaseConnectionFactory dbConFac = null;

	private DatabaseConnectionFactory() {
		super();
	}

	public static synchronized DatabaseConnectionFactory getInstance() {
		if (dbConFac == null) {
			dbConFac = new DatabaseConnectionFactory();
		}
		return dbConFac;
	}

	public Connection getConnection() {
		Connection connection = null;
		Properties prop = new Properties();
//		String url= "jdbc:postgresql://revature-osberg.cmbmxlvbcdgh.us-east-2.rds.amazonaws.com:5432/postgres";
//		String username="protossarchon";
//		String password="poweroverwhelming";

		try {
			prop.load(new FileReader("database.properties"));
			connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
