package com.revature.bank_application.services;

import com.revature.bank_application.daos.TransactionDoa;
import com.revature.bank_application.execeptions.WrongAmountException;

public class TransactionServices {

    private TransactionDoa transactionDoa = new TransactionDoa();


    public boolean withDraw(int amount){

        if(amount <= 0){
            throw new WrongAmountException("The amount inputted was a negative number or zero.");
        }

        return true;
    }


    public boolean deposit(int amount){

        if(amount <= 0){
            throw new WrongAmountException("The amount inputted was a negative number or zero.");
        }

        return true;
    }
}
