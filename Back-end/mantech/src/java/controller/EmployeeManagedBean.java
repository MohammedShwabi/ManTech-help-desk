/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Departments;
import entities.Employees;
import model.EmployeesFacade;
import model.DepartmentsFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
public class EmployeeManagedBean implements Serializable {

    @EJB
    private EmployeesFacade employeesFacade;
    private Employees employee = new Employees();

    @EJB
    private DepartmentsFacade departmentsFacade;

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    // save the choosed item
    private Integer selectedDepartmentId;

    // getter and setter for selecteditemID
    public Integer getSelectedDepartmentId() {
        return selectedDepartmentId;
    }

    public void setSelectedDepartmentId(Integer selectedDepartmentId) {
        this.selectedDepartmentId = selectedDepartmentId;
    }
    // end of choosed item

    // reset the Employee object
    public void resetEmployee() {
        // Clear the employee object
        employee = new Employees();
        // Clear the selectedDepartmentId
        selectedDepartmentId = null;
    }

    // Method to add a new employee
    public String addEmployee() {
        Departments department = departmentsFacade.find(selectedDepartmentId);
        employee.setDepId(department);
        employeesFacade.create(employee);
        this.resetEmployee();
        return "view"; // Redirect to a view page
    }

    // for get all employee data
    public List<Employees> findAll() {
        return employeesFacade.findAll();
    }

    // to go to update page and pass the current object to fill the form input
    public String gotoUpdate(Employees emp) {
        this.employee = emp;
        // to select option for department
        selectedDepartmentId = employee.getDepId().getId();
        return "update";
    }

    // to save edited employee details
    public String update() {
        Departments department = departmentsFacade.find(selectedDepartmentId);
        employee.setDepId(department);
        employeesFacade.edit(employee); // Use the edit method to update the employee
        this.resetEmployee();
        return "view"; // Redirect to a view page
    }

    // to delete an employee
    public void delete(Employees emp) {
        employeesFacade.remove(emp);
    }

    // to update employee status
    public void changeEmployeeStatus(Employees employeeId, boolean status) {
        employeeId.setActivated(status);
        employeesFacade.edit(employeeId);
    }

    // to filter emplyees based on department
//    private String selectedDepartmentName = "All"; // Default to "All" departments
    private Integer selectedDepartmentID = 0; // Default to "All" departments

    public List<Employees> getFilteredEmployees() {
//        if ("All".equals(selectedDepartmentName)) {
//            return employeesFacade.findAll();
//        } else {
        return employeesFacade.findByDepartmentID(selectedDepartmentID);
//        }
    }

    public Integer getSelectedDepartmentID() {
        return selectedDepartmentID;
    }

    public void setSelectedDepartmentID(Integer selectedDepartmentID) {
        this.selectedDepartmentID = selectedDepartmentID;
    }

    // Custom validator method for unique email
    public void validateUniqueEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;

        //Check email format using a regular expression 
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {

            // If the email format is not valid, throw a validation error
            FacesMessage message = new FacesMessage("Invalid email format.");
            throw new ValidatorException(message);
        }

        // Check if the email is already used by another employee
        // Check if the email is already used by another employee (excluding the current employee being updated)
//        Employees currentEmployee = this.getEmployee(); // Get the current employee being updated
//        boolean isEmailUnique = employeesFacade.isEmailUnique(email, currentEmployee.getId());

        Integer emp_id = (employee.getId() == null)? 0 : employee.getId();
        boolean isEmailUnique = employeesFacade.isEmailUnique(email, emp_id);

        if (!isEmailUnique) {
            // If the email is not unique, throw a validation error
            FacesMessage message = new FacesMessage("Email is already in use.");
            throw new ValidatorException(message);
        }
    }

    public String gotoAdd() {
        // Reset the Employee object and selected department
        employee = new Employees();
        return "add";
    }

}