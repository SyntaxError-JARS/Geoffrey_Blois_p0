package com.revature.bank_application.execeptions;

public class WrongAmountException extends RuntimeException{
    public WrongAmountException(String message) {
        super(message);
    }
}
