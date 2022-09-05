package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMem implements UserDao {
    private List<User> users = new ArrayList<>();
    private static UserDaoMem instance = null;

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }


    @Override
    public void add(User user) {
        user.setId(users.size()+1);
        users.add(user);
    }

    @Override
    public User getUser(int index) {
        return users.get(index);
    }


}
