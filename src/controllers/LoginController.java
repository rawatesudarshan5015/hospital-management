// src/controllers/LoginController.java

package controllers;

import models.User;

public class LoginController {

    public static String login(String username, String password) {
        User user = User.authenticate(username, password);
        if (user != null) {
            return user.getRole(); // Redirect based on role
        } else {
            System.out.println("Invalid credentials!");
            return null;
        }
    }
}
