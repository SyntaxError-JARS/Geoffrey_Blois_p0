package com.revature.bank_application.execeptions;

public class BankAccountInsertDataException extends RuntimeException{
    public BankAccountInsertDataException(String message) {
        super(message);
    }
}
