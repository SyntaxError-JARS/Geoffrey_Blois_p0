package com.revature.bank_application.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SignInAccount {

     //File signInFile = new File("data/User_Data");

    public static  UserAccountData[] SignInAccounts() throws IOException {
        FileReader fileReader = new FileReader("data/User_Data.txt");
        BufferedReader dataReader = new BufferedReader(fileReader);

        UserAccountData[] users = new UserAccountData[10];

        String userData = dataReader.readLine();
        int index = 0;

        while (userData != null){

            String[] userInfo = userData.split(",");

            String username = userInfo[0];
            String password = userInfo[1];
            String email = userInfo[2];
            String phoneNumber = userInfo[3];

            UserAccountData user = new UserAccountData(username, password, email, phoneNumber);
            users[index] = user;

            index++;

            userData = dataReader.readLine();

        }
        dataReader.close();

        System.out.println("A New User has been created!!!");
        return users;
    }
}
