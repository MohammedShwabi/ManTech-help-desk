/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Compliants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.CompliantsFacade;

/**
 *
 * @author dell
 */
@Named(value = "complaintDetailsManagedBean")
@RequestScoped
public class ComplaintDetailsManagedBean {

    @EJB
    private CompliantsFacade compliantsFacade;

    private int complaintId;
    private Compliants selectedCompliant;

    public int getComplaintId() {
        return complaintId;
    }

    public Compliants getSelectedCompliant() {
        return selectedCompliant;
    }

    public void setSelectedCompliant(Compliants selectedCompliant) {
        this.selectedCompliant = selectedCompliant;
    }

    @PostConstruct
    public void init() {
        // Retrieve the complaint ID from the query parameter
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idParam = params.get("id");

        // Check if the ID parameter is a valid integer
        try {
            complaintId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            complaintId = 0; // Default value
            return;
        }

        // Load the selected compliant based on the compliants ID
        selectedCompliant = compliantsFacade.find(complaintId);

        // Check if the complaint was found, handle the case if it doesn't exist
        if (selectedCompliant == null) {
            complaintId = 0; // Default value
        }
    }

    /**
     * Creates a new instance of ComplaintDetailsManagedBean
     */
    public ComplaintDetailsManagedBean() {
    }
    
}
