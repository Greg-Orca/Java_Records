package com.codecool.shop.dao.dbImplementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {

    private CartDao cartDao;
    private ProductDao productDao;
    private UserDao userDao;


    public void setup() throws SQLException, IOException {
        try{
        DataSource dataSource = connect();
        cartDao = new CartDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource);
        userDao = new UserDaoJdbc(dataSource);

    }catch (Exception e){ throw new IOException();}}

    public void addToCart(Product product){cartDao.add(product);}

    public CartDao getCartDao() {
        return cartDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    private DataSource connect() throws SQLException, IOException {
        try{
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        InputStream input = new FileInputStream("src/main/resources/connections.properties");
        Properties prop = new Properties();
        prop.load(input);
        String dbName = prop.getProperty("database");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }catch (Exception e){
            throw new IOException(e);
        }}
}
