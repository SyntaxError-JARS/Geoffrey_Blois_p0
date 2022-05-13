package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.BankAccountData;
import com.revature.bank_application.services.BankAccountServices;
import com.revature.bank_application.web.dto.BankDeleteCreds;
import com.revature.bank_application.web.dto.UpdateBankAccountCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AccountServlet extends HttpServlet {

    private final BankAccountServices bankAccountServices;

    private final ObjectMapper mapper;

    // TODO: ADD LOGGER!!!!!!!

    public AccountServlet(BankAccountServices bankAccountServices, ObjectMapper mapper) {
        this.bankAccountServices = bankAccountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp)) return;

        if(req.getParameter("bank_id") != null){
            BankAccountData bankAccountData;
            try {
                resp.getWriter().write("Grabbing User! \n");
                bankAccountData = bankAccountServices.findbyId(req.getParameter("bank_id"));
            }catch (ResourcePersistanceException e){
                //logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(bankAccountData);
            resp.getWriter().write("You have successfully looked up a bank account!\n");
            resp.getWriter().write(payload);
            return;
        }

        ArrayList<BankAccountData> bankAccountData = bankAccountServices.findAll();

        String payload = mapper.writeValueAsString(bankAccountData);

        resp.getWriter().write(payload);

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
        } catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UpdateBankAccountCreds newBankName = mapper.readValue(req.getInputStream(), UpdateBankAccountCreds.class);
            boolean newBankAccountName = bankAccountServices.updateBankAccountName(newBankName.getId2(), newBankName.getNewBankAccountName());

            String payload = mapper.writeValueAsString(newBankAccountName);

            resp.getWriter().write("You have successfully updated your Bank Account Name");
            resp.getWriter().write(payload);
            resp.setStatus(200);
        }catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BankDeleteCreds deleteBankAccount = mapper.readValue(req.getInputStream(), BankDeleteCreds.class);
            boolean deletedBank = bankAccountServices.deleteBankAccount(deleteBankAccount.getId());

            String payload = mapper.writeValueAsString(deletedBank);

            resp.getWriter().write("You have successfully deleted your account");
            resp.getWriter().write(payload);
            resp.setStatus(200);
        }catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
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
