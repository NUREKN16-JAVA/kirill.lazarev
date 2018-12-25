package com.lazarev.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String driver;
	private String url;
	private String password;
	private String user;
	
	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.password = password;
		this.url = url;
		this.user = user;
	}

	public Connection createConnection() throws DatabaseExeption {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseExeption();
		}
	}

}
