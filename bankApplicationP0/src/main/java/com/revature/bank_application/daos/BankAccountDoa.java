package com.revature.bank_application.daos;

import com.revature.bank_application.execeptions.BankAccountInsertDataException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.BankAccountData;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class BankAccountDoa implements BankAccountCrudable<BankAccountData>{
    @Override
    public BankAccountData create(BankAccountData bankAccountData) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            UserAccountData userAccountData = new UserAccountData();
            // TODO: add the id or username so it adds to the right account
            String sql = "insert into bankaccount values(default, ?, ?, ?, true)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, BankAccountData.randomBankAccountNumber());
            ps.setString(2, bankAccountData.getBankAccountName());
            ps.setInt(3, bankAccountData.getBankAccountAmount());
            //ps.setString(4, userAccountData.getUserName());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new BankAccountInsertDataException("The sql statement came back as 0. Please check the information inputted or the String sql above");
            }

            return bankAccountData;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<BankAccountData> findAll() throws IOException {

        ArrayList<BankAccountData> bankAccounts = new ArrayList<>();


        try (Connection conn = ConnectionFactory.getInstance().getConnection();){ // try with resource, because connection extends the interface Auto-closes

            String sql = "select * from bankaccount";

            Statement s = conn.createStatement();

            s.executeQuery(sql);

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {

                BankAccountData bankAccountData = new BankAccountData();
                bankAccountData.setBankAccountNumber(rs.getInt("bank_account_number"));
                bankAccountData.setBankAccountName(rs.getString("bank_account_name"));
                bankAccountData.setBankAccountAmount(rs.getInt("bank_account_amount"));

                bankAccounts.add(bankAccountData);

            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return bankAccounts;
    }

    @Override
    public BankAccountData findById(String bId) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from bankaccount where bank_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(bId));

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                throw new ResourcePersistanceException("Bank account was not found in the database, please check  Bank_ID entered was correct.");
            }

            BankAccountData bankAccountData =new BankAccountData();

            bankAccountData.setBankAccountNumber(rs.getInt("bank_account_number"));
            bankAccountData.setBankAccountName(rs.getString("bank_account_name"));
            bankAccountData.setBankAccountAmount(rs.getInt("bank_account_amount"));

            return bankAccountData;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(String id2, String newBankAccountName) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "update bankaccount set bank_account_name=? where bank_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newBankAccountName);
            ps.setInt(2, Integer.parseInt(id2));

            ps.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(String id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "delete from bankaccount where bank_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id);


            ps.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public BankAccountData deposit(String deposit, String id){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "update bankaccount set bank_account_amount = bank_account_amount + ? where bank_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1 , Integer.parseInt(deposit));
            ps.setInt(2, Integer.parseInt(id));

            ps.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public double withdraw(){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){




            return 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
