package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.dbImplementation.CartDaoJdbc;
import com.codecool.shop.dao.dbImplementation.DataBaseManager;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
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
import java.util.HashMap;
import java.util.List;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
        CartDao cartDataStore = CartDaoMem.getInstance();
        DataBaseManager dataBaseManager = new DataBaseManager();

        CartDao cartDao = null;
        try {
            dataBaseManager.setup();
            cartDao = dataBaseManager.getCartDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HashMap<Product, Integer> asd = new HashMap<Product, Integer>();
        asd = cartDao.getAll();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", cartDao.getAll());
        context.setVariable("fullPrice", 30.00);
//        context.setVariable("quantity", cartDataStore.getAll().values());
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/cart.html", context, resp.getWriter());
    }

}

