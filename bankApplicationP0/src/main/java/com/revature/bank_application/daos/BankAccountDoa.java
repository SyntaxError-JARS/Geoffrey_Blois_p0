package main.java.com.revature.bank_application.daos;

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

            String sql = "insert into bankaccount values(default, ?, ?, ?, true";

            PreparedStatement ps = conn.prepareStatement(sql);

           // ps.setInt(1, Integer.parseInt());



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
