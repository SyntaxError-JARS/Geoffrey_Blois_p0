package com.revature.bank_application.services;

import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.models.UserAccountData;

public class UserAccountServices {

    private UserAccountDao userAccountDao = new UserAccountDao();


    public void readUsers(){
        // TODO: Implement Me!!!!!!

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
        // TODO: Add more checks for validating a new account.
        return true;
    }
}
