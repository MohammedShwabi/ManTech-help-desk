/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Employees;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author dell
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> {

    @PersistenceContext(unitName = "mantechPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }

//    // Implement the findByDepartmentName method
//    public List<Employees> findByDepartmentName(String departmentName) {
//        Query query = em.createQuery("SELECT e FROM Employees e WHERE e.depId.name = :departmentName");
//        query.setParameter("departmentName", departmentName);
//        return query.getResultList();
//    }
    // Implement the findByDepartmentName method
//    public List<Employees> findByDepartmentName(String departmentName) {
//        // Return employees by department name
//        TypedQuery<Employees> query = em.createQuery("SELECT e FROM Employees e WHERE e.depId.name = :departmentName", Employees.class);
//        query.setParameter("departmentName", departmentName);
//        return query.getResultList();
//
//    }
    public List<Employees> findByDepartmentID(Integer departmentId) {
        System.err.println("Selected Department Name: " + departmentId);

        if (departmentId == 0) {
            System.out.println("Retrieving all employees...");
            TypedQuery<Employees> query = em.createQuery("SELECT e FROM Employees e", Employees.class);
            return query.getResultList();
        } else {
            System.out.println("Retrieving employees in department: " + departmentId);
            TypedQuery<Employees> query = em.createQuery("SELECT e FROM Employees e WHERE e.depId.id = :departmentId", Employees.class);
            query.setParameter("departmentId", departmentId);

            System.out.println("SQL Query: " + query.toString());
            return query.getResultList();
        }
    }

    // Method to check email uniqueness while excluding the current employee
    public boolean isEmailUnique(String email, Integer currentEmployeeId) {
        // Create a query to count the number of employees with the given email
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(e) FROM Employees e WHERE e.email = :email AND e.id != :currentEmployeeId", Long.class);
        query.setParameter("email", email);
        query.setParameter("currentEmployeeId", currentEmployeeId);
        
         System.out.println("SQL Query: " + query.toString());
         System.out.println("email : " + email);
         System.out.println("id : " + currentEmployeeId);
        
        // Execute the query and get the result as a Long
        Long count = query.getSingleResult();
//        System.out.println("erorrerewr: " + query.toString() + "count: " + count);
        
        // If count is 0, the email is unique (excluding the current employee)
        return count == 0;
        
    }
    
    // Method to check email uniqueness while excluding the current employee
//    public boolean isEmailUnique(String email, Long currentEmployeeId) {
//        // Create a query to count the number of employees with the given email
//        Query query = em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.email = :email AND e.id != :currentEmployeeId");
//        query.setParameter("email", email);
//        query.setParameter("currentEmployeeId", currentEmployeeId);
//
//        // Execute the query and get the result as a Long
//        Long count = (Long) query.getSingleResult();
//
//        // If count is 0, the email is unique (excluding the current employee)
//        return count == 0;
//    }

}