package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.services.UserAccountServices;
import com.revature.bank_application.web.dto.LoginCreds;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebService("/auth")
public class AuthServlet extends HttpServlet {

    private final UserAccountServices userAccountServices;
    private final ObjectMapper mapper = new ObjectMapper();

    public AuthServlet(UserAccountServices userAccountServices, ObjectMapper mapper){
        this.userAccountServices = userAccountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // TODO: wrap in a try catch block with a exception for auth and a 404
        LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

         //UserAccountData authUser = userAccountServices TODO: Need to add an authenticate User method use email first then password.

        HttpSession httpSession = req.getSession(true);
        //httpSession.setAttribute("authUser", authUser);

        //resp.getStatus(200);

        //getwriter.write("login in)
    }

}
