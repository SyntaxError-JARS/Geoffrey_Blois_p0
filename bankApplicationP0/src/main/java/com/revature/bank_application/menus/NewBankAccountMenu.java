package main.java.com.revature.bank_application.menus;

import main.java.com.revature.bank_application.execeptions.InvalidRequestException;
import main.java.com.revature.bank_application.execeptions.ResourcePersistanceException;
import main.java.com.revature.bank_application.models.BankAccountData;
import main.java.com.revature.bank_application.services.BankAccountServices;

import java.io.BufferedReader;

public class NewBankAccountMenu extends Menu{

    private BankAccountServices bankAccountServices = new BankAccountServices();
    public NewBankAccountMenu( BufferedReader terminalReader) {
        super("Create a New Bank Account with Us", "/newBankAccountMenu", terminalReader);
    }

    @Override
    public void render() throws Exception {

        System.out.println("Please choose the name you would like for your new Bank Account to have");
        String accountName = terminalReader.readLine();

        System.out.println("Each new account that is created needs money in it. \n At the Bank of Everything you need to have at least 50 dollars able to be put into the bank. \n So please input 50 dollars or more to create your account.");
        String accountAmount = terminalReader.readLine();


        BankAccountData bankAccountData = new BankAccountData(accountName, accountAmount);
        System.out.println("Here is the user provide account data: " + bankAccountData);

        try{
            bankAccountServices.CreateBankAccount(bankAccountData);
        }catch(InvalidRequestException | ResourcePersistanceException e){
            System.out.println(e.getMessage());
        }

    }
}
