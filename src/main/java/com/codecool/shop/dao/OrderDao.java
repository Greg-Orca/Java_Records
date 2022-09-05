package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;

public interface OrderDao {
    void saveOrder();
    void setOrder(Cart order);
    Cart getOrder();
}
