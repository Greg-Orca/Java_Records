package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;

public interface CartDao {

    void add(Product product);

    HashMap<Product, Integer> getAll();

    public int getItemQty();

    float sumPrice();
    void emptyCart();
    public int getAllItemQty();

    public void setItemQty(Product product, int i);
}
