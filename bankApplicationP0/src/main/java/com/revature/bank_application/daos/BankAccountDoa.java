package main.java.com.revature.bank_application.daos;

import main.java.com.revature.bank_application.execeptions.BankAccountInsertDataException;
import main.java.com.revature.bank_application.models.BankAccountData;
import main.java.com.revature.bank_application.models.UserAccountData;
import main.java.com.revature.bank_application.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountDoa implements BankAccountCrudable<BankAccountData>{
    @Override
    public BankAccountData create(BankAccountData bankAccountData) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            // TODO: add the id or username so it adds to the right account
            String sql = "insert into bankaccount values(default, ?, ?, ?, true)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, BankAccountData.randomBankAccountNumber());
            ps.setString(2, bankAccountData.getBankAccountName());
            ps.setInt(3, bankAccountData.getBankAccountAmount());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new BankAccountInsertDataException("The sql statement came back as 0. Please check the information inputted or the String sql above");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserAccountData[] findAll() throws IOException {
        return new UserAccountData[0];
    }

    @Override
    public BankAccountData findById(String id) {
        return null;
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
