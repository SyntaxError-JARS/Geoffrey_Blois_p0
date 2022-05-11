package com.revature.bank_application.web.dto;

public class LoginCreds {

    private String email;
    private String password;

    // Jackson Requires a no arg arumente.

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
