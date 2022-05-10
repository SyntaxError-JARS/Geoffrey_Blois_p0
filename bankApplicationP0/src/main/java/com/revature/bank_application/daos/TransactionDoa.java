package com.revature.bank_application.daos;

import com.revature.bank_application.models.TransactionData;
import com.revature.bank_application.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionDoa implements TransactionsDoaInterface<TransactionData>{
    @Override
    public TransactionData withDraw(int amount)  {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){





            // TODO: replace this!!!!!!!!!!!!!!!!!!!
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TransactionData deposit(int object) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){




            // TODO: replace this!!!!!!!!!!!!!!!!!!!
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
