package com.revature.bank_application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.services.UserAccountServices;
import com.revature.bank_application.web.servlets.AuthServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper mapper = new ObjectMapper();
        UserAccountDao userAccountDao = new UserAccountDao();
        UserAccountServices userAccountServices = new UserAccountServices(userAccountDao);

        AuthServlet authServlet = new AuthServlet(userAccountServices, mapper);
        // TODO: add a user servlet

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
