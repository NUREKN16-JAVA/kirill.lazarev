package com.lazarev.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import com.lazarev.usermanagement.User;

class HsqldbUserDao implements UserDao {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, last, name, dateofbirthd FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lasrname, DateOfBirthd) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;

	public HsqldbUserDao() {
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}



	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}



	public User create(User user) throws DatabaseExeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(1, user.getLastName());
			statement.setDate(3, (java.sql.Date) new Date(user.getDateOfBirthd().getTime()));
			int n = statement.executeUpdate();
			if (n != 1)
			{
				throw new DatabaseExeption("Number of inserted rows: " + n); 
			}
			CallableStatement callableStatement = connection.prepareCall("call INDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if(keys.next())
			{
				user.setiD(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
	}

	public void update(User user) throws DatabaseExeption {
		// TODO Auto-generated method stub

	}

	public void delete(User user) throws DatabaseExeption {
		// TODO Auto-generated method stub

	}

	public User find(Long id) throws DatabaseExeption {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection findAll() throws DatabaseExeption {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setiD(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthd(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
		return result;
	}

}
