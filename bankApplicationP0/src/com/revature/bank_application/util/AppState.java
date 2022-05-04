package com.revature.bank_application.util;

import com.revature.bank_application.menus.RegisterMenu;
import com.revature.bank_application.menus.WelcomeMenu;
import com.revature.bank_application.services.UserAccountServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    // The reason these attributes are private are these variables will only be used in the AppStata.java file so there is no need to make these private
    private static boolean isRunning;

    private WelcomeMenu welcomeMenu;
    private RegisterMenu registerMenu;


    public AppState(){

        System.out.println("AppState loading");
        // This is telling the app that the current state of this application is true
        isRunning = true;

        // This is creating a new instance of BufferedReader which gets input from users.
        // This is in here so, I don't have to add this to every class I need a BufferedReader.(Save Memory)
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        UserAccountServices userAccountServices = new UserAccountServices();


        this.welcomeMenu = new WelcomeMenu(terminalReader, userAccountServices);
        this.registerMenu = new RegisterMenu(terminalReader);

    }

    // Starts up the Application
    public void startup() {
            try {
                // While this is running it will continue to run the application.
                while(isRunning == true) {
                    // Have to manually switch between these for now.
                    //welcomeMenu.render();
                    registerMenu.render();
                    System.out.println("Loading the welcomeMenu");
                }
            } catch (Exception e) {
                 e.printStackTrace();
                //RuntimeException
            }
    }

    // Shutdown the Application
    public static void shutdown(){
        isRunning = false;
    }


}
