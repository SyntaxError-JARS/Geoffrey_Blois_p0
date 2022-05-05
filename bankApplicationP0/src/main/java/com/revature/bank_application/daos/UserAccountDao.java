package main.java.com.revature.bank_application.daos;

import main.java.com.revature.bank_application.models.UserAccountData;
import main.java.com.revature.bank_application.util.ConnectionFactory;

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

        try (Connection conn = ConnectionFactory.getInstance().getConnection();){ // try with resource, becuase connection extends the interface Auto-closes

            String sql = "select * from useraccount";
            Statement s = conn.createStatement();

            s.executeQuery(sql); //select
            //s.executeUpdate(sql); // Insert, delete, update

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {

                UserAccountData userAccountData = new UserAccountData();
                userAccountData.setFirstName(rs.getString("first_name"));
                userAccountData.setLastName(rs.getString("last_name"));
                userAccountData.setUserName(rs.getString("user_name"));
                userAccountData.setEmail(rs.getString("email"));
                userAccountData.setPassword(rs.getString("password"));

                System.out.println("Inserted User int index" + index);
                users[index] = userAccountData;

                index++;



            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        System.out.println("A New User has been created!!!");
        return users;
    }

    @Override
    public UserAccountData findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from useraccount where id =?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            UserAccountData userAccountData = new UserAccountData();

            userAccountData.setFirstName(rs.getString("first_name"));
            userAccountData.setLastName(rs.getString("last_name"));
            userAccountData.setUserName(rs.getString("user_name"));
            userAccountData.setEmail(rs.getString("email"));
            userAccountData.setPassword(rs.getString("password"));

            return userAccountData;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean update(UserAccountData updateObject) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){


            String sql = "update user_name from useraccount set user_name=? where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            //ps.setInt(1, Integer.parseInt(id));
            UserAccountData userAccountData = new UserAccountData();
            //ps.setInt(1, Integer.parseInt(updateObject));
            ps.setString(2, userAccountData.getUserName());


            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "delete from useraccount where id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            System.out.println("About to execute the ps.executeupdate");
             ps.executeUpdate();

            System.out.println("Successfully deleted a user");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }
}
