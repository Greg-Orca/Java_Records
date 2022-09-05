package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem implements CartDao {
    private HashMap<Product, Integer> cart = new HashMap<Product, Integer>();
    private static CartDaoMem instance = null;

    @Override
    public void add(Product product) {
            if (cart.containsKey(product)){
                cart.put(product, cart.get(product) + 1);
            } else {cart.put(product, 1);}



    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return cart;
    }

    @Override
    public int getItemQty() {
        return 0;
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    public void setItemQty(Product product, int i){
        for (Product prod: cart.keySet()){
            if (prod == product){
                cart.put(product, cart.get(product) + i);
            }
        }

    }


    public int getAllItemQty(){
        int quantity = 0;
        for (Integer qty: cart.values()){
            quantity+=qty;
        }
        return quantity;
    }

    @Override
    public float sumPrice() {
        float sum = 0;
        String price;
        for(Product product : cart.keySet()){
            price = product.getPrice();
            sum += Float.parseFloat(price.split(" ")[0])*cart.get(product);
        }
        return sum;
    }

    @Override
    public void emptyCart() {
        cart = new HashMap<>();
    }
}
