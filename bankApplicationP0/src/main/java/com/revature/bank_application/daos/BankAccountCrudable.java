package main.java.com.revature.bank_application.daos;

import main.java.com.revature.bank_application.models.UserAccountData;

import java.io.IOException;

// Here I am utilizing a generic <T> to allow T to be filling in with anything I want. It could be a String, int,
// char, object, etc..
public interface BankAccountCrudable <T>{

    T create(T newObject);

    UserAccountData[] findAll() throws IOException;
    T findById(String id);

    //boolean update(String id);

    boolean update(String id2, String userName);

    boolean delete(String id);
}
