package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    //private final UserAccountServices userAccountServices = new UserAccountServices(UserAccountDao());
    private final ObjectMapper mapper =new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UserAccountData[] userAccountData = userAccountServices.readUsers();
    }
}
