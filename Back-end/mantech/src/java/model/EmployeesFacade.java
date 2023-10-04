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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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

    public List<Employees> findByDepartmentID(Integer departmentId) {
        return em.createNamedQuery("Employees.findByDepartmentExceptAdmin").setParameter("departmentId", departmentId).getResultList();
    }

    // Method to check email uniqueness while excluding the current employee
    public boolean isEmailUnique(String email, Integer currentEmployeeId) {
        // Create a query to count the number of employees with the given email
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(e) FROM Employees e WHERE e.email = :email AND e.id != :currentEmployeeId", Long.class);
        query.setParameter("email", email);
        query.setParameter("currentEmployeeId", currentEmployeeId);

        // Execute the query and get the result as a Long
        Long count = query.getSingleResult();

        // If count is 0, the email is unique (excluding the current employee)
        return count == 0;

    }

    public Employees login(String email, String password) {
        TypedQuery<Employees> query = em.createNamedQuery("Employees.findUser", Employees.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Return null if no matching user is found
        }
    }

    public List<Employees> findAllTechnician() {
        return em.createNamedQuery("Employees.findAllTechnician").getResultList();
    }

}
