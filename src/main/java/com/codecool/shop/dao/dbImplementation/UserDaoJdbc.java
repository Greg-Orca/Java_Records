package com.codecool.shop.dao.dbImplementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {try (Connection conn = dataSource.getConnection()) {
        updateUser(user);
        String sql =
                "INSERT INTO address (country, city, street_address, state, zip, user_id)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getAdress().getCountry());
        statement.setString(2, user.getAdress().getCity());
        statement.setString(3, user.getAdress().getStreetAdress());
        statement.setString(4, user.getAdress().getState());
        statement.setString(5, user.getAdress().getZipCode());
        statement.setInt(6, 1);
        statement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

    private void updateUser(User user) throws SQLException {try (Connection conn = dataSource.getConnection()) {
        String sql = "UPDATE shop_user SET email = ? ,first_name = ?, last_name = ? WHERE id = ?;";
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setInt(4, 1);

        user.setId(1);
        statement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }



    @Override
    public User getUser(int index) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT first_name, last_name, phone_number, email, a.country, a.street_address, a.city, " +
                    "a.state, a.zip FROM shop_user " +
                    "JOIN address a on shop_user.id = a.user_id " +
                    "WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 1);
            ResultSet rs = statement.executeQuery();
            ResultSet resultSet = statement.getGeneratedKeys();
            User result = null;
            if(rs.next()){
                Util util = new Util();
                result = new User(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4),
                        util.getAddress(rs.getString(5), rs.getString(6), rs.getString(7),
                                rs.getString(8), rs.getString(9)));
                result.setId(1);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

