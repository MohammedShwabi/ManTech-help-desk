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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
public class EmployeeManagedBean implements Serializable {

    @EJB
    private EmployeesFacade employeesFacade;
    private Employees employee = new Employees();
    private String confirm_Password;

    public String getConfirm_Password() {
        return confirm_Password;
    }

    public void setConfirm_Password(String confirm_Password) {
        this.confirm_Password = confirm_Password;
    }

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

    // create a SelectItem which contains both the displayed label and the corresponding value.
    // like this 
    // <f:selectItems value="#{departmentManagedBean.find_all()}" var="dept" itemValue="#{dept.id}" itemLabel="#{dept.name}" />
    public List<SelectItem> getDepartmentItems() {
        List<SelectItem> departmentItems = new ArrayList<>();

        List<Departments> departments = departmentsFacade.findEmployeeDepartments();
        departments.forEach((dept) -> {
            departmentItems.add(new SelectItem(dept.getId(), dept.getName()));
        });
        return departmentItems;
    }

    // reset the Employee object
    public void resetEmployee() {
        // Clear the employee object
        employee = new Employees();
        // Clear the selectedDepartmentId
        selectedDepartmentId = null;
    }

    // Method to add a new employee
    public String addEmployee() {
        if (employee.getPassword().compareTo(confirm_Password) == 0) {
            Departments department = departmentsFacade.find(selectedDepartmentId);
            employee.setDepId(department);
            employee.setPhoto("profile.svg");
            
            String hashedPassword = hashPassword( employee.getPassword() );
            
            employee.setPassword(hashedPassword);
            
            employeesFacade.create(employee);
            this.resetEmployee();
            // Redirect to a view page
            return "view?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Password and Confirm Password do not match.");
            context.addMessage("emp_form:Confirm_New_Password", message); // "form" is the name of your form
            return null; // Return null to stay on the same page
        }
    }

    // to hash the password
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hashBytes) {
                hexHash.append(String.format("%02x", b));
            }
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }

    // to go to update page and pass the current object to fill the form input
    public String gotoUpdate(Employees emp) {
        this.employee = emp;
        // to select option for department
        selectedDepartmentId = employee.getDepId().getId();
        return "update?faces-redirect=true";
    }

    // to save edited employee details
    public String update() {
        Departments department = departmentsFacade.find(selectedDepartmentId);
        employee.setDepId(department);
        employeesFacade.edit(employee); // Use the edit method to update the employee
        this.resetEmployee();
        return "view?faces-redirect=true"; // Redirect to a view page
    }

    // to update employee status
    public void changeEmployeeStatus(Employees employeeId, boolean status) {
        employeeId.setActivated(status);
        employeesFacade.edit(employeeId);
    }

    // to filter emplyees based on department
    private Integer selectedDepartmentID = 0; // Default to "All" departments

    public List<Employees> getFilteredEmployees() {
        return employeesFacade.findByDepartmentID(selectedDepartmentID);
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

        // Check if the email is already used by another employee (excluding the current employee being updated)
        int emp_id = (employee.getId() == null) ? 0 : employee.getId();
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
        confirm_Password = null;
        return "/admin/employee/add?faces-redirect=true";
    }

}
