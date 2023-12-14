package com.example.mad_proj_bcsf20m525.Activities.login_signup;

import java.util.HashMap;
import java.util.Map;

public class CredentialsManager {
    private Map<String, String> validCredentials = new HashMap<>();

    public CredentialsManager() {
        // Initialize the map with pre-defined credentials
        validCredentials.put("hassan@gmail.com", "123");
        validCredentials.put("ali@yahoo.com", "786");
        validCredentials.put("abd@gmail.com", "000");
    }

    public boolean isValidUser(String email, String password) {
        if (validCredentials.containsKey(email)) {
            String correctPassword = validCredentials.get(email);
            return password.equals(correctPassword);
        }
        return false;
    }

    public boolean addUser(String email, String password) {
        if (!validCredentials.containsKey(email)) {
            validCredentials.put(email, password);
            return true;
        }
        return false;
    }
}
