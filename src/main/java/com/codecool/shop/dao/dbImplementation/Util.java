package com.codecool.shop.dao.dbImplementation;

import com.codecool.shop.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {

    public Util() {
    }

    public ProductCategory getProductCategory(String name, String department, String description){
        return new ProductCategory(name, department, description);

    }

    public Supplier getSupplier(String name, String description){
        return new Supplier(name, description);
    }

    public Product newProduct(ResultSet rs) throws SQLException {
        Product product = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(7),
                rs.getString(6),
                getProductCategory(rs.getString(4), rs.getString(6), rs.getString(5)),
                getSupplier(rs.getString(9), rs.getString(10)));
        product.setId(rs.getInt(1));
        return product;
    }

    public Address getAddress(String country, String streetAdress, String city, String state, String zipCode){
        return new Address(country, streetAdress, city, state, zipCode);
    }
}
