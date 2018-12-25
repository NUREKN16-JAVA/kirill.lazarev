package com.lazarev.usermanagemen.web;

import com.lazarev.usermanagement.User;
import com.lazarev.usermanagemen.web.EditServlet;
import com.lazarev.usermanagemen.web.MockServletTestCase;

import java.text.DateFormat;
import java.util.Date;

public class EditServletTest extends MockServletTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        createServlet(EditServlet.class);    }

    public void testEdit() {
        Date date = new Date();
        User user = new User(new Long(1000), "Kirill", "Lazarev", date);
        getMockUserDao().expect("update", user);

        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    public void testEditEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyLastName() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditEmptyDate() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testEditInvalidDate() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "Kirill");
        addRequestParameter("lastName", "Lazarev");
        addRequestParameter("date", "testtestInnatest1");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

}