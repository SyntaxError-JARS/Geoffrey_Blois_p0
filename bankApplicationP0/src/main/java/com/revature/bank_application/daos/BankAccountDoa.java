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
                bankAccountData.setBankAccountAmount(rs.getInt("bank_account_number"));
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
    public boolean update(String id2, String userName) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }


}
