package com.lazarev.usermanagement.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DaoFactoryTest {

	@Test
	void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instanse is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("DaoFactory instanse is null", userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
