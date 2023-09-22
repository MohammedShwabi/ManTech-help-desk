/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author dell
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }
    
    public String login() {
        // Perform authentication logic here (e.g., check credentials in the database)

        // Simulate a successful login
        String departmentName = "Allied system"; // Replace with actual logic to get department name
        if (departmentName == null) {
            return "admin"; // Redirect to admin page
        } else if ("Allied system".equals(departmentName)) {
            return "technical"; // Redirect to technical page
        } else {
            return "employee"; // Redirect to employee page
        }
    }
    
}
