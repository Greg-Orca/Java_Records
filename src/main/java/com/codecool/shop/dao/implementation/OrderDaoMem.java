package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;

public class OrderDaoMem implements OrderDao {

    Cart order;
    private static OrderDaoMem instance = null;


    public OrderDaoMem() {
    }

    @Override
    public void saveOrder() {

    }

    @Override
    public void setOrder(Cart order) {
    }

    @Override
    public Cart getOrder() {
        return order;
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }
}
