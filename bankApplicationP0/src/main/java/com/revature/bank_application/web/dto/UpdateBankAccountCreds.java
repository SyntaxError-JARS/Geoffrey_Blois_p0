package com.revature.bank_application.web.dto;

public class UpdateBankAccountCreds {

    private String id2;

    private String newBankAccountName;


    public String getId2() {
        return id2;
    }


    public String getNewBankAccountName() {
        return newBankAccountName;
    }

    public void setNewBankAccountName(String newBankAccountName) {
        this.newBankAccountName = newBankAccountName;
    }
}
