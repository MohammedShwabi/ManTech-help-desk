/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Compliants;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import model.CompliantsFacade;

/**
 *
 * @author dell
 */
@Named(value = "updatePriorityManagedBean")
@SessionScoped
public class UpdatePriorityManagedBean implements Serializable {

    @EJB
    private CompliantsFacade compliantsFacade;

    private int complaintId;
    private String priority;

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void updatePriority() {
        Compliants complaint = compliantsFacade.find(complaintId);
        complaint.setPriority(priority);
        compliantsFacade.edit(complaint);
    }

    /**
     * Creates a new instance of UpdatePriorityManagedBean
     */
    public UpdatePriorityManagedBean() {
    }

}
