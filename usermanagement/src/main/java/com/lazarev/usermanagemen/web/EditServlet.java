package com.lazarev.usermanagemen.web;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.xml.bind.ValidationException;
import javax.servlet.http.HttpServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lazarev.usermanagement.User;
import com.lazarev.usermanagement.db.DatabaseExeption;

public class EditServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("okButton") != null) {
            doOk(req, resp);
        } else if (req.getParameter("cancelButton") != null) {
            doCancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);

    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        try {
            user = getUser(req);
        } catch (ValidationException e1) {
            req.setAttribute("error", e1.getMessage());
            showPage(req, resp);
            return;
        }
        try {
            processUser(user);
        } catch (DatabaseExeption e) {
            e.printStackTrace();
            new ServletException(e);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);

    }

    private User getUser(HttpServletRequest req) throws ValidationException {
        User user = new User();
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("dateOfBirth");
        if (firstName == null) {
            throw new ValidationException("First name is empty");
        }
        if (lastName == null) {
            throw new ValidationException("Last name is empty");
        }
        if (dateStr == null) {
            throw new ValidationException("Date is empty");
        }

        if (idStr != null) {
            user.setiD(new Long(idStr));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            user.setDateOfBirthd(DateFormat.getDateInstance().parse(dateStr));
        } catch (ParseException e) {
            throw new ValidationException("Date format is incorrect");
        }
        return user;
    }

    protected void processUser(User user) throws DatabaseExeption {
        DaoFactory.getInstance().getUserDao().update(user);
    }
}
