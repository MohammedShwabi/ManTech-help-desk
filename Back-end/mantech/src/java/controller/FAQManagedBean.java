/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Faqs;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.FaqsFacade;

/**
 *
 * @author dell
 */
@Named(value = "fAQManagedBean")
@SessionScoped
public class FAQManagedBean implements Serializable {

    @EJB
    private FaqsFacade faqsFacade;
    private Faqs faqs = new Faqs();
    int id=1;
    public Faqs getFaqs() {
        return faqs;
    }

    public void setFaqs(Faqs faqs) {
        this.faqs = faqs;
    }
    
    
    /**
     * Creates a new instance of FAQManagedBean
     */
    public FAQManagedBean() {
    }
    
    // for get all FAQ data
    public List<Faqs> findAll() {
        return faqsFacade.findAll();
    }
    
    public String gotoAddgotoDetailsFAQ() {
        // Reset the Employee object
        faqs = faqsFacade.find(id);
        return "FAQ_details";
    }
    
    public String gotoAdd() {
        // Reset the Employee object
        faqs = new Faqs();
        return "add";
    }
    
    // to go to update page and pass the current object to fill the form input
    public String gotoUpdate(Faqs faq) {
        this.faqs = faq;
        return "update";
    }
    
    // to delete an faq
    public void delete(Faqs faq) {
        faqsFacade.remove(faq);
    }
    
    // Method to add a new question
    public String addQuestion() {
        faqsFacade.create(faqs);
        this.resetQuestion();
        return "view"; // Redirect to a view page
    }
    
    // reset the question object
    public void resetQuestion() {
        // clear the question object
        faqs = new Faqs();
    }
    
    // to save edited question details
    public String update() {
        faqsFacade.edit(faqs);
        this.resetQuestion();
        return "view"; // Redirect to a view page
    }
}
