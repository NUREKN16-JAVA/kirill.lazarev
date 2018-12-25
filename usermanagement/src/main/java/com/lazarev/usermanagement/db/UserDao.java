package com.lazarev.usermanagement.db;

import java.util.Collection;

import com.lazarev.usermanagement.User;

public interface UserDao {
	User create(User user) throws DatabaseExeption;
	
	void update(User user) throws DatabaseExeption;
	
	void delete(User user) throws DatabaseExeption;
	
	User find(Long id) throws DatabaseExeption;
	
	Collection findAll() throws DatabaseExeption;
	
	public void setConnectionFactory(ConnectionFactory connectionFactory);
}
