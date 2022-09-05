package com.codecool.shop.dao.dbImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM categories " +
                    "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
//            ResultSet resultSet = statement.getGeneratedKeys();
            ProductCategory result = null;
            while (rs.next()){
                result = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
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
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM categories";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();
//            ResultSet resultSet = statement.getGeneratedKeys();
            List<ProductCategory> result = new ArrayList<>();
            while (rs.next()){
                result.add(new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
