package com.partsinventory.helper;

import com.partsinventory.model.Users;

public class Session {
    private static Session instance;
    private Users loggedInUser;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Users user) {
        this.loggedInUser = user;
    }
}