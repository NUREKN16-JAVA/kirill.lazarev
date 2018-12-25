package com.lazarev.usermanagemen.web;

import ua.nure.kn.dvoinikova.usermanagement.User;
import ua.nure.kn.dvoinikova.usermanagement.web.DetailsServlet;

public class DetailsServletTest extends com.lazarev.usermanagemen.web.MockServletTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    public void testBack() {
        addRequestParameter("backButton", "BACK");
        User nullUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User must not exist in session scope", nullUser);
    }
}
