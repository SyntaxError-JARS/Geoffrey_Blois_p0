package com.revature.bank_application.daos;

import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.util.ConnectionFactory;

import java.io.*;
import java.sql.*;


// The word implements below allow me to implement method from the BankAccountCrudable Interface class. This make so I don't have to recreate so many methods.
public class UserAccountDao implements BankAccountCrudable<UserAccountData> {


    @Override
    public UserAccountData create(UserAccountData userAccountData) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            // Never concatenate or directly use these strings inside of the sql statement;
            //String sql = "insert into useraccount values ("
            System.out.println("Here is the new user as it enters the doa layer"+ userAccountData);

            String sql = "insert into useraccount values(default, ?, ?, ? ,? ,?, 0)";
            //String sql = "insert into useraccount (username, password, email, first_name, last_name)  values(default ?, ?, ? ,? ,?, 0)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1 -indexed, so first ? starts at 1
            ps.setString(1, userAccountData.getUserName());
            ps.setString(2, userAccountData.getPassword());
            ps.setString(3, userAccountData.getEmail());
            ps.setString(4, userAccountData.getFirstName());
            ps.setString(5, userAccountData.getLastName());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException();
            }


        }catch (SQLException | RuntimeException e){
            e.printStackTrace();
            return null;
        }
        System.out.println("New User Added to the data base");
        return userAccountData;
    }



    @Override
    public UserAccountData[] findAll() throws IOException {
        // This allows me to read files


        // I am initializing an array of 10 userAccountData
        UserAccountData[] users = new UserAccountData[10];
        int index = 0;

        // In this try with resources once called it establishes a connection to my database so that the methods inside it can interact with my database.
        try (Connection conn = ConnectionFactory.getInstance().getConnection();){ // try with resource, because connection extends the interface Auto-closes

            // Creating a string called sql which will tell sql once run what statement should be run.
            String sql = "select * from useraccount";
            // The statement s here means that the statement is an executable instruction which tell the compiler what to perform.
            Statement s = conn.createStatement();

            // This is executing a query statement for all user information in the useraccount table.
            s.executeQuery(sql); //select
            //s.executeUpdate(sql); // Insert, delete, update

            //
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {

                UserAccountData userAccountData = new UserAccountData();
                userAccountData.setFirstName(rs.getString("first_name"));
                userAccountData.setLastName(rs.getString("last_name"));
                userAccountData.setUserName(rs.getString("user_name"));
                userAccountData.setEmail(rs.getString("email"));
                userAccountData.setPassword(rs.getString("password"));

                users[index] = userAccountData;

                index++;



            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return users;
    }

    @Override
    public UserAccountData findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            // This is creating a sql string for java to use with sql statements.
            String sql = "select * from useraccount where id =?";

            //  The preparedStatement can help against sql injections. A preparedStatement is a statement that pre-complies SQL code to separate it from the data.
            PreparedStatement ps = conn.prepareStatement(sql);

            // I only want to find one user with an id so I just need to parse the int of the id the user provides and when the sql statement is executed it will display the user.
            ps.setInt(1, Integer.parseInt(id));

            //This is executing a query which can only be used with select.
            ResultSet rs = ps.executeQuery();

            // This is creating a new user object to display the information requested.
            UserAccountData userAccountData = new UserAccountData();

            // This is grabbing all the data that the user id is connected to.
            userAccountData.setFirstName(rs.getString("first_name"));
            userAccountData.setLastName(rs.getString("last_name"));
            userAccountData.setUserName(rs.getString("user_name"));
            userAccountData.setEmail(rs.getString("email"));
            userAccountData.setPassword(rs.getString("password"));

            // Once this is completed it will return the userAccountData
            return userAccountData;

            //This catch is catching an execution and uses the print stacktrace to print out the exact error in the terminal
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean update(String id2, String userName) {
        // In this try with resource it is establishing a connection with my database
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            // The first ? in this is always 1.
            String sql = ("update useraccount set user_name=? where id=?");

            //  The preparedStatement can help against sql injections. A preparedStatement is a statement that pre-complies SQL code to separate it from the data.
            PreparedStatement ps = conn.prepareStatement(sql);
            // These taking the user inputs from the menu and putting them into the sql statement.
            // Whenever you do this the order matter so the first ? in sql line is the first parameterindex.
            ps.setString(1, userName);
            ps.setInt(2, Integer.parseInt(id2));

            //This executes the sql statement in postgresql
            ps.executeUpdate();
            System.out.println("Updated user account of" + id2 + " to " + userName );
            return true;

            //This catch is catching an execution and uses the print stacktrace to print out the exact error in the terminal
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(String id) {
        // In this try with resources it is establishing a connection to my database.
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // This is the sql command I want to use when this method is called
            String sql = "delete from useraccount where id=?";

            //  The preparedStatement can help against sql injections. A preparedStatement is a statement that pre-complies SQL code to separate it from the data.
            PreparedStatement ps = conn.prepareStatement(sql);

            // This is inputing the id the user is imputing so when the statement gets executed the right user id account is deleted.
            ps.setInt(1, Integer.parseInt(id));

            System.out.println("About to execute the ps.executeupdate");
            //This executes the sql statement in postgresql
             ps.executeUpdate();

            System.out.println("Successfully deleted a user");
            return true;
            //This catch is catching an execution and uses the print stacktrace to print out the exact error in the terminal
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public UserAccountData authenticateUser(String email, String password){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from useraccount where email= ? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            UserAccountData userAccountData = new UserAccountData();

            if(!rs.next()){
                return null;
            }

            userAccountData.setUserName(userAccountData.getUserName());
            userAccountData.setPassword(userAccountData.getPassword());
            userAccountData.setEmail(userAccountData.getEmail());
            userAccountData.setFirstName(userAccountData.getFirstName());
            userAccountData.setLastName(userAccountData.getLastName());

            return userAccountData;



        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
