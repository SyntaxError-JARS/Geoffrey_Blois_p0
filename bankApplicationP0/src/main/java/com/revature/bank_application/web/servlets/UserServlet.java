package com.revature.bank_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.InvalidRequestException;
import com.revature.bank_application.execeptions.ResourcePersistanceException;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.services.UserAccountServices;
import com.revature.bank_application.util.logging.Logger;
import com.revature.bank_application.web.dto.UserDeleteCreds;
import com.revature.bank_application.web.dto.UserUpdateCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UserServlet extends HttpServlet {

    private final UserAccountServices userAccountServices;
    private final ObjectMapper mapper;
    private final Logger logger = Logger.getLogger(true);

    public UserServlet(UserAccountServices userAccountServices, ObjectMapper mapper) {
        this.userAccountServices = userAccountServices;
        this.mapper = mapper;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {

        if(!checkAuth(req, resp)) return;

        if(req.getParameter("id") != null){
            UserAccountData userAccountData;
            try {
                resp.getWriter().write("Grabbing User! \n");
                userAccountData = userAccountServices.findByID(req.getParameter("id"));
            }catch (ResourcePersistanceException e){
                logger.warn(e.getMessage());
                resp.setStatus(404);
                resp.getWriter().write(e.getMessage());
                return;
            }
            String payload = mapper.writeValueAsString(userAccountData);
            resp.getWriter().write("You have successfully looked up a new user account!\n");
            resp.getWriter().write(payload);
            return;

        }

        ArrayList<UserAccountData> userAccountData = userAccountServices.readUsers();

        String payload = mapper.writeValueAsString(userAccountData);

        resp.getWriter().write(payload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserAccountData userAccountData = mapper.readValue(req.getInputStream(), UserAccountData.class);
            boolean newUser = userAccountServices.registerAccount(userAccountData);

            String payload = mapper.writeValueAsString(newUser);

            //HttpSession httpSession = req.getSession(true);
            //httpSession.setAttribute("newUser", newUser);

            // TODO: Replace the HttpSession with this for the rest of the methods.
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!checkAuth(req, resp)) return;
        try {
            UserDeleteCreds userDeleteCreds = mapper.readValue(req.getInputStream(), UserDeleteCreds.class);
            UserAccountData deleteUser = userAccountServices.deleteAccount(userDeleteCreds.getId());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("deleteUser", deleteUser);

            resp.setStatus(200);
            resp.getWriter().write("You have successfully deleted a new user account!");

        }catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        }catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp)) return;
        try {
            UserUpdateCreds userUpdateCreds = mapper.readValue(req.getInputStream(), UserUpdateCreds.class);
            boolean updateUser = userAccountServices.updateAccount(userUpdateCreds.getId2(), userUpdateCreds.getUserName());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("updateUser", updateUser);

            resp.setStatus(200);
            resp.getWriter().write("You have successfully updated a new user account!");

        }catch (InvalidRequestException | ResourcePersistanceException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        }catch (Exception e) {
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
