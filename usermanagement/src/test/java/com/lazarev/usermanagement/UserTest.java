package com.lazarev.usermanagement;


import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	private User user;
	private Date dateOfBirthd;

	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.JUNE, 21);
		dateOfBirthd = calendar.getTime();
	}

	public void testGetFullName() {
		user.setFirstName("Kirill");
		user.setLastName("Lazarev");
		assertEquals("Lazarev, Kirill", user.getFullName());
	}
	public void testGetAge() {
		user.setDateOfBirthd(dateOfBirthd);
		assertEquals(2018 - 1999, user.getAge());
	}
}
