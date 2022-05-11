package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.AuthenticationException;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.services.UserAccountServices;
import com.revature.bank_application.web.dto.LoginCreds;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InvalidClassException;

//@WebService("/auth")
public class AuthServlet extends HttpServlet {

    private final UserAccountServices userAccountServices;
    private final ObjectMapper mapper;

    public AuthServlet(UserAccountServices userAccountServices, ObjectMapper mapper){
        this.userAccountServices = userAccountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        // TODO: wrap in a try catch block with a exception for auth and a 404
        try {
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            UserAccountData authUser = userAccountServices.authenticateUser(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);

            resp.setStatus(200);

            resp.getWriter().write("You have successfully logged in!");
        }catch(AuthenticationException | InvalidClassException e){
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

}
