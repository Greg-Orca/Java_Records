package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/cart/get/"})
public class GetCartItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       CartDao cartDao = CartDaoMem.getInstance();
        HashMap<Product, Integer> products = cartDao.getAll();
        JsonObject json = new JsonObject();
        Gson gson = new Gson();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products.keySet()){
            int qty = products.get(product);
            ProductDTO productDTO = new ProductDTO(product, qty);

            productDTOList.add(productDTO);

       }
        String out = gson.toJson(productDTOList);

       resp.getOutputStream().print(String.valueOf(out));

    }
}
