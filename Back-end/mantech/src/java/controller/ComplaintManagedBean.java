/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import entities.Compliants;
import entities.Departments;
import entities.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import model.CategoriesFacade;
import model.CompliantsFacade;
import model.DepartmentsFacade;
import model.EmployeesFacade;

/**
 *
 * @author dell
 */
@Named(value = "complaintsBean")
@SessionScoped
public class ComplaintManagedBean implements Serializable {

    @EJB
    private DepartmentsFacade departmentsFacade;

    @EJB
    private CategoriesFacade categoriesFacade;

    @EJB
    private CompliantsFacade compliantsFacade;

    @EJB
    private EmployeesFacade employeesFacade;

    // To save the selected list ID
    private int selectedFilterID = 0; // Default to "All"
    /*
        allower value for first param:
        "techId"
        "empId.depId"
        "catId"
     */
    private String selectedFilterType = "techId"; // Default filter

    public int getSelectedFilterID() {
        return selectedFilterID;
    }

    public void setSelectedFilterID(int selectedFilterID) {
        this.selectedFilterID = selectedFilterID;
    }

    public String getSelectedFilterType() {
        return selectedFilterType;
    }

    public void setSelectedFilterType(String selectedFilterType) {
        this.selectedFilterType = selectedFilterType;
    }

    // to reset the selected filter ID when the filter type changes
    public void resetFilterID() {
        selectedFilterID = 0; // Reset to "All" when the filter type changes
    }

    // To save the filtered list of complaints
    public List<Compliants> getFilteredComplaints() {
        return compliantsFacade.getFilteredComplaints(selectedFilterType, selectedFilterID);
    }

    // For select filter item drop-down list
    // Create a SelectItem which contains both the displayed label and the corresponding value.
    public List<SelectItem> getFilterItems() {

        List<SelectItem> filterItems = new ArrayList<>();
        filterItems.add(new SelectItem(0, "All"));

        switch (selectedFilterType) {
            case "techId":
                filterItems.addAll(getSelectItems(employeesFacade.findAllTechnician()));
                break;
            case "catId":
                filterItems.addAll(getSelectItems(categoriesFacade.findAll()));
                break;
            case "empId.depId":
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

            if (entity instanceof Employees) {
                Employees employee = (Employees) entity;
                id = employee.getId();
                label = employee.getFullName();
            } else if (entity instanceof Categories) {
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

    /**
     * Creates a new instance of ComplaintsBean
     */
    public ComplaintManagedBean() {
    }

    // to get the Solved at column using theis format 2 days 4 hours 3 mints
    public static String getSolvedTime(Date startDate, Date endDate) {
        long timeDifferenceMillis = endDate.getTime() - startDate.getTime();

        long days = TimeUnit.MILLISECONDS.toDays(timeDifferenceMillis);
        long hours = TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifferenceMillis) % 60;

        StringBuilder formattedDifference = new StringBuilder();

        if (days > 0 || hours > 0 || minutes > 0) {
            if (days > 0) {
                formattedDifference.append(days).append(" day");
                if (days > 1) {
                    formattedDifference.append("s");
                }
            }

            if (hours > 0) {
                if (formattedDifference.length() > 0) {
                    formattedDifference.append(" and ");
                }

                formattedDifference.append(hours).append(" hour");
                if (hours > 1) {
                    formattedDifference.append("s");
                }

            }
            if (minutes > 0) {
                if (formattedDifference.length() > 0) {
                    formattedDifference.append(" and ");
                }

                formattedDifference.append(minutes).append(" minute");
                if (minutes > 1) {
                    formattedDifference.append("s");
                }

            }
            return formattedDifference.toString();
        } else {
            return "Less than a minute";
        }
    }

    public static String fromatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

}
