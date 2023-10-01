/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Departments;
import entities.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import model.EmployeesFacade;

/**
 *
 * @author dell
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String email;
    private String password;
    private String message;

//    @ManagedProperty(value = "#{userBean}")
    private Employees loggedInEmployee;

    @EJB
    private EmployeesFacade employeesFacade;

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

    public Employees getLoggedInEmployee() {
        return loggedInEmployee;
    }

//    public void setLoggedInEmployee(Employees loggedInEmployee) {
//        this.loggedInEmployee = loggedInEmployee;
//    }

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }

    public String login() {
        // Check if email and password are valid
        loggedInEmployee = employeesFacade.login(email, password);

        if (loggedInEmployee != null) {
            // to get the department id 
            // null for admin
            // "Allied system" for technical
            // other for normal employee
            Departments department = loggedInEmployee.getDepId();

            // save the current employee details
            saveEmployeeDetails(loggedInEmployee);

            // Redirect the user to the appropriate page based on their role
            if (department == null) {
                // If department is null, go to the admin page
                return "admin?faces-redirect=true";
            } else if ("Allied system".equals(department.getName())) {
                // If department is 'Allied system', go to the technician page
                return "technician?faces-redirect=true";
            } else {
                // For other departments, go to the employee page
                return "employee?faces-redirect=true";
            }
        } else {
            message = "Invalid email or password.";
            return "login";
        }
    }

    public void saveEmployeeDetails(Employees loggedInEmployee) {

        // Store the employee in the session
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInEmployee", loggedInEmployee);
    }

    // use this in any page to check the user
    public static void check_user() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Employees loggedInEmployeea = (Employees) facesContext.getExternalContext().getSessionMap().get("loggedInEmployee");

        // Check if loggedInEmployee is null, which means the user is not logged in or the session has expired
        if (loggedInEmployeea != null) {
            System.out.println("hi");
            // You can access employee properties like loggedInEmployee.getFullName(), loggedInEmployee.getDepId(), etc.
        } else {
            // Handle the case when the user is not logged in or the session has expired
            System.out.println("buy");
        }
    }

    // Logout method to clear the session and redirect to the login page
    public String logout() {
        loggedInEmployee = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
}
