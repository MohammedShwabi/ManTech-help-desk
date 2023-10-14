/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Employees;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import model.EmployeesFacade;

/**
 *
 * @author Almomyz
 */
@Named(value = "profileManagedBean")
@SessionScoped
public class ProfileManagedBean implements Serializable {

    @EJB
    private EmployeesFacade employeesFacade;

    // get the current logged in user from the session
    private Employees employees = LoginManagedBean.getCurrentUser();

    String OldPassword;
    String NewPassword;
    String ConfirmNewPassword;
    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String OldPassword) {
        this.OldPassword = OldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }

    public String getConfirmNewPassword() {
        return ConfirmNewPassword;
    }

    public void setConfirmNewPassword(String ConfirmNewPassword) {
        this.ConfirmNewPassword = ConfirmNewPassword;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public String passwordReset() {

        String hashedEnteredPassword = EmployeeManagedBean.hashPassword(OldPassword);

        if (hashedEnteredPassword.compareTo(employees.getPassword()) != 0) {

            // add error message if old password is not correct
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("old Password is not correct");
            context.addMessage("rest_form:old_password", message); // "form" is the name of your form

            return null; // Return null to stay on the same page

        } else if (NewPassword.compareTo(ConfirmNewPassword) != 0) {

            // add error message if new and Confirm  password does not match
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("new password and confirm new password does not match.");
            context.addMessage("rest_form:Confirm_New_Password", message); // "form" is the name of your form

            return null; // Return null to stay on the same page

        } else {

            String hashedNewPassword = EmployeeManagedBean.hashPassword(NewPassword);
            // save the new value
            employees.setPassword(hashedNewPassword);
            employeesFacade.edit(employees);
            // to rest variable
            OldPassword = "";
            NewPassword = "";
            ConfirmNewPassword = "";

            return "profile?faces-redirect=true";
        }
    }

    public String editImage() throws IOException {
        if (file != null) {
            // upload the new image
            String pathImage = ImageUploader.upload(file, "profiles_photos");

            // Check if the employee already has a photo
            if (employees.getPhoto() != null && !employees.getPhoto().equals("profile.svg")) {
                // Delete the old photo
                ImageUploader.deleteOldPhoto("profiles_photos", employees.getPhoto());
            }
            // update photo name in database
            employees.setPhoto(pathImage);
            employeesFacade.edit(employees);
            
            // reset the file object
            file = null;
            
            return "profile?faces-redirect=true";
        }
        return null;
    }

    /**
     * Creates a new instance of ProfileManagedBean
     */
    public ProfileManagedBean() {
    }

}
