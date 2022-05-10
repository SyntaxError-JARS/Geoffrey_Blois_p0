package com.revature.bank_application.util;

// Design patterns

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
    Singleton Design Pattern
    - Creational Pattern
    - Restricts that only a single instance of this class can be made within the application
    - Constructor cannot be invoked outside of this class
    - Eager or Lazy singleton


    Factory Design Pattern
    -Creational Pattern
    -Used to abstract away the creation/instantiation of the class

 */
public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); // instead Eagar Singleton
    private Properties prop = new Properties();

    private ConnectionFactory(){
        try {
            prop.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        // Reflections are just viewing a class
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    public Connection getConnection(){

        Connection conn = null;

        //String url = "jdbc:postgresql://localhost:5432/postgres"; // default url will connect you to public
        // TODO: WE NEED TO FIX THIS
//        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=bankaccount"; // ?
//        String user = "postgres";
//        String password = "password";

        try {
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}

