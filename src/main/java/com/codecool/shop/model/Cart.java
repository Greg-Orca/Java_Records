package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
    private float sumPrice;

    public Cart(HashMap<Product, Integer> cart) {
        this.cart = cart;
        this.sumPrice = setSumPrice();
    }

    public int size(){return cart.size();}

    public float setSumPrice() {
        float sum = 0;
        String price;
        for(Product product : cart.keySet()){
            price = product.getPrice();
            sum += Float.parseFloat(price.split(" ")[0])*cart.get(product);
        }
        return sum;
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        for ( Product product: cart.keySet()){
            products.add(product);
            System.out.println(product.getName());
        }
        return products;
    }
}
