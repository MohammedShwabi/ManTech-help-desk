/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Departments;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import model.DepartmentsFacade;

/**
 *
 * @author dell
 */
@Named(value = "departmentManagedBean")
@SessionScoped
public class DepartmentManagedBean implements Serializable {

    @EJB
    private DepartmentsFacade departmentsFacade;
    private Departments dep = new Departments();

    public DepartmentsFacade getDepartmentsFacade() {
        return departmentsFacade;
    }

    public void setDepartmentsFacade(DepartmentsFacade departmentsFacade) {
        this.departmentsFacade = departmentsFacade;
    }

    public Departments getDep() {
        return dep;
    }

    public void setDep(Departments dep) {
        this.dep = dep;
    }

    
    private List<SelectItem> departmentItems;

    // create a SelectItem which contains both the displayed label and the corresponding value.
    // like this 
    // <f:selectItems value="#{departmentManagedBean.find_all()}" var="dept" itemValue="#{dept.id}" itemLabel="#{dept.name}" />
    public List<SelectItem> getDepartmentItems() {
        departmentItems = new ArrayList<>();
        List<Departments> departments = departmentsFacade.findAll();
        departments.forEach((dept) -> {
            departmentItems.add(new SelectItem(dept.getId(), dept.getName()));
        });
        return departmentItems;
    }
    // to here gpt

    /**
     * Creates a new instance of DepartmentManagedBean
     */
    public DepartmentManagedBean() {
    }

    // to get all the department
    public List<Departments> find_all() {
        return departmentsFacade.findAll();
    }

}
