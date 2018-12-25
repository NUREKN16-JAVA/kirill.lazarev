package com.lazarev.usermanagement.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Date;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lazarev.usermanagement.User;

import junit.framework.TestCase;

public class HsqldbUserDaoTest extends TestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	protected void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);
	}

	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Kirill");
			user.setLastName("Lazarev");
			user.setDateOfBirthd(new Date());
			assertNull(user.getiD());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getiD());
		} catch (DatabaseExeption e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}
	
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Colleclion is null", collection);
			assertEquals("Colleclion size.", 2, collection.size());
		} catch (DatabaseExeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("userDataSet.xml"));
		return dataSet;
	}
}
