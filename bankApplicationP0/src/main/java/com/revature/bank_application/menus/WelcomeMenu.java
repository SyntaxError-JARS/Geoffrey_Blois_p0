package main.java.com.revature.bank_application.menus;

import main.java.com.revature.bank_application.services.UserAccountServices;

import java.io.BufferedReader;

import static main.java.com.revature.bank_application.util.AppState.shutdown;

// The extends key word allows me to extend the abstract menu class to quickly make new menus

// Inheritance from menu abstract class. Another pillar of OOp
public class WelcomeMenu extends Menu{

    private UserAccountServices userAccountServices;
    public WelcomeMenu( BufferedReader terminalReader, UserAccountServices userAccountServices) {
        super("Welcome", "/welcome", terminalReader);
        this.userAccountServices = userAccountServices;
    }

    // This method renders the welcome menu
    @Override
    public void render() throws Exception {

        String welcome = "Welcome to the Bank of Everything!";
        String option1 = " 1) Not a User? Create an Account";
        String option2 = " 2) Want to delete your account?";
        String option3 = " 3) View all of the User in the Database";
        String option4 = " 4) Want to change your username?";
        String option5 = " 5) Leave the Bank of Everything";

        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s ", welcome, option1, option2, option3, option4, option5).println();

        System.out.println("Select one of the input from above\n >");
        String userInputToEnterBankApp = terminalReader.readLine();



        switch (userInputToEnterBankApp) {
            case "1":
                System.out.println("User has chosen to create an account ...");
                //registerMenu.render();
                break;
            case "2":
                System.out.println("User has chosen to Delete. Provide Id below ...");
                //SignInAccount.SignInAccounts();
                String id = terminalReader.readLine();
                userAccountServices.deleteAccount(id);

                break;
            case "3":
                System.out.println("User has chosen to view Users ...");
                userAccountServices.readUsers();
                break;
            case "4":
                System.out.println("User has chosen to update their username. Please pick the user id you want to update...");
                String id2 = terminalReader.readLine();
                System.out.println("Please enter the new username you would like the account to have...");
                String userName = terminalReader.readLine();
                //userAccountServices.updateAccount(id2, userName);
                break;
            case "5":
                System.out.println("User has chosen to exit application. Thank for using our service ...");
                shutdown();
                break;
            default:
                System.out.println("A invalid input has been detected please run the application again and use one of the valid input. Thank You ...");
                break;

        }
    }
}
