package com.revature.bank_application.models;

public class TransactionData {

    private int accountNumber;
    private String transactionType;
    private String transactionNote;


    public TransactionData(int accountNumber, String transactionType, String transactionNote){
        super();
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.transactionNote = transactionNote;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }


    public String toFileString(){
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(accountNumber)
                .append(",")
                .append(transactionType)
                .append(",")
                .append(transactionNote);
        return mutableString.toString();
    }


    @Override
    public String toString() {
        return "TransactionData{" +
                "accountNumber=" + accountNumber +
                ", transactionType='" + transactionType + '\'' +
                ", transactionNote='" + transactionNote + '\'' +
                '}';
    }
}
