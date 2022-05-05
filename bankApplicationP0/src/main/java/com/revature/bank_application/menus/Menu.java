package main.java.com.revature.bank_application.menus;

import java.io.BufferedReader;

// Here I am abstracting a class in order to implement the methods below into other classes.
// Abstract classes cannot be instantiated.
public abstract class Menu {

    // The reason the Strings and BufferedReader are using the protected key word is to ensure that they can only be
    // called within the same package unless inheritance is applied
    protected String name;
    protected String route;
    protected BufferedReader terminalReader;

    public Menu(String name, String route, BufferedReader terminalReader){
        super();
        // I am using .this to set the class attributes to the parameters of the Menu().
        this.name = name;
        this.route = route;
        this.terminalReader = terminalReader;
    }

    // In these two get statements. I am using them to get the name and route of the menus.
    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    // I am using this abstract method because you can instantiate in an abstract class so, using abstract allows me
    // to use this method in any new menu class I create. This makes it easy to create new menus.
    public abstract void render() throws Exception;
}

