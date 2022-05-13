package com.revature.bank_application.services;

import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.execeptions.AuthenticationException;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.UserAccountData;

import java.io.IOException;
import java.util.ArrayList;

public class UserAccountServices {

    private UserAccountDao userAccountDao;

    public UserAccountServices(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }


    public ArrayList<UserAccountData> readUsers(){

        //UserAccountData[] userAccountData = new UserAccountData[0];
        try{
            ArrayList<UserAccountData> userAccountData = userAccountDao.findAll();
//            for (int i = 0; i < userAccountData.length; i++){
//                UserAccountData userAccountData1 = userAccountData[i];
//                System.out.println(userAccountData1.toString());
//            }
            // forEach
            for(Object account:userAccountData){
                if (account != null) {
                    System.out.println((UserAccountData) account); //UserAccountData indicates a single in the account array
                }
            }

            return userAccountData;

        }catch (IOException | NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }


    // This allows me to check to make sure that the information that the user inputs is correct before adding it to my data base.
    public boolean registerAccount(UserAccountData userAccountData) {
        if (!ValidateNewAccount(userAccountData)) {
            throw new InvalidRequestException("User input was not validated, either empty Sting or null values");

        }
        System.out.println("User is Valid");
        UserAccountData persistedAccount = userAccountDao.create(userAccountData);

        if(persistedAccount == null){
            throw new ResourcePersistanceException("Account was not persisted to the database upon registration");
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

    public UserAccountData deleteAccount(String id){

        boolean deleteAccount = userAccountDao.delete(id);

        return null;
    }

    public boolean updateAccount(String id2, String userName){
        boolean updateAccount = userAccountDao.update(id2, userName);
        return updateAccount;
    }

    public UserAccountData findByID(String id){

        UserAccountData findUserById = userAccountDao.findById(id);


        return findUserById;
    }
    public UserAccountData authenticateUser(String email, String password){
        if(password == null || password.trim().equals("") || email == null || email.trim().equals("")){
            throw new InvalidRequestException("Either username or email is an invalid entry. Please try logging in again");

        }
        UserAccountData authenticatedUser = userAccountDao.authenticateUser(email, password);

        if(authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database");
        }

        return authenticatedUser;
    }
}
