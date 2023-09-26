/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Compliants;
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

    public List<Object[]> getFilteredComplaints(String columnName, int columnID) {
        String jpql = "SELECT c.id, cat.name, emp.fullName, dep.name, c.createdDate, c.closedDate, tech.fullName "
                + "FROM Compliants c "
                + "JOIN c.catId cat "
                + "JOIN c.empId emp "
                + "JOIN emp.depId dep "
                + "LEFT JOIN c.techId tech "
                // to filter by specific item id or get all item
                + "WHERE (" + columnName + ".id = :columnID OR :columnID = 0) "
                + "AND c.status = 'closed' "
                + "ORDER BY c.id";

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("columnID", columnID);
        return query.getResultList();
    }
}
