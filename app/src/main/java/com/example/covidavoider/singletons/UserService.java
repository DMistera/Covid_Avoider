package com.example.covidavoider.singletons;

import com.example.covidavoider.daos.UserDao;
import com.example.covidavoider.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private static UserService instance;
    public static void create() {
        instance = new UserService();
    }
    public static UserService getInstance() {
        return instance;
    }

    private UserDao userDao;
    private User currentUser;

    private UserService() {
        userDao = DatabaseService.getInstance().getDatabase().userDao();
    }

    public void register(User user) {
        userDao.insertAll(user);
    }

    public boolean usernameExists(String username) {
        User user = userDao.findByName(username);
        return user != null;
    }

    public boolean login(String username, String password) {
        User user = userDao.findByName(username);
        if(user != null) {
             if(user.password.equals(password)) {
                 currentUser = user;
                 return true;
             }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
