/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import entities.Compliants;
import entities.Departments;
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
import model.DepartmentsFacade;

/**
 *
 * @author dell
 */
@Named(value = "pendingComplaintManagedBean")
@SessionScoped
public class PendingComplaintManagedBean implements Serializable {

    @EJB
    private CompliantsFacade compliantsFacade;

    @EJB
    private DepartmentsFacade departmentsFacade;

    @EJB
    private CategoriesFacade categoriesFacade;

    // to hold the selected filter options
    private int selectedCategory = 0; // Default to "All Categories"
    private int selectedDepartment = 0; // Default to "All Departments"
    private String selectedPriority = "all";  // Default to "All Priority"
    private int selectedDays = 0;  // Default to "All days"

    private List<Compliants> filteredComplaints;

    // getter and setter for all selected filter options
    public int getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(int selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public int getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(int selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public String getSelectedPriority() {
        return selectedPriority;
    }

    public void setSelectedPriority(String selectedPriority) {
        this.selectedPriority = selectedPriority;
    }

    public int getSelectedDays() {
        return selectedDays;
    }

    public void setSelectedDays(int selectedDays) {
        this.selectedDays = selectedDays;
    }

    // to get list of filtered complaints
    public List<Compliants> getFilteredComplaints() {
        filteredComplaints = compliantsFacade.filterPendingComplaints(selectedCategory, selectedDepartment, selectedPriority, selectedDays);
        return filteredComplaints;
    }

    /**
     * Creates a new instance of PendingComplaintManagedBean
     */
    public PendingComplaintManagedBean() {
    }

    // For department and category drop-down list
    // Create a SelectItem which contains both the displayed label and the corresponding value.
    public List<SelectItem> getFilterItems(String selectedFilterType) {

        List<SelectItem> filterItems = new ArrayList<>();

        switch (selectedFilterType) {
            case "cat":
                filterItems.addAll(getSelectItems(categoriesFacade.findAll()));
                break;
            case "dep":
                filterItems.addAll(getSelectItems(departmentsFacade.findEmployeeDepartments()));
                break;
        }
        return filterItems;
    }

    private List<SelectItem> getSelectItems(List<?> entities) {
        List<SelectItem> selectItems = new ArrayList<>();
        entities.forEach((entity) -> {
            int id = -1; // Default ID value
            String label = ""; // Default label value

            if (entity instanceof Categories) {
                Categories category = (Categories) entity;
                id = category.getId();
                label = category.getName();
            } else if (entity instanceof Departments) {
                Departments department = (Departments) entity;
                id = department.getId();
                label = department.getName();
            }

            selectItems.add(new SelectItem(id, label));
        });

        return selectItems;
    }

    // to get the "pending for" column using theis format 2 days 4 hours 3 mints
    public String getPendingTime(Date startDate) {
        
        // get the current data
        Date currentDate = new Date();
        
        return ComplaintManagedBean.getSolvedTime(startDate, currentDate);
    }
    
    // For pending days drop-down list
    private List<SelectItem> pendingDaysOptions;

    public List<SelectItem> getPendingDaysOptions() {
        if (pendingDaysOptions == null) {
            pendingDaysOptions = new ArrayList<>();
            pendingDaysOptions.add(new SelectItem(0, "All Days")); // Default option
            pendingDaysOptions.add(new SelectItem(1, "1 Day"));
            pendingDaysOptions.add(new SelectItem(2, "2 Days"));
            pendingDaysOptions.add(new SelectItem(3, "3 Days"));
            pendingDaysOptions.add(new SelectItem(7, "7 Days"));
            pendingDaysOptions.add(new SelectItem(30, "30 Days"));
        }
        return pendingDaysOptions;
    }
    
}
