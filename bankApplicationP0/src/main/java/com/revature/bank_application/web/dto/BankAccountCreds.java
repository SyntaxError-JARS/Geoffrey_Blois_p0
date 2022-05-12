package com.revature.bank_application.web.dto;

public class BankAccountCreds {

    private int bankAccountAmount;
    private String bankAccountName;


    public int getBankAccountAmount() {
        return bankAccountAmount;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
}
