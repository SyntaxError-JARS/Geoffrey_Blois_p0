package com.revature.bank_application.daos;

import com.revature.bank_application.models.UserAccountData;

import java.io.*;


// The word implements below allow me to implement method from the BankAccountCrudable Interface class. This make so I don't have to recreate so many methods.
public class UserAccountDao implements BankAccountCrudable<UserAccountData> {
    @Override
    public UserAccountData create(UserAccountData accountData) {

        // This is obtaining the user_data.txt file from the desired path. This allows me to write to the path or in the findAll() method I can read it.
        File userPersistingData = new File("data/user_Data.txt");


        try (FileWriter fileWriter = new FileWriter(userPersistingData, true)) {
            fileWriter.write(accountData.toFileString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

        return accountData;
    }



    @Override
    public UserAccountData[] findAll() throws IOException {
        // This allows me to read files
        FileReader fileReader = new FileReader("data/User_Data.txt");
        BufferedReader dataReader = new BufferedReader(fileReader);

        // I am initializing an array of 10 userAccountData
        UserAccountData[] users = new UserAccountData[10];

        // This is going to read the file line by line
        String userData = dataReader.readLine();
        int index = 0;

        while (userData != null){

            // The .split splits the data that is separated by a comma
            String[] userInfo = userData.split(",");

            String username = userInfo[0];
            String password = userInfo[1];
            String firstName = userInfo[2];
            String lastName = userInfo[3];
            String email = userInfo[4];


            UserAccountData user = new UserAccountData(username, password, firstName, lastName, email);
            users[index] = user;

            index++;

            userData = dataReader.readLine();

        }
        dataReader.close();

        System.out.println("A New User has been created!!!");
        return users;
    }


    @Override
    public boolean update(UserAccountData updateObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
