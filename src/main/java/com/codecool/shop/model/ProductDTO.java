package com.codecool.shop.model;

import java.util.Currency;

public class ProductDTO {
    int id;
    String name;
    String categoryName;
    int quantity;

    String price;
    Currency currency;

    public ProductDTO(Product product, int quantity) {

        this.quantity = quantity;
        this.id = product.getId();

        this.name = product.getName();
        this.price = product.getPrice();
        this.currency = product.getDefaultCurrency();
        this.categoryName = product.getProductCategory().getName();
    }
}
