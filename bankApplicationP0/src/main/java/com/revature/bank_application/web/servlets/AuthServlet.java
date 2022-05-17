package com.revature.bank_application.web.servlets;

// Here I am importing a ObjectMapper from the jackson dependency
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank_application.execeptions.AuthenticationException;
import com.revature.bank_application.models.UserAccountData;
import com.revature.bank_application.services.UserAccountServices;
import com.revature.bank_application.web.dto.LoginCreds;

//Here I am importing the HTTPServlet so, I can get a request from postman and send postman a response
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InvalidClassException;

public class AuthServlet extends HttpServlet {

    //I am using private and final so that only this class has access to them and is the only place that these declared variables can be messed with
    private final UserAccountServices userAccountServices;
    private final ObjectMapper mapper;


    // In this AuthServlet method I am shadowing the userAccountServices and mapper so they can be used in other classes but the main variables won't get messed with.
    public AuthServlet(UserAccountServices userAccountServices, ObjectMapper mapper){
        this.userAccountServices = userAccountServices;
        this.mapper = mapper;
    }


    // Here I am leveraging the doPost method from the HttpServlet class and overriding the doPost because there is already a method used in the Http servlet that would
    // already do something as it is already defined.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            // Here I created a LoginCreds class that contains the values I only want to use when a user logs in then setting them to my mapper so it can read the request
            // then check the values needed in postman are right.
            LoginCreds loginCreds = mapper.readValue(req.getInputStream(), LoginCreds.class);

            //Here I am creating an authUser that is set to my userAccountServices.authenticateUser so, I can use it to create a httpSession and set the attribute it
            // to the auth user. In the parenthesis of the authenticateUser I am grabbing the getEmail and getPassword from my login creds which will be used to authenticate my user login.
            UserAccountData authUser = userAccountServices.authenticateUser(loginCreds.getEmail(), loginCreds.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);


            // Here I am setting the response status to 200 which means success and also writing to the postman console that the user has been logged in.
            resp.setStatus(200);
            resp.getWriter().write("You have successfully logged in!");
        }catch(AuthenticationException | InvalidClassException e){
            // When one of these error are throw above it will output a 404 error and write the exception message to the console. 400 codes are client side issues.
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        } catch (Exception e){
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }
    }

}
