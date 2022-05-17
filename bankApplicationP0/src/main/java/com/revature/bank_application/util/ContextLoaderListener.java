package com.revature.bank_application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.daos.BankAccountDoa;
import com.revature.bank_application.daos.UserAccountDao;
import com.revature.bank_application.services.BankAccountServices;
import com.revature.bank_application.services.UserAccountServices;
import com.revature.bank_application.web.servlets.AccountServlet;
import com.revature.bank_application.web.servlets.AuthServlet;
import com.revature.bank_application.web.servlets.DepositeAndWithDrawServlets;
import com.revature.bank_application.web.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //Here I am creating a new ObjectMapper and new useraccountDoa + a bankaccountDoa. The reason for this is so I don't have to create all of these in each
        //servlet. This is the same reasoning for the services as well.
        ObjectMapper mapper = new ObjectMapper();
        UserAccountDao userAccountDao = new UserAccountDao();
        BankAccountDoa bankAccountDoa = new BankAccountDoa();

        UserAccountServices userAccountServices = new UserAccountServices(userAccountDao);
        BankAccountServices bankAccountServices = new BankAccountServices(bankAccountDoa);

        //Here I am creating a new instance of each of my servlets.
        AuthServlet authServlet = new AuthServlet(userAccountServices, mapper);
        UserServlet userServlet = new UserServlet(userAccountServices, mapper);
        AccountServlet accountServlet = new AccountServlet(bankAccountServices, mapper);
        DepositeAndWithDrawServlets depositeAndWithDraw = new DepositeAndWithDrawServlets(bankAccountServices, mapper);


        //These are the /commands that I would have in a url or in postman after .com/test-app/
        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("UserServlet", userServlet).addMapping("/user/*");
        context.addServlet("accountServlet", accountServlet).addMapping("/bank_account/*");
        context.addServlet("depositAndWithDraw", depositeAndWithDraw).addMapping("/depositAndWithDraw");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
