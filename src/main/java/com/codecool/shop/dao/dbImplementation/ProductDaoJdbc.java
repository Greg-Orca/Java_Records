package com.codecool.shop.dao.dbImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    DataSource dataSource;
    private static ProductDaoJdbc instance;
    private final Util util = new Util();

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc(instance.dataSource);
        }
        return instance;
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT products.id, products.name, price, c.name as category, c.description as category_description, " +
                "c.department as department, currency, products.description, s.name as supplier, " +
                "s.description as supplier_description FROM products " +
                "JOIN categories c on c.id = products.category_id " +
                "JOIN supplier s on s.id = products.supplier_id WHERE products.id = ?";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        ResultSet resultSet = statement.getGeneratedKeys();
        Product result = null;
        if(rs.next()){
            result = new Product(rs.getString(2), rs.getBigDecimal(3), rs.getString(7),
                    rs.getString(6),
                    util.getProductCategory(rs.getString(4), rs.getString(6), rs.getString(5)),
                    util.getSupplier(rs.getString(9), rs.getString(10)));
            result.setId(rs.getInt(1));
        }
        return result;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.name, price, c.name as category, c.description as category_description, " +
                    "c.department as department, currency, products.description, s.name as supplier, " +
                    "s.description as supplier_description FROM products " +
                    "JOIN categories c on c.id = products.category_id " +
                    "JOIN supplier s on s.id = products.supplier_id ";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();
//            ResultSet resultSet = statement.getGeneratedKeys();
            List<Product> result = new ArrayList<>();
            while (rs.next()){
                Product product = util.newProduct(rs);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
