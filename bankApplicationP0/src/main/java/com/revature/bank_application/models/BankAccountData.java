package main.java.com.revature.bank_application.models;

import java.util.Random;

public class BankAccountData {

     //File signInFile = new File("data/User_Data");
    // TODO: Update this class to bank account class instead so the models package follows the amount of tables I have.

    private int bankAccountNumber;
    private String bankAccountName;
    private int bankAccountAmount;
    private boolean hasBankAccount;

    private int randBankNumber;


    public BankAccountData(int bankAccountNumber, String bankAccountName, int bankAccountAmount, boolean hasBankAccount, int randBankNumber){
        super();
        this.bankAccountNumber = bankAccountNumber;
        this.bankAccountName = bankAccountName;
        this.bankAccountAmount = bankAccountAmount;
        this.hasBankAccount = hasBankAccount;
        this.randBankNumber = randBankNumber;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public int getBankAccountAmount() {
        return bankAccountAmount;
    }

    // TODO: This should not be Here!!!!!!!!!!!!
//    public void setBankAccountAmount(int bankAccountAmount) {
//        this.bankAccountAmount = bankAccountAmount;
//    }

    public void getHasBankAccount(boolean hasBankAccount){
        this.hasBankAccount = hasBankAccount;
    }
    public void setHasBankAccount(boolean hasBankAccount) {
        this.hasBankAccount = hasBankAccount;
    }

    public String toFileString(){
        StringBuilder mutableString = new StringBuilder();
        mutableString .append(bankAccountNumber)
                      .append(",")
                      .append(bankAccountName)
                      .append(",")
                      .append(bankAccountAmount)
                      .append(",")
                      .append(hasBankAccount);
        return mutableString.toString();
    }

    // This randomBankAccount method will generate a random number between 0 - 999999999
    public static int randomBankAccountNumber(){
        // The Random is a class that java provides. So all I had to do was right the Random and java imported the Random class into this class.
        // I am also creating a new instance of random when ever this method is called.
        Random rand = new Random();
        // The bound here means that the random number will not be above 999,999,999. NOTE: These number provided needs to be positive as a negative number will not work.
        int number = rand.nextInt(999999999);

        return number;
    }
    @Override
    public String toString() {
        return "BankAccountData{" +
                "bankAccountNumber=" + bankAccountNumber +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountAmount=" + bankAccountAmount +
                ", hasBankAccount=" + hasBankAccount +
                '}';
    }
}
