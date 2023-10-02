/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Blogs;
import entities.Faqs;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.BlogsFacade;
import model.FaqsFacade;

/**
 *
 * @author Almomyz
 */
@Named(value = "bolgs_FAQManagedBean")
@SessionScoped
public class Bolgs_FAQManagedBean implements Serializable {
    int id=2;
    @EJB
    private BlogsFacade blogsFacade;
     @EJB
    private FaqsFacade faqsFacade;
     private Faqs faqs = new Faqs();
     private Blogs blogs = new Blogs();
    /**
     * 
     * Creates a new instance of Bolgs_FAQManagedBean
     */
    public Bolgs_FAQManagedBean() {
    }

    public Faqs getFaqs() {
        return faqs;
    }

    public void setFaqs(Faqs faqs) {
        this.faqs = faqs;
    }

    public Blogs getBlogs() {
        return blogs;
    }

    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }
    
    
     public List<Blogs> findAllBlogs() {
        return blogsFacade.findAll();
    }
    public String gotoDetailsBlog() {
      blogs=blogsFacade.find(id);
        
        return "blog_details";
    }
    
    
    public List<Faqs> findAllFAQ() {
        return faqsFacade.findAll();
    }
}
