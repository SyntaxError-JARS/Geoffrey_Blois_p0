package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.services.UserAccountServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final UserAccountServices userAccountServices;
    private final ObjectMapper mapper;

    public UserServlet(UserAccountServices userAccountServices, ObjectMapper mapper) {
        this.userAccountServices = userAccountServices;
        this.mapper = mapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAccountData[] userAccountData = userAccountServices.readUsers();

        String payload = mapper.writeValueAsString(userAccountData);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserAccountData userAccountData = mapper.readValue(req.getInputStream(), UserAccountData.class);
            boolean newUser = userAccountServices.registerAccount(userAccountData);

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("newUser", newUser);

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
