package com.revature.bank_application.services;

import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.execeptions.AuthenticationException;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.util.logging.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class UserAccountServices {

    private UserAccountDao userAccountDao;

    private Logger logger = Logger.getLogger(false);

    public UserAccountServices(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }


    public ArrayList<UserAccountData> readUsers(){

        try{
            ArrayList<UserAccountData> userAccountData = userAccountDao.findAll();

            logger.info("All users have been found and here are all the results: \n");
            for(Object account:userAccountData){
                if (account != null) {
                    System.out.println((UserAccountData) account); //UserAccountData indicates a single in the account array
                }
            }

            return userAccountData;

        }catch (IOException | NullPointerException e){
            e.printStackTrace();
            logger.warn("A problem with the readUsers() occurred");
            return null;
        }

    }


    // This allows me to check to make sure that the information that the user inputs is correct before adding it to my data base.
    public boolean registerAccount(UserAccountData userAccountData) {
        if (!ValidateNewAccount(userAccountData)) {
            logger.info("User input was not validated, either empty Sting or null values");
            throw new InvalidRequestException("User input was not validated, either empty Sting or null values");

        }
        System.out.println("User is Valid");
        UserAccountData persistedAccount = userAccountDao.create(userAccountData);

        if(persistedAccount == null){
            throw new ResourcePersistanceException("Account was not persisted to the database upon registration");
        }

        logger.info("A new user has been created and persisted to the database \n");
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

    // This method calls my delete(using the id in the parameter) to my dao class which then executes it on the database.
    public UserAccountData deleteAccount(String id){

        boolean deleteAccount = userAccountDao.delete(id);
        logger.info("A user account has been deleted \n");
        return null;
    }

    // This method calls my update(using the id2 and userName in the parameter) to my dao class which then executes it on the database.
    public boolean updateAccount(String id2, String userName){
        boolean updateAccount = userAccountDao.update(id2, userName);
        logger.info("A user has updated there username \n");
        return updateAccount;
    }

    // This method calls my findById(using the id in the parameter) to my dao class which then executes it on the database.
    public UserAccountData findByID(String id){

        UserAccountData findUserById = userAccountDao.findById(id);

        logger.info("A single user was found: \n");
        return findUserById;
    }

    // In this method I am check to see if the user log in information was correct with my database before it logs them in. If their information is not on my database
    // it will throw AuthenticationException.
    public UserAccountData authenticateUser(String email, String password){
        if(password == null || password.trim().equals("") || email == null || email.trim().equals("")){
            throw new InvalidRequestException("Either username or email is an invalid entry. Please try logging in again");

        }
        UserAccountData authenticatedUser = userAccountDao.authenticateUser(email, password);
        logger.info("A user has been able to log in to the application");
        if(authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database");
        }

        return authenticatedUser;
    }
}
