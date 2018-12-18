package com.lazarev.usermanagement;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long 	iD;				
	private String 	firstName;			
	private String 	lastName;			
	private Date 	dateOfBirthd;
	public Long getiD() {
		return iD;
	}
	public void setiD(Long iD) {
		this.iD = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirthd() {
		return dateOfBirthd;
	}
	public void setDateOfBirthd(Date dateOfBirthd) {
		this.dateOfBirthd = dateOfBirthd;
	}
	public Object getFullName() {
		return getLastName() + ", " +  getFirstName();
	}
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(getDateOfBirthd());
		int year = calendar.get(Calendar.YEAR);
		return currentYear - year;
	}
	
}
