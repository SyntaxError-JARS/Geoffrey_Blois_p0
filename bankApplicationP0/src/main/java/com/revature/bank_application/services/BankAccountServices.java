package com.revature.bank_application.services;

import com.revature.bank_application.daos.BankAccountDoa;
import com.revature.bank_application.models.BankAccountData;

public class BankAccountServices {

    private BankAccountDoa bankAccountDoa;

    public BankAccountServices(BankAccountDoa bankAccountDoa) {
        this.bankAccountDoa = bankAccountDoa;
    }


    public  boolean CreateBankAccount(BankAccountData bankAccountData){

        BankAccountData persistedAccount = bankAccountDoa.create(bankAccountData);

//        if(persistedAccount == null){
//            throw new ResourcePersistanceException("Account was not persisted on account creation");
//        }

        return true;
    }

    public boolean validateBankAccount(BankAccountData bankAccountData){
        if(bankAccountData == null) return false;
        if(bankAccountData.getBankAccountNumber() == 0) return false;
        return true;
    }

    public BankAccountData findbyId(String bId){

        BankAccountData findbyId = bankAccountDoa.findById(bId);

        return findbyId;
    }

}
