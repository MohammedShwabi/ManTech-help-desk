/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Compliants;
import entities.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import model.CompliantsFacade;

/**
 *
 * @author dell
 */
@Named(value = "technicianComplaintManagedBean")
@SessionScoped
public class TechnicianComplaintManagedBean implements Serializable {

    @EJB
    private CompliantsFacade compliantsFacade;
    
    Compliants compliants = new Compliants();

    public Compliants getCompliants() {
        return compliants;
    }

    public void setCompliants(Compliants compliants) {
        this.compliants = compliants;
    }
    
    // possibale value:
    // all, pending, closed
    private String selectedComplaintStatus = "all"; // Default status

    // get the current logged in user from the session
    Employees currentUser = LoginManagedBean.getCurrentUser();

    public String getSelectedComplaintStatus() {
        return selectedComplaintStatus;
    }

    public void setSelectedComplaintStatus(String selectedComplaintStatus) {
        this.selectedComplaintStatus = selectedComplaintStatus;
    }

    /**
     * Creates a new instance of TechnicianComplaintManagedBean
     */
    public TechnicianComplaintManagedBean() {
    }

    // to filter complaints by status
    public List<Compliants> findTechnicianComplaint() {
        return compliantsFacade.findTechnicianComplaint(currentUser.getId(), selectedComplaintStatus);
    }

    // to go to complaintDetails page
    public String showDetails(Compliants c) {
        // Store the selected complaint in a property for display in a different view
        this.compliants = c;
        return "complaintDetails?faces-redirect=true"; // Navigate to a different JSF page to display details
    }
    
    public String answerComplaint() {
        compliants.setClosedDate(new Date());
        compliants.setStatus("closed");
        
        compliantsFacade.edit(compliants);
        return "view?faces-redirect=true"; // Navigate to a different JSF page to display details
    }
    

}
