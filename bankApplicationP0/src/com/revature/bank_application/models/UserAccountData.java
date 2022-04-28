package com.revature.bank_application.models;

public class UserAccountData {

    private String userName;
    private String password;
    private String phoneNumber;
    private String email;

    public UserAccountData()
    {
        super();
    }
    public UserAccountData(String userName, String password, String phoneNumber, String email){
        super();
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserAccountData{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


