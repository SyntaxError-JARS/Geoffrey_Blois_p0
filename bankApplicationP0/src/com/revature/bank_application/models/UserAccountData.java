package com.revature.bank_application.models;

public class UserAccountData {

    // These are creating private strings that only this script can see.
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;

    public UserAccountData()
    {
        super();
    }
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

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
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


