package com.revature.bank_application;

import com.revature.bank_application.util.AppState;


public class MainDriver {

    // All this class does now create a new appState and run the startup() method.
    public static void main(String[] args) {
        System.out.println("AppState Instantiated");
        AppState appState = new AppState();
        appState.startup();
    }
}
