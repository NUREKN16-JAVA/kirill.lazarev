package com.lazarev.usermanagemen.web;

import com.lazarev.usermanagement.User;
import com.lazarev.usermanagement.web.AddServlet;

import java.text.DateFormat;
import java.util.Date;

public class AddServletTest extends com.lazarev.usermanagemen.web.MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }
    public void testAdd() {
        Date date = new Date();
        User newUser = new User("Kirill", "Lazarev", date);
        User user = new User(new Long(1000), "Kirill", "Lazarev", date);
        getMockUserDao().expectAndReturn("create", newUser, user);

        addRequestParameter("firstName", "Kirill");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddInvalidDate() {
        Date date = new Date();
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("date", "testtestInnatest1");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}