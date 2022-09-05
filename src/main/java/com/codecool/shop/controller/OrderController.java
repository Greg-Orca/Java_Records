package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.dbImplementation.DataBaseManager;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = UserDaoMem.getInstance();

        DataBaseManager dm = new DataBaseManager();
        UserDao userDao1 = null;

        try {
            dm.setup();
            userDao1 = dm.getUserDao();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("order", userDao1.getUser(1));
        engine.process("product/order.html", context, resp.getWriter());
    }
}
