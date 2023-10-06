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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import model.CompliantsFacade;
import model.EmployeesFacade;

/**
 *
 * @author dell
 */
@Named(value = "waitingComplaintManagedBean")
@SessionScoped
public class waitingComplaintManagedBean implements Serializable {

    @EJB
    private EmployeesFacade employeesFacade;

    @EJB
    private CompliantsFacade compliantsFacade;

    private Compliants compliants = new Compliants();

    private int selectedTechnicianID;

    private List<SelectItem> technicianItems;

    public int getSelectedTechnicianID() {
        return selectedTechnicianID;
    }

    public void setSelectedTechnicianID(int selectedTechnicianID) {
        this.selectedTechnicianID = selectedTechnicianID;
    }

    public Compliants getCompliants() {
        return compliants;
    }

    public void setCompliants(Compliants compliants) {
        this.compliants = compliants;
    }

    // create a SelectItem which contains both the displayed label and the corresponding value.
    public List<SelectItem> getTechnicianItems() {
        technicianItems = new ArrayList<>();
        List<Employees> technicians = employeesFacade.findAllTechnician();

        technicians.forEach((tech) -> {
            technicianItems.add(new SelectItem(tech.getId(), tech.getFullName()));
        });
        return technicianItems;
    }

    /**
     * Creates a new instance of waitingComplaintManagedBean
     */
    public waitingComplaintManagedBean() {
    }

    // to get all waiting complaint
    public List<Compliants> getWaitingComplaint() {
        // use compliantsFacade to get all complaint that its status is "waiting"
        return compliantsFacade.findWaitingComplaints();
    }

    public String assignComplaint(Compliants c) {
        // get the employee object by its id
        Employees selectedTechnician = employeesFacade.find(selectedTechnicianID);

        // update the complaint technican id
        c.setTechId(selectedTechnician);
        // also update the status of the complaint to be pending
        c.setStatus("pending");
        // and add the pending date
        c.setPendingDate(new Date());

        // save change to db
        compliantsFacade.edit(c);

        // reset the dorpdown list
        selectedTechnicianID = 0;

        return "view?faces-redirect=true";
    }

    // to go to complaintDetails page
    public String showDetails(Compliants c) {
        // Store the selected complaint in a property for display in a different view
        this.compliants = c;
        return "waitingComplaintDetails"; // Navigate to a different JSF page to display details
    }

    // to update the priority in complaintDetails page
    public void updatePriority() {
        compliantsFacade.edit(this.compliants);
    }

}
