package com.revature.bank_application.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserAccountData {

    // These are creating private strings that only this script can see.
    private static String userName;
    private static String password;
    private static String phoneNumber;
    private static String email;

    /*public UserAccountData()
    {
        super();
    }*/
    // This method is grabbing the Strings above and assigning them
    public UserAccountData(String userName, String password, String phoneNumber, String email){
        super();
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    // These strings are getting the string then return the proper value
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toFileString(){
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(userName)
                .append(",")
                .append(password)
                .append(",")
                .append(phoneNumber)
                .append(",")
                .append(email);

        return mutableString.toString();
    }

    // I guess that this checks to see if any data has been enter and if it has it will over right it otherwise it will return null or some default data.
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


