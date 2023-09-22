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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "compliants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compliants.findAll", query = "SELECT c FROM Compliants c")
    , @NamedQuery(name = "Compliants.findById", query = "SELECT c FROM Compliants c WHERE c.id = :id")
    , @NamedQuery(name = "Compliants.findByTitle", query = "SELECT c FROM Compliants c WHERE c.title = :title")
    , @NamedQuery(name = "Compliants.findByPhoto", query = "SELECT c FROM Compliants c WHERE c.photo = :photo")
    , @NamedQuery(name = "Compliants.findByPriority", query = "SELECT c FROM Compliants c WHERE c.priority = :priority")
    , @NamedQuery(name = "Compliants.findByStatus", query = "SELECT c FROM Compliants c WHERE c.status = :status")
    , @NamedQuery(name = "Compliants.findByResend", query = "SELECT c FROM Compliants c WHERE c.resend = :resend")
    , @NamedQuery(name = "Compliants.findByCreatedDate", query = "SELECT c FROM Compliants c WHERE c.createdDate = :createdDate")
    , @NamedQuery(name = "Compliants.findByClosedDate", query = "SELECT c FROM Compliants c WHERE c.closedDate = :closedDate")})
public class Compliants implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Size(max = 30)
    @Column(name = "photo")
    private String photo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "priority")
    private String priority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resend")
    private short resend;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "answer")
    private String answer;
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categories catId;
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employees empId;
    @JoinColumn(name = "tech_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Employees techId;

    public Compliants() {
    }

    public Compliants(Integer id) {
        this.id = id;
    }

    public Compliants(Integer id, String title, String description, String priority, String status, short resend, Date createdDate, Date closedDate, String answer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.resend = resend;
        this.createdDate = createdDate;
        this.closedDate = closedDate;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public short getResend() {
        return resend;
    }

    public void setResend(short resend) {
        this.resend = resend;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Categories getCatId() {
        return catId;
    }

    public void setCatId(Categories catId) {
        this.catId = catId;
    }

    public Employees getEmpId() {
        return empId;
    }

    public void setEmpId(Employees empId) {
        this.empId = empId;
    }

    public Employees getTechId() {
        return techId;
    }

    public void setTechId(Employees techId) {
        this.techId = techId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compliants)) {
            return false;
        }
        Compliants other = (Compliants) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compliants[ id=" + id + " ]";
    }
    
}
