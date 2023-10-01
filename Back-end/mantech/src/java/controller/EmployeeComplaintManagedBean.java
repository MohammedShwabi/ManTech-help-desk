/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import entities.Compliants;
import entities.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
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
    public String addComplaint() {
        Categories categories = categoriesFacade.find(selectedCategoryId);
        Employees employees = employeesFacade.find(employeeId);
        
        compliants.setCreatedDate(new Date());
        compliants.setCatId(categories);
        compliants.setEmpId(employees);
        compliants.setStatus("waiting");
        
        // edit here to add image
        compliants.setPhoto("test_photo.png");

        compliantsFacade.create(compliants);
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

}
