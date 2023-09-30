/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import entities.ComplaintByMonth;
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
import model.ComplaintByMonthFacade;
import model.DepartmentsFacade;

/**
 *
 * @author dell
 */
@Named(value = "closedComplaintManagedBean")
@SessionScoped
public class ClosedComplaintManagedBean implements Serializable {

    @EJB
    private ComplaintByMonthFacade complaintByMonthFacade;

    @EJB
    private DepartmentsFacade departmentsFacade;

    @EJB
    private CategoriesFacade categoriesFacade;

    // to hold the selected filter options
    private int selectedCategory = 0; // Default to "All Categories"
    private int selectedDepartment = 0; // Default to "All Departments"
    private String selectedPriority = "all";  // Default to "All Priority"
    
    
    // these for filtering based on raido btn
    private String selectedFilter = "day";

    // this if the filter is by day
    private Date selectedDate;

    // this if the filter is by week
    private String selectedWeek;

    // this if the filter is by month
    private String selectedYearMonth;

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
    
    // getter and setter for all radio filter
    public String getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedFilter(String selectedFilter) {
        this.selectedFilter = selectedFilter;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedWeek() {
        return selectedWeek;
    }

    public void setSelectedWeek(String selectedWeek) {
        this.selectedWeek = selectedWeek;
    }

    public String getSelectedYearMonth() {
        return selectedYearMonth;
    }

    public void setSelectedYearMonth(String selectedYearMonth) {
        this.selectedYearMonth = selectedYearMonth;
    }

    // to get list of filtered complaints
    public List<ComplaintByMonth> getFilteredComplaints() {

        return complaintByMonthFacade.filterClosedComplaints(selectedCategory, selectedDepartment, selectedPriority,
                selectedFilter, selectedDate, selectedYearMonth, selectedWeek);
    }

    // to rest the filter
    public String restFilter() {
        
        selectedFilter = "day";
        selectedDate = null;
        selectedWeek = null;
        selectedYearMonth = null;

        return "closedComplaints";
    }

    /**
     * Creates a new instance of ClosedComplaintManagedBean
     */
    public ClosedComplaintManagedBean() {
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
                filterItems.addAll(getSelectItems(departmentsFacade.findEmployeeDepartments(6)));
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
}
