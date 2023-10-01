/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Compliants;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author dell
 */
@Stateless
public class CompliantsFacade extends AbstractFacade<Compliants> {

    @PersistenceContext(unitName = "mantechPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompliantsFacade() {
        super(Compliants.class);
    }

//    public List<Object[]> getFilteredComplaints(String columnName, int columnID) {
//        String jpql = "SELECT c.id, cat.name, emp.fullName, dep.name, c.createdDate, c.closedDate, tech.fullName, c.pendingDate "
//                + "FROM Compliants c "
//                + "JOIN c.catId cat "
//                + "JOIN c.empId emp "
//                + "JOIN emp.depId dep "
//                + "LEFT JOIN c.techId tech "
//                // to filter by specific item id or get all item
//                + "WHERE (" + columnName + ".id = :columnID OR :columnID = 0) "
//                + "AND c.status = 'closed' "
//                + "ORDER BY c.id";
//
//        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
//        query.setParameter("columnID", columnID);
//        return query.getResultList();
//    }
    
    public List<Compliants> getFilteredComplaints(String columnName, int columnID) {
        String jpql = "SELECT c FROM Compliants c "
                // to filter by specific item id or get all item
                + "WHERE ( c." + columnName + ".id = :columnID OR :columnID = 0) "
                + "AND c.status = 'closed' "
                + "ORDER BY c.id";

        TypedQuery<Compliants> query = em.createQuery(jpql, Compliants.class);
        query.setParameter("columnID", columnID);
        return query.getResultList();
    }

    public List<Compliants> filterPendingComplaints(int selectedCategory, int selectedDepartment, String selectedPriority, int selectedDays) {

        String jpql = "SELECT c FROM Compliants c WHERE "
                + "(:selectedCategory = 0 OR c.catId.id = :selectedCategory) AND "
                + "(:selectedDepartment = 0 OR c.empId.depId.id = :selectedDepartment) AND "
                + "(:selectedPriority = 'all' OR c.priority = :selectedPriority) AND ";

        if (selectedDays != 0) {
            jpql += "c.pendingDate >= :selectedDays AND ";
        }

        // get only the pending report  
        jpql += "c.status = 'pending'";

        TypedQuery<Compliants> query = em.createQuery(jpql, Compliants.class)
                .setParameter("selectedCategory", selectedCategory)
                .setParameter("selectedDepartment", selectedDepartment)
                .setParameter("selectedPriority", selectedPriority);

        // to add the selectedDays parameter if any
        if (selectedDays != 0) {
            Date startDate = calculateStartDate(selectedDays); // Convert selected days to a date
            query.setParameter("selectedDays", startDate);
        }

        return query.getResultList();
    }

    // to convert the pending days to date
    public Date calculateStartDate(int selectedDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -selectedDays);
        return calendar.getTime();
    }

    public List<Compliants> findWaitingComplaints() {
        TypedQuery<Compliants> query = em.createNamedQuery("Compliants.findByStatus", Compliants.class);
        query.setParameter("status", "waiting");
        return query.getResultList();
    }
}
