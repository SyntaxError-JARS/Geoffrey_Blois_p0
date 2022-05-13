package com.revature.bank_application.daos;

import java.io.IOException;
import java.util.ArrayList;

// Here I am utilizing a generic <T> to allow T to be filling in with anything I want. It could be a String, int,
// char, object, etc..
public interface BankAccountCrudable <T>{

    T create(T newObject);

    ArrayList<T> findAll() throws IOException;
    T findById(String id);

    //boolean update(String id);

    boolean update(String T, String newObject);

    boolean delete(String id);
}
