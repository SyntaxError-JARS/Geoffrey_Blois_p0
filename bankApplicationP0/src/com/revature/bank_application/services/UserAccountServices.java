package com.revature.bank_application.services;

import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.models.UserAccountData;

import java.io.IOException;

public class UserAccountServices {

    private UserAccountDao userAccountDao = new UserAccountDao();


    public void readUsers(){
        // TODO: Implement Me!!!!!!
        UserAccountData[] userAccountData = new UserAccountData[0];
        try{
            userAccountData = userAccountDao.findAll();
            for (int i = 0; i < userAccountData.length; i++){
                UserAccountData userAccountData1 = userAccountData[i];
                System.out.println(userAccountData1.toString());
            }
        }catch (IOException | NullPointerException e){

        }
    }

    // This allows me to check to make sure that the information that the user inputs is correct before adding it to my data base.
    public boolean registerAccount(UserAccountData userAccountData) {
        if (!ValidateNewAccount(userAccountData)) {
            throw new RuntimeException();
        }

        UserAccountData persistedAccount = userAccountDao.create(userAccountData);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        return true;
    }

    // This is where all of my check will be for creating an account.
    public boolean ValidateNewAccount(UserAccountData userAccountData){
        if(userAccountData == null) return false;
        if(userAccountData.getUserName() == null || userAccountData.getUserName().trim().equals("")) return false;
        if(userAccountData.getFirstName() == null || userAccountData.getFirstName().trim().equals("")) return false;
        if(userAccountData.getLastName() == null || userAccountData.getLastName().trim().equals("")) return false;
        if(userAccountData.getPassword() == null || userAccountData.getPassword().trim().equals("")) return false;
        if(userAccountData.getEmail() == null || userAccountData.getEmail().trim().equals("")) return false;

        return true;
    }
}
