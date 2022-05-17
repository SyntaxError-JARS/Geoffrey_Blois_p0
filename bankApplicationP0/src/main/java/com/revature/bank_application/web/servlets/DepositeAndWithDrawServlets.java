package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.BankAccountData;
import com.revature.bank_application.services.BankAccountServices;
import com.revature.bank_application.web.dto.BankDepositCreds;
import com.revature.bank_application.web.dto.BankWithdrawCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DepositeAndWithDrawServlets extends HttpServlet {

    private final BankAccountServices bankAccountServices;

    private final ObjectMapper mapper;


    public DepositeAndWithDrawServlets(BankAccountServices bankAccountServices, ObjectMapper mapper) {
        this.bankAccountServices = bankAccountServices;
        this.mapper = mapper;
    }

    // So do a withdrawal or deposit in postman you need to use a doPut. Also, in the url you need to use a ?deposit or withdraw
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;

        // This if statement is checking if the ?=deposit in the url is there, or it will check if the parameter in withdraw in the other if statement.
        // Based on what parameter is set it will add funds or subtract funds.
        // The reason this is there is so that I can do multiple doPut calls in one method instead of many a class for each doPut call
        // The only reason I put this is a separate servlet is that the account servlet was getting to long.
        if (req.getParameter("deposit") != null) {
            try {
                BankDepositCreds deposit = mapper.readValue(req.getInputStream(), BankDepositCreds.class);
                BankAccountData newBankAmount = bankAccountServices.deposit(deposit.getDeposit(), deposit.getId());

                String payload = mapper.writeValueAsString(newBankAmount);

                resp.getWriter().write("You have Successfully deposited money into your account");
                resp.getWriter().write(payload);
                resp.setStatus(200);
            } catch (InvalidRequestException | ResourcePersistanceException e) {
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            } catch (Exception e) {
                resp.setStatus(409);
                resp.getWriter().write(e.getMessage());
            }
        }

        if (req.getParameter("withdraw") != null) {
            try {

                BankWithdrawCreds bankWithdrawCreds = mapper.readValue(req.getInputStream(), BankWithdrawCreds.class);
                BankAccountData lossingMoney = bankAccountServices.withDraw(bankWithdrawCreds.getWithdraw(), bankWithdrawCreds.getId());


                String payload = mapper.writeValueAsString(lossingMoney);

                resp.getWriter().write("You have Successfully withdrew money from your account");
                resp.getWriter().write(payload);
                resp.setStatus(200);
            } catch (InvalidRequestException | ResourcePersistanceException e) {
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
            } catch (Exception e) {
                resp.setStatus(409);
                resp.getWriter().write(e.getMessage());
            }
        }


    }
    protected boolean checkAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession.getAttribute("authUser") == null){
            resp.getWriter().write("Unauthorized request - not logged in as register user ");
            resp.setStatus(401);
            return false;
        }
        return true;
    }


}
