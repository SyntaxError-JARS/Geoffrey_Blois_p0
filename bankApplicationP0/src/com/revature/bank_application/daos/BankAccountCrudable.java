package com.revature.bank_application.daos;

import com.revature.bank_application.models.UserAccountData;

import java.io.IOException;

// Here I am utilizing a generic <T> to allow T to be filling in with anything I want. It could be a String, int,
// char, object, etc..
public interface BankAccountCrudable <T>{

    T create(T newObject);

    UserAccountData[] findAll() throws IOException;

    boolean update(T updateObj);

    boolean delete(String id);
}
