package com.example.covidavoider.singletons;

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

    private Map<String, User> users;

    private UserService() {
        users = new HashMap<>();
    }

    public void register(User user) {
        users.put(user.username, user);
    }

    public boolean login(String username, String password) {
        if(users.containsKey(username)) {
            User user = users.get(username);
            return user.password.equals(password);
        }
        return false;
    }
}
