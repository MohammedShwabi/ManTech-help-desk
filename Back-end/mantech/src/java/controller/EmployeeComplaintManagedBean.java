/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import entities.Compliants;
import entities.Employees;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import model.CategoriesFacade;
import model.CompliantsFacade;
import model.EmployeesFacade;

/**
 *
 * @author dell
 */
@Named(value = "employeeComplaintManagedBean")
@SessionScoped
public class EmployeeComplaintManagedBean implements Serializable {

    @EJB
    private EmployeesFacade employeesFacade;

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

    // it should get it from the session after the user login
    int employeeId = 2;

    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public CompliantsFacade getCompliantsFacade() {
        return compliantsFacade;
    }

    public void setCompliantsFacade(CompliantsFacade compliantsFacade) {
        this.compliantsFacade = compliantsFacade;
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
        return compliantsFacade.findEmployeeComplaint(employeeId, selectedComplaintStatus);
    }

    // to go to complaintDetails page
    public String showDetails(Compliants c) {
        // Store the selected complaint in a property for display in a different view
        this.compliants = c;
        return "complaintDetails?faces-redirect=true"; // Navigate to a different JSF page to display details
    }

    public String gotoAdd() {
        // Reset the Employee object and selected department
        compliants = new Compliants();
        return "add";
    }

    // Method to add a new employee
    public String addComplaint() throws IOException {
        Categories categories = categoriesFacade.find(selectedCategoryId);
        Employees employees = employeesFacade.find(employeeId);

        compliants.setCreatedDate(new Date());
        compliants.setCatId(categories);
        compliants.setEmpId(employees);
        compliants.setStatus("waiting");

        if (file == null) {
            compliants.setPhoto("defult image");
        } else {
            String pathImage = upload();
            compliants.setPhoto(pathImage);
        }
        compliantsFacade.create(compliants);
        this.resetComplaint();
        return "view"; // Redirect to a view page
    }
    
    
    public String updateComplaint() throws IOException {

        if (file == null) {
            compliants.setPhoto("defult image");
        } else {
            String pathImage = upload();
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
    }

    public String upload() throws IOException {
        String fileName = file.getSubmittedFileName();
        InputStream fileContent = file.getInputStream();
        String uploadDirectory = "C:\\Users\\Almomyz\\Documents\\GitHub\\ManTech-help-desk\\Back-end\\mantech\\web\\upload\\complaints_photos\\";
        String filePath = uploadDirectory + fileName;
        try {

            // Process the uploaded file as needed (e.g., save it to a directory, store it in a database, etc.)
            // Example: Save the file in a specific directory inside your project
            // Create a file object representing the destination file
            File destinationFile = new File(filePath);

            // Use Java I/O streams to save the file
            FileOutputStream outputStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();

            // Optionally, you can show a success message to the user
        } catch (IOException e) {
            // Handle any exceptions that may occur during the file upload process
            e.printStackTrace();
        }

        System.out.println(filePath);

        return fileName;
    }

    // to delete the complaint
    public String delete() {
        compliantsFacade.remove(compliants);
        return "view?faces-redirect=true"; // Navigate to a different JSF page to display details 
    }

    // to resend the complaint
    public String resend() {
        compliants.setResend(true);
        compliantsFacade.edit(compliants);
        return "complaintDetails?faces-redirect=true"; // Navigate to a different JSF page to display details 
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
