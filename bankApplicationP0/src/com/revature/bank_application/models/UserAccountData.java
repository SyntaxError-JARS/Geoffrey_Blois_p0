package com.revature.bank_application.models;

public class UserAccountData {

    // These are creating private strings that only this script can see.
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    /*public UserAccountData()
    {
        super();
    }*/
    // This method is grabbing the Strings above and assigning them
    public UserAccountData(String userName, String password, String firstName,String lastName, String email){
        super();
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
                .append(firstName)
                .append(",")
                .append(lastName)
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


