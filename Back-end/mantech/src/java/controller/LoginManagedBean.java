/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.EmployeesFacade;

/**
 *
 * @author dell
 */
@Named(value = "loginManagedBean")
@SessionScoped
//@RequestScoped
public class LoginManagedBean implements Serializable {

    // Variables for email and password and message
    private String email;
    private String password;
    private String message;

    // Variables for logged in employee details
    private Employees loggedInEmployee;

    @EJB
    private EmployeesFacade employeesFacade;

    // Getter and setter methods for email and password and message
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }

    public String login() {
        // first delete the old message 
        message = "";
        
        // Check if email and password are valid
        loggedInEmployee = employeesFacade.login(email, password);

        if (loggedInEmployee != null) {
            // to get the department id 
            // 7 for admin, 6 for technician and other for normal employee
            int depID = loggedInEmployee.getDepId().getId();

            // Store the employee in the session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("loggedInEmployee", loggedInEmployee);

            // Redirect the user to the appropriate page based on their role
            return redirectToUserPage(depID);
        } else {
            message = "Invalid email or password.";
            return "login";
        }
    }

    // Logout method to clear the session and redirect to the login page
    public String logout() {
        // Get the current FacesContext
        FacesContext context = FacesContext.getCurrentInstance();

        // Get the ExternalContext
        ExternalContext externalContext = context.getExternalContext();

        // Get the HttpSession
        HttpSession session = (HttpSession) externalContext.getSession(false);

        // Check if the user is logged in
        if (session != null) {
            // Remove the loggedInEmployee attribute from the session
            session.removeAttribute("loggedInEmployee");

            // Remove session-managed beans from the session
            session.invalidate();
        }

        // Redirect to the login page
        gotoLogin(context);

        // Return null to indicate a successful logout
        return null;
    }

    // Handle redirection to login page
    public static void gotoLogin(FacesContext context) {
        // Configure the Flash scope to keep messages during a redirect
        context.getExternalContext().getFlash().setKeepMessages(true);

        // Create an error message and add it to the FacesContext
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have been logged out.", null));

        // Obtain the NavigationHandler associated with the application
        NavigationHandler navHandler = context.getApplication().getNavigationHandler();

        // Trigger a navigation to the login page with a redirect
        navHandler.handleNavigation(context, null, "/login.xhtml?faces-redirect=true");
    }

    public static Employees getCurrentUser() {

        // Get the FacesContext
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Get the ExternalContext
        ExternalContext externalContext = facesContext.getExternalContext();

        // Get the HttpSession
        HttpSession session = (HttpSession) externalContext.getSession(false);

        // Access the loggedInEmployee object from the session
        Employees loggedInUser = (Employees) session.getAttribute("loggedInEmployee");

        return loggedInUser;
    }

    // Add a method to check if the user is logged in
    public boolean isloggedIn() {
        return getCurrentUser() != null;
    }

    private String redirectToUserPage(int depID) {
        switch (depID) {
            case 7:
                // If department ID is 7, go to the admin page
                return "/admin/complaint/view?faces-redirect=true";
            case 6:
                // If department ID is 6, go to the technician page
                return "/technician/view?faces-redirect=true";
            default:
                // For other departments, go to the employee page
                return "/employee/view?faces-redirect=true";
        }
    }

}
