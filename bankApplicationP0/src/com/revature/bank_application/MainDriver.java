package com.revature.bank_application;

import com.revature.bank_application.models.SignInAccount;
import com.revature.bank_application.models.UserAccountData;

import java.io.*;


public class MainDriver {

    static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        boolean isAppRunning  = true;
        // When the user start the application they will be greeted with this:
        String welcome = "Welcome to the Bank of Everything!";
        String option1 = " 1) Not a User? Create an Account";
        String option2 = " 2) A returning User? Sign in";
        String option3 = " 3) Leave the Bank of Everything";

        while(isAppRunning == true) {
            System.out.printf("%s \n %s \n %s \n %s", welcome, option1, option2, option3).println();

            try {
                System.out.println("Select one of the input from above\n >");
                String userInputToEnterBankApp = terminalReader.readLine();

                //SignInAccount  signIn = new SignInAccount();


                // This Will allow users to select an input.
                switch (userInputToEnterBankApp) {
                    case "1":
                        System.out.println("User has chosen to create an account ...");
                        userCreateAccountInput();
                        break;
                    case "2":
                        System.out.println("User has chosen to Login. Welcome back User ...");
                        SignInAccount.SignInAccounts();

                        break;
                    case "3":
                        System.out.println("User has chosen to exit application. Thank for using our service ...");
                        break;
                    default:
                        System.out.println("A invalid input has been detected please run the application again and use one of the valid input. Thank You ...");
                        break;

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    // This code will grab an exception and throw to the method above.
    static void userCreateAccountInput() throws IOException {


        System.out.println("Create a Username");
        String username = terminalReader.readLine();
        System.out.println("Create a Password");
        String password = terminalReader.readLine();
        System.out.println("Create a Password");
        String passwordCheck = terminalReader.readLine();
        System.out.println("Add your Phone Number");
        String phoneNumber = terminalReader.readLine();
        System.out.println("Add your email address");
        String email = terminalReader.readLine();


        if (!password.equals(passwordCheck)) {
            System.out.println("The Passwords you entered did not match. Please re-enter the information and make sure the passwords match.");
            return;
        }

        UserAccountData accountData = new UserAccountData(username, password, phoneNumber, email);
        System.out.println(accountData);

        File userPersistingData = new File("data/user_Data.txt");

        try (FileWriter fileWriter = new FileWriter(userPersistingData, true)) {
            fileWriter.write(accountData.toFileString());
        } catch (IOException e) {
            e.printStackTrace();

        }



    }

}
