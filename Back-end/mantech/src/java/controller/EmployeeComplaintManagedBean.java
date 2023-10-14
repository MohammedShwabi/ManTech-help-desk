/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import entities.Compliants;
import entities.Employees;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import model.CategoriesFacade;
import model.CompliantsFacade;

/**
 *
 * @author dell
 */
@Named(value = "employeeComplaintManagedBean")
@SessionScoped
public class EmployeeComplaintManagedBean implements Serializable {

    @EJB
    private CategoriesFacade categoriesFacade;

    @EJB
    private CompliantsFacade compliantsFacade;

    private Compliants compliants;

    // possibale value:
    // all, waiting, pending, closed
    private String selectedComplaintStatus = "all"; // Default status

    // save the choosed item
    private Integer selectedCategoryId;

    // get the current logged in user from the session
    Employees currentUser = LoginManagedBean.getCurrentUser();

    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Integer getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(Integer selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public Compliants getCompliants() {
        return compliants;
    }

    public void setCompliants(Compliants compliants) {
        this.compliants = compliants;
    }

    public String getSelectedComplaintStatus() {
        return selectedComplaintStatus;
    }

    public void setSelectedComplaintStatus(String selectedComplaintStatus) {
        this.selectedComplaintStatus = selectedComplaintStatus;
    }

    private List<SelectItem> categoryItems;

    // create a SelectItem which contains both the displayed label and the corresponding value.
    public List<SelectItem> getCategoryItems() {
        categoryItems = new ArrayList<>();
        List<Categories> categories = categoriesFacade.findAll();
        categories.forEach((cat) -> {
            categoryItems.add(new SelectItem(cat.getId(), cat.getName()));
        });
        return categoryItems;
    }

    /**
     * Creates a new instance of EmployeeComplaintManagedBean
     */
    public EmployeeComplaintManagedBean() {
    }

    // to filter complaints by status
    public List<Compliants> findEmployeeComplaint() {
        return compliantsFacade.findEmployeeComplaint(currentUser.getId(), selectedComplaintStatus);
    }

    // to go to complaintDetails page
    public String showDetails(Compliants c) {
        // Store the selected complaint in a property for display in a different view
        this.compliants = c;
        return "complaintDetails"; // Navigate to a different JSF page to display details
    }

    public String gotoAdd() {
        // Reset the compliants object
        compliants = new Compliants();
        // Reset the selectedCategoryId
        selectedCategoryId = null;
        return "add";
    }

    // Method to add a new employee
    public String addComplaint() throws IOException {
        Categories categories = categoriesFacade.find(selectedCategoryId);

        compliants.setCreatedDate(new Date());
        compliants.setCatId(categories);
        compliants.setEmpId(currentUser);
        compliants.setStatus("waiting");

        if (file == null) {
            compliants.setPhoto("defult_image");
        } else {
            String pathImage = ImageUploader.upload(file, "complaints_photos");
            compliants.setPhoto(pathImage);
        }

        // to Create and send the email with the complaint description in the subject
        EmailSender emailSender = new EmailSender();
        boolean result = emailSender.sendNewComplaintEmail(compliants.getTitle(), compliants.getDescription());

        if (result) {
            // save the complaint to the database
            compliantsFacade.create(compliants);

            // to reset the object
            this.resetComplaint();

            // go to the view page
            return "view?faces-redirect=true"; // Redirect to a view page
        } else {
            // display error message and stay in the same page
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("failed to save complaint and send email");
            context.addMessage("employee_complaint:error_message", message); // "form" is the name of your form
            return null; // Return null to stay on the same page
        }

    }

    public String updateComplaint() throws IOException {

        if (file != null) {
            String pathImage = ImageUploader.upload(file, "complaints_photos");

            // Check if the complaint already has a photo
            if (compliants.getPhoto() != null && !compliants.getPhoto().equals("default_image")) {
                // Delete the old photo
                ImageUploader.deleteOldPhoto("complaints_photos", compliants.getPhoto());
            }

            compliants.setPhoto(pathImage);
        }

        compliantsFacade.edit(compliants);
        this.resetComplaint();
        return "view"; // Redirect to a view page
    }

    // reset the complaint object
    public void resetComplaint() {
        // Clear the object
        compliants = new Compliants();
        // Clear the selectedDepartmentId
        selectedCategoryId = null;
        // Clear file
        file = null;
    }

    // to delete the complaint
    public String delete() {
        // Check if the complaint already has a photo
        if (compliants.getPhoto() != null && !compliants.getPhoto().equals("default_image")) {
            // Delete the old photo
            ImageUploader.deleteOldPhoto("complaints_photos", compliants.getPhoto());
        }

        compliantsFacade.remove(compliants);
        return "view?faces-redirect=true"; // Navigate to a different JSF page to display details 
    }

    // to resend the complaint
    public String resend() {
        compliants.setResend(true);
        compliantsFacade.edit(compliants);
        return "complaintDetails?faces-redirect=tru1?2e"; // Navigate to a different JSF page to display details 
    }

    // to show or hide the resend btn
    public boolean renderResend() {
        return !compliants.getResend() && isWaitingComplaintOverdue(compliants.getCreatedDate());
    }

    public boolean isWaitingComplaintOverdue(Date createDate) {
        // Convert the java.util.Date to java.time.LocalDate
        LocalDate closedLocalDate = createDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the difference in days
        long daysDifference = ChronoUnit.DAYS.between(closedLocalDate, currentDate);

        // Check if the difference is 2 days or more
        return daysDifference >= 2;
    }

}
