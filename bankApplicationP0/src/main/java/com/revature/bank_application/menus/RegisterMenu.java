package main.java.com.revature.bank_application.menus;

import main.java.com.revature.bank_application.models.UserAccountData;
import main.java.com.revature.bank_application.services.UserAccountServices;

import java.io.BufferedReader;

public class RegisterMenu extends Menu {

    private UserAccountServices userAccountServices = new UserAccountServices();
    public RegisterMenu( BufferedReader terminalReader) {
        super("Register", "/register", terminalReader);
    }

    // Polymorphism Another pillar of OOP for the same thing doing different things
    // This is overriding a method
    @Override
    public void render() throws Exception {
        System.out.println("Create a Username");
        String username = terminalReader.readLine();

        System.out.println("Create a Password");
        String password = terminalReader.readLine();

        System.out.println("Create a Password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("Add your Full name");
        String fullName = terminalReader.readLine();

        System.out.println("Add your email address");
        String email = terminalReader.readLine();

        String[] nameArray = fullName.split(" ");
        String firstName = nameArray[0];
        String lastName = nameArray[1];

        if (!password.equals(passwordCheck)) {
            System.out.println("The Passwords you entered did not match. Please re-enter the information and make sure the passwords match.");
            return;
        }

        UserAccountData userAccountData = new UserAccountData(username, password, firstName, lastName, email);
        System.out.println(" Here is the user account information provided by the user:"+ userAccountData);
        userAccountServices.registerAccount(userAccountData);

    }
}