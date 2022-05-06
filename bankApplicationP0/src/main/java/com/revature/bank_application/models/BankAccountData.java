package main.java.com.revature.bank_application.models;

public class BankAccountData {

     //File signInFile = new File("data/User_Data");
    // TODO: Update this class to bank account class instead so the models package follows the amount of tables I have.

    private int bankAccountNumber;
    private String bankAccountName;
    private int bankAccountAmount;
    private boolean hasBankAccount;

    public BankAccountData(int bankAccountNumber, String bankAccountName, int bankAccountAmount, boolean hasBankAccount){
        super();
        this.bankAccountNumber = bankAccountNumber;
        this.bankAccountName = bankAccountName;
        this.bankAccountAmount = bankAccountAmount;
        this.hasBankAccount = hasBankAccount;
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
