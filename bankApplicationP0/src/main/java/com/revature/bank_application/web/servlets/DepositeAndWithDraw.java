package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.models.BankAccountData;
import com.revature.bank_application.services.BankAccountServices;
import com.revature.bank_application.web.dto.BankDeleteCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepositeAndWithDraw extends HttpServlet {

    private final BankAccountServices bankAccountServices;

    private final ObjectMapper mapper;


    public DepositeAndWithDraw(BankAccountServices bankAccountServices, ObjectMapper mapper) {
        this.bankAccountServices = bankAccountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            BankDeleteCreds deposit = mapper.readValue(req.getInputStream(), BankDeleteCreds.class);
            BankAccountData newBankAmount = bankAccountServices.deposit(deposit.getDeposit(), deposit.getId());

            String payload = mapper.writeValueAsString(newBankAmount);

            resp.getWriter().write("You have Successfully deposited money into your account");
            resp.getWriter().write(payload);
            resp.setStatus(200);
    }
}
