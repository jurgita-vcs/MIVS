package vcs.lt.service;

import vcs.lt.model.User;
import vcs.lt.utils.IOObjectStreamUtils;
import vcs.lt.utils.ScannerUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class UserService {
    public static User login() {
        System.out.println("Welcome! Please login.");
        while (true) {
            System.out.println();
            String username = ScannerUtils.scanString("Username: ");
            String password = ScannerUtils.scanString("Password: ");

            User user = UserService.findUser(username);
            if (user != null
                    && username.equals(user.getUserName())
                    && password.equals(user.getPassword())) {
                return user;
            }
            System.out.println("Login failed, please try again");
        }
    }

    public static User findUser(String username) {
        HashMap<String, User> users;
        try {
            users = (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
            return users.get(username);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void save(User user) {
        HashMap<String, User> allUsers = findAllUsers();
        allUsers.put(user.getUserName(), user);
        IOObjectStreamUtils.writeObjectToFile("users", allUsers);
    }

    public HashMap<String, User> findAllUsers() {
        try {
            return (HashMap<String, User>) IOObjectStreamUtils.readFirstObjectFromFile("users");
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
