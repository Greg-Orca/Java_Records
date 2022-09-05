package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/cart/change"})
public class CartChange extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productDetails = req.getReader().lines().collect(Collectors.joining());
        JsonObject jsonObject = new JsonParser().parse(productDetails).getAsJsonObject();
        String id = jsonObject.get("id").toString();
        String plusMinus = jsonObject.get("plusMinus").toString();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();

        cartDataStore.setItemQty(productDataStore.find(Integer.parseInt(id)),Integer.parseInt(plusMinus));

    }


}
