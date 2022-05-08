package main.java.com.revature.bank_application.menus;

import main.java.com.revature.bank_application.services.BankAccountServices;
import main.java.com.revature.bank_application.services.UserAccountServices;
import main.java.com.revature.bank_application.util.logging.Logger;

import java.io.BufferedReader;

import static main.java.com.revature.bank_application.util.AppState.shutdown;

// The extends key word allows me to extend the abstract menu class to quickly make new menus

// Inheritance from menu abstract class. Another pillar of OOp
public class WelcomeMenu extends Menu{

    private UserAccountServices userAccountServices;
    private BankAccountServices bankAccountServices;
    private NewBankAccountMenu newBankAccountMenu;

    private final Logger logger;

    // The super("welcome", "/welcome", terminalReader) has to be in the method because I extended the menu class which means anything in that class has to be in this class.
    // The whole method below is telling the Welcome Menu to play once the WelcomeMenu is called in the AppState. In the WelcomeMenu parenthesis are parameters that are needed for the
    // welcome menu to be properly instantiated.
    public WelcomeMenu( BufferedReader terminalReader, UserAccountServices userAccountServices, Logger logger, BankAccountServices bankAccountServices) {
        super("Welcome", "/welcome", terminalReader);
        this.userAccountServices = userAccountServices;
        this.logger = logger;
        this.bankAccountServices = bankAccountServices;
    }

    // This method renders the welcome menu
    // The override is here because there is a method that is in the menu interface, so I have to override that method to be able to render my welcome menu
    // The render() must be in this and all menus because if it was not the program would yell at me because I am extending the menu class which means I need to use everything in that class.
    @Override
    public void render() throws Exception {

        String welcome = "Welcome to the Bank of Everything!";
        String option1 = " 1) Not a User? Create an Account";
        String option2 = " 2) Want to delete your account?";
        String option3 = " 3) View all of the User in the Database";
        String option4 = " 4) Want to change your username?";
        String option5 = " 5) Leave the Bank of Everything";

        // The %s interprets as string in the sout below while the \n are creating a new line.
        // after the last %s the arguments proceed after it which means they will print out in that order. So welcome will print then new line then option1 and so on.
        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s ", welcome, option1, option2, option3, option4, option5).println();

        // This line is printing last and
        System.out.println("Select one of the input from above\n >");
        String userInputToEnterBankApp = terminalReader.readLine();


        // This switch statement is a type of selection control mechanism used to allow the value of the variable or expression to change the control flow of the program execution.
        switch (userInputToEnterBankApp) {
            case "1":
                //logger.info("User has chosen to create an account ...");
                System.out.println("User has chosen to create a new bank account");
                //registerMenu.render();
                //newBankAccountMenu.render();


                // Bank Find with ID
                String bId = terminalReader.readLine();
                bankAccountServices.findbyId(bId);
                break;
            case "2":
                logger.info("User has chosen to Delete. Provide Id below ...");
                //SignInAccount.SignInAccounts();
                String id = terminalReader.readLine();
                userAccountServices.deleteAccount(id);

                break;
            case "3":
                logger.info("User has chosen to view Users ...");
                userAccountServices.readUsers();
                break;
            case "4":
                logger.info("User has chosen to update their username.");
                System.out.println("Please pick the user id you want to update...");
                String id2 = terminalReader.readLine();
                System.out.println("Please enter the new username you would like the account to have...");
                String userName = terminalReader.readLine();
                userAccountServices.updateAccount(id2, userName);
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
