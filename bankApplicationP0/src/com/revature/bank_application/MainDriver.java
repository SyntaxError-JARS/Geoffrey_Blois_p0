package com.revature.bank_application;

import com.revature.bank_application.models.UserAccountData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainDriver {

    static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {

        String welcome = "Welcome to the Bank of Everything!";
        String option1 = " 1) Not a User? Create an Account";
        String option2 = " 2) A returning User? Sign in";
        String option3 = " 3) Leave the Bank of Everything";

        System.out.printf("%s \n %s \n %s \n %s", welcome, option1, option2, option3).println();

        try {
            System.out.println("Select one of the input from above\n >");
            String userInputToEnterBankApp = terminalReader.readLine();


            switch (userInputToEnterBankApp) {
                case "1":
                    System.out.println("User has chosen to create an account ...");
                    userCreateAccountInput();
                    break;
                case "2":
                    System.out.println("User has chosen to Login. Welcome back User ...");
                    userCreateAccountInput();
                    break;
                case "3":
                    System.out.println("User has chosen to exit application. Thank for using our service ...");
                    break;
                default:
                    System.out.println("A invalid input has been detected please run the application again and use one of the valid input. Thank You ...");
                    break;

            }

        }catch (IOException e){
            throw  new RuntimeException(e);
        }



    }

    static void userCreateAccountInput() throws IOException{
        System.out.println("Create a Username");
        String username = terminalReader.readLine();
        System.out.println("Create a Password");
        String password = terminalReader.readLine();
        System.out.println("Add your Phone Number");
        String phoneNumber = terminalReader.readLine();
        System.out.println("Add your email address");
        String email = terminalReader.readLine();

        UserAccountData accountData = new UserAccountData(username, password, phoneNumber, email);
        System.out.println(accountData);

    }

}
