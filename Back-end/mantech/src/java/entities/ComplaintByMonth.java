/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "complaint_by_month")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplaintByMonth.findAll", query = "SELECT c FROM ComplaintByMonth c")
    , @NamedQuery(name = "ComplaintByMonth.findById", query = "SELECT c FROM ComplaintByMonth c WHERE c.id = :id")
    , @NamedQuery(name = "ComplaintByMonth.findByTitle", query = "SELECT c FROM ComplaintByMonth c WHERE c.title = :title")
    , @NamedQuery(name = "ComplaintByMonth.findByPhoto", query = "SELECT c FROM ComplaintByMonth c WHERE c.photo = :photo")
    , @NamedQuery(name = "ComplaintByMonth.findByPriority", query = "SELECT c FROM ComplaintByMonth c WHERE c.priority = :priority")
    , @NamedQuery(name = "ComplaintByMonth.findByStatus", query = "SELECT c FROM ComplaintByMonth c WHERE c.status = :status")
    , @NamedQuery(name = "ComplaintByMonth.findByResend", query = "SELECT c FROM ComplaintByMonth c WHERE c.resend = :resend")
    , @NamedQuery(name = "ComplaintByMonth.findByCreatedDate", query = "SELECT c FROM ComplaintByMonth c WHERE c.createdDate = :createdDate")
    , @NamedQuery(name = "ComplaintByMonth.findByPendingDate", query = "SELECT c FROM ComplaintByMonth c WHERE c.pendingDate = :pendingDate")
    , @NamedQuery(name = "ComplaintByMonth.findByClosedDate", query = "SELECT c FROM ComplaintByMonth c WHERE c.closedDate = :closedDate")
    , @NamedQuery(name = "ComplaintByMonth.findByEmpId", query = "SELECT c FROM ComplaintByMonth c WHERE c.empId = :empId")
    , @NamedQuery(name = "ComplaintByMonth.findByEmployee", query = "SELECT c FROM ComplaintByMonth c WHERE c.employee = :employee")
    , @NamedQuery(name = "ComplaintByMonth.findByCatId", query = "SELECT c FROM ComplaintByMonth c WHERE c.catId = :catId")
    , @NamedQuery(name = "ComplaintByMonth.findByCategory", query = "SELECT c FROM ComplaintByMonth c WHERE c.category = :category")
    , @NamedQuery(name = "ComplaintByMonth.findByDepId", query = "SELECT c FROM ComplaintByMonth c WHERE c.depId = :depId")
    , @NamedQuery(name = "ComplaintByMonth.findByDepartment", query = "SELECT c FROM ComplaintByMonth c WHERE c.department = :department")
    , @NamedQuery(name = "ComplaintByMonth.findByTechId", query = "SELECT c FROM ComplaintByMonth c WHERE c.techId = :techId")
    , @NamedQuery(name = "ComplaintByMonth.findByTechnician", query = "SELECT c FROM ComplaintByMonth c WHERE c.technician = :technician")
    , @NamedQuery(name = "ComplaintByMonth.findByFormattedMonth", query = "SELECT c FROM ComplaintByMonth c WHERE c.formattedMonth = :formattedMonth")
    , @NamedQuery(name = "ComplaintByMonth.findByWeekNumber", query = "SELECT c FROM ComplaintByMonth c WHERE c.weekNumber = :weekNumber")
    , @NamedQuery(name = "ComplaintByMonth.findByDate", query = "SELECT c FROM ComplaintByMonth c WHERE c.date = :date")})
public class ComplaintByMonth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @Id
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "photo")
    private String photo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "priority")
    private String priority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resend")
    private boolean resend;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer")
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "pending_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pendingDate;
    @Column(name = "closed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "emp_id")
    private int empId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "employee")
    private String employee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cat_id")
    private int catId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dep_id")
    private int depId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "department")
    private String department;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tech_id")
    private int techId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "technician")
    private String technician;
    @Size(max = 7)
    @Column(name = "formatted_month")
    private String formattedMonth;
    @Column(name = "week_number")
    private Integer weekNumber;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public ComplaintByMonth() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getResend() {
        return resend;
    }

    public void setResend(boolean resend) {
        this.resend = resend;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getPendingDate() {
        return pendingDate;
    }

    public void setPendingDate(Date pendingDate) {
        this.pendingDate = pendingDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getFormattedMonth() {
        return formattedMonth;
    }

    public void setFormattedMonth(String formattedMonth) {
        this.formattedMonth = formattedMonth;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
