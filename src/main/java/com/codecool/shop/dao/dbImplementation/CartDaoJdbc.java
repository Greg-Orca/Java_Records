package com.codecool.shop.dao.dbImplementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;

public class CartDaoJdbc implements CartDao {
    private DataSource dataSource;
    private final Util util = new Util();
    private HashMap<Product, Integer> cart = new HashMap<Product, Integer>();

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
          //  if(cart == null){createUserCart(User user);}
            cart = getAll();
            if (cart.containsKey(product)){
                updateOrderItems(product, 1);
            }else {
                String sql = "INSERT INTO order_items (cart_id, prod_id, qty) VALUES ( ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, 1);
                statement.setInt(2, product.getId());
                statement.setInt(3, 1);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOrderItems(Product product, int cart_id){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE order_items SET qty = ? WHERE cart_id = ? AND prod_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cart.get(product)+1);
            statement.setInt(2, cart_id);
            statement.setInt(3, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUserCart(User user){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO carts (user_id, total) VALUES ( ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getId());
            statement.setBigDecimal(2, BigDecimal.valueOf(sumPrice()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeUserCart(User user){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE carts (closed) SET ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, true);
            statement.setInt(1, getOpenCartId(user));

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getOpenCartId(User user){
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM carts WHERE user_id = ? AND closed= ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getId());
            statement.setBoolean(1, false);
            ResultSet rs = statement.executeQuery();
            ResultSet resultSet = statement.getGeneratedKeys();
            int result = 0;
            if(rs.next()){
                result = rs.getInt(1);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.id, products.name, price, c.name as category, c.description as category_description,\n" +
                    "                c.department as department, currency, products.description, s.name as supplier,\n" +
                    "                s.description as supplier_description, oi.qty as qty FROM products\n" +
                    "JOIN categories c on c.id = products.category_id\n" +
                    "JOIN supplier s on s.id = products.supplier_id\n" +
                    "JOIN order_items oi on products.id = oi.prod_id\n" +
                    "JOIN carts c2 on c2.id = oi.cart_id\n" +
                    "WHERE oi.cart_id = c2.id AND oi.prod_id = products.id";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();
//            ResultSet resultSet = statement.getGeneratedKeys();
            HashMap<Product, Integer> result = new HashMap<>();
            while (rs.next()){
                Product product = util.newProduct(rs);
                result.put(product, rs.getInt(11));
            }
            cart = result;
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    if (cart.containsKey(product)){
//        cart.put(product, cart.get(product) + 1);
//    } else {cart.put(product, 1);}

    @Override
    public int getItemQty() {
        return 0;
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

    }

    @Override
    public int getAllItemQty() {
        return 0;
    }

    @Override
    public void setItemQty(Product product, int i) {

    }
}
