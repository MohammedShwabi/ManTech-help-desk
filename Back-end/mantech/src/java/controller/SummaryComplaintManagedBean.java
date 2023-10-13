/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import model.ComplaintByMonthFacade;

/**
 *
 * @author dell
 */
@Named(value = "summaryComplaintManagedBean")
@SessionScoped
public class SummaryComplaintManagedBean implements Serializable {
    
    @EJB
    private ComplaintByMonthFacade complaintByMonthFacade;

    // these for filtering based on raido btn
    private String selectedFilter = "day";

    // this if the filter is by day
    private Date selectedDate;

    // this if the filter is by week
    private String selectedWeek = "";

    // this if the filter is by month
    private String selectedYearMonth = "";
    
    // for selected status
//    private String selectedStatus = "all";
    private String selectedStatus = "closed";
    // for selected departmane
    private int selectedDepartment = 2;

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(String selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public int getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(int selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
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
    public Long getFilteredComplaints(int depId, String status) {

        return complaintByMonthFacade.filterSummaryComplaints(selectedFilter, selectedDate, selectedYearMonth, selectedWeek, status, depId);
    }

    // to rest the filter
    public String restFilter() {

        selectedFilter = "day";
        selectedDate = null;
        selectedWeek = "";
        selectedYearMonth = "";

        return "summaryComplaints";
    }


    /**
     * Creates a new instance of SummaryComplaintManagedBean
     */
    public SummaryComplaintManagedBean() {
    }
}
