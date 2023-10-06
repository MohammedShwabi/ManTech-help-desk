/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.ComplaintByMonth;
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
public class ComplaintByMonthFacade extends AbstractFacade<ComplaintByMonth> {

    @PersistenceContext(unitName = "mantechPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComplaintByMonthFacade() {
        super(ComplaintByMonth.class);
    }

    // for closed report
    public List<ComplaintByMonth> filterClosedComplaints(int selectedCategory, int selectedDepartment, String selectedPriority,
            String selectedFilter, Date selectedDate, String selectedYearMonth, String selectedWeek) {

        String jpql = "SELECT c FROM ComplaintByMonth c WHERE "
                + "(:selectedCategory = 0 OR c.catId = :selectedCategory) AND "
                + "(:selectedDepartment = 0 OR c.depId = :selectedDepartment) AND "
                + "(:selectedPriority = 'all' OR c.priority = :selectedPriority) ";

        if (null != selectedFilter) {

            if (selectedDate != null && selectedFilter.equals("day")) {
                // Filter by date using the selectedDate
                jpql += " AND c.date = :selectedDate ";
            } else if (!selectedYearMonth.isEmpty() && selectedFilter.equals("month")) {
                // Filter by formattedDate using the selectedYearMonth
                jpql += " AND c.formattedMonth = :selectedYearMonth ";

            } else if (!selectedWeek.isEmpty() && selectedFilter.equals("week")) {
                // Filter by week_number
                jpql += " AND c.weekNumber = :selectedWeek ";
            }
        }

        jpql += " AND c.status = 'closed'";

        TypedQuery<ComplaintByMonth> query = em.createQuery(jpql, ComplaintByMonth.class)
                .setParameter("selectedCategory", selectedCategory)
                .setParameter("selectedDepartment", selectedDepartment)
                .setParameter("selectedPriority", selectedPriority);

        if (null != selectedFilter) {

            if (selectedDate != null && selectedFilter.equals("day")) {
                query.setParameter("selectedDate", selectedDate);

            } else if (!selectedYearMonth.isEmpty() && selectedFilter.equals("month")) {
                query.setParameter("selectedYearMonth", selectedYearMonth);

            } else if (!selectedWeek.isEmpty() && selectedFilter.equals("week")) {
                // to convert the week for string "2023-W19" to int 202319
                int week = Integer.parseInt(selectedWeek.replace("-W", ""));
                query.setParameter("selectedWeek", week);

            }
        }

        return query.getResultList();
    }

    // for closed report
    public List<Object[]> filterSummaryComplaints(
            String selectedFilter, Date selectedDate, String selectedYearMonth, String selectedWeek) {

        String jpql = "SELECT COUNT(c.id) as comp_count, c.department, c.status FROM ComplaintByMonth c";

        if (null != selectedFilter) {

            if (selectedDate != null && selectedFilter.equals("day")) {
                // Filter by date using the selectedDate
                jpql += " WHERE c.date = :selectedDate ";
            } else if (!selectedYearMonth.isEmpty() && selectedFilter.equals("month")) {
                // Filter by formattedDate using the selectedYearMonth
                jpql += " WHERE c.formattedMonth = :selectedYearMonth ";

            } else if (!selectedWeek.isEmpty() && selectedFilter.equals("week")) {
                // Filter by week_number
                jpql += " WHERE c.weekNumber = :selectedWeek ";
            }
        }

        jpql += " GROUP BY c.department, c.status"
                + " ORDER BY c.department";

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

        if (null != selectedFilter) {

            if (selectedDate != null && selectedFilter.equals("day")) {
                query.setParameter("selectedDate", selectedDate);

            } else if (!selectedYearMonth.isEmpty() && selectedFilter.equals("month")) {
                query.setParameter("selectedYearMonth", selectedYearMonth);

            } else if (!selectedWeek.isEmpty() && selectedFilter.equals("week")) {
                // to convert the week for string "2023-W19" to int 202319
                int week = Integer.parseInt(selectedWeek.replace("-W", ""));
                query.setParameter("selectedWeek", week);
            }
        }

        return query.getResultList();
    }

}
