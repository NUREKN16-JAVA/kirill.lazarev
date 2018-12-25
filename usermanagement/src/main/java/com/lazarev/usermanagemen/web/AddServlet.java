package com.lazarev.usermanagemen.web;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lazarev.usermanagement.User;
import com.lazarev.usermanagement.db.DaoFactory;
import com.lazarev.usermanagement.db.DatabaseExeption;

class AddServlet extends EditServlet {

    public AddServlet() {
    }

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void processUser(User user) throws DatabaseExeption {
        DaoFactory.getInstance().getUserDao().create(user);
    }
}
