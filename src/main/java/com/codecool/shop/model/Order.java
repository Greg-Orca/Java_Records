package com.codecool.shop.model;

import java.util.List;

public class Order {
    List<Cart> order;

    public Order(List<Cart> order) {
        this.order = order;
    }

}
