package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.BankAccountData;
import com.revature.bank_application.services.BankAccountServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

    private final BankAccountServices bankAccountServices;

    private final ObjectMapper mapper;

    // TODO: ADD LOGGER!!!!!!!

    public AccountServlet(BankAccountServices bankAccountServices, ObjectMapper mapper) {
        this.bankAccountServices = bankAccountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //BankAccountCreds bankAccountCreds = mapper.readValue(req.getInputStream(), BankAccountCreds.class);
            BankAccountData bankAccountData = mapper.readValue(req.getInputStream(), BankAccountData.class);
            boolean newBankAccount = bankAccountServices.CreateBankAccount(bankAccountData);

            String payload = mapper.writeValueAsString(newBankAccount);


            resp.getWriter().write("You have successfully created a new user account!");
            resp.getWriter().write(payload);
            resp.setStatus(200);
        }catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        }catch (Exception e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
    }

    }
}
