package com.revature.bank_application.daos;

public interface TransactionsDoaInterface <T>{


    T withDraw(int object);

    T deposit(int object);

}
