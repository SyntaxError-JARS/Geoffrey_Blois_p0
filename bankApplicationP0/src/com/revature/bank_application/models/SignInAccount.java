package com.revature.bank_application.models;

import java.io.*;

public class SignInAccount {

     File signInFile = new File("data/User_Data");

    public  SignInAccount() throws IOException {
        //super();
        try( BufferedReader accountReader = new BufferedReader(new FileReader(signInFile))){
            String accountLine;
            System.out.println("Entered The Try LOOP in SignInAccount2");
            while ((accountLine = accountReader.readLine()) != null){
                System.out.println(accountLine);
            }

        }catch(IOException e){
            throw new FileNotFoundException("e");
        }

    }
}
