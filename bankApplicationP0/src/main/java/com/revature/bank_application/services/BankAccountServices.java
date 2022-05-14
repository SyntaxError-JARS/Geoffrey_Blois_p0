package com.revature.bank_application.services;

import com.revature.bank_application.daos.BankAccountDoa;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.models.BankAccountData;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<BankAccountData> findAll() throws IOException {

        ArrayList<BankAccountData> findAll = bankAccountDoa.findAll();

        return findAll;
    }

    public boolean updateBankAccountName(String id2, String newBankAccountName){
        boolean updatedBankAccount = bankAccountDoa.update(id2, newBankAccountName);

        return updatedBankAccount;

    }

    public boolean deleteBankAccount(String id){
        boolean deletedBankAccount = bankAccountDoa.delete(id);

        return deletedBankAccount;
    }

    public BankAccountData deposit(String deposit, String id){

        if(depositCheck(deposit) == false){
            throw new InvalidRequestException("User inputted a negative number or not a int. Please check to see if the information enter was correct.");
        }

        BankAccountData depositInAccount = bankAccountDoa.deposit(deposit, id);

        return depositInAccount;

    }

    public BankAccountData withDraw(String deposit, String id){

        if(withdrawCheck(deposit) == false){
            throw new InvalidRequestException("User inputted a negative number or not a int. Please check to see if the information enter was correct.");
        }
        BankAccountData withdrawInAccount = bankAccountDoa.withdraw(deposit, id);

        return withdrawInAccount;

    }

     //if(bankAccountData.getBankAccountName() == null || bankAccountData.getBankAccountName().trim().equals("")) return false;
    public boolean depositCheck(String deposit){

        if(Integer.parseInt(deposit) < 0 || deposit.equals("")) return false;

        return true;
    }

    public boolean withdrawCheck(String deposit){

        if(Integer.parseInt(deposit) < 0 || deposit.equals("")) return false;

        return true;
    }

}
