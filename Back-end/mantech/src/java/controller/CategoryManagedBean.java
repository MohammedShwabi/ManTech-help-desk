/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Categories;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.CategoriesFacade;

/**
 *
 * @author dell
 */
@Named(value = "categoryManagedBean")
@SessionScoped
public class CategoryManagedBean implements Serializable {

    @EJB
    private CategoriesFacade categoriesFacade;
    private Categories categories = new Categories();

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    
    
    

    /**
     * Creates a new instance of CategoryManagedBean
     */
    public CategoryManagedBean() {
    }
    
    // for get all categories data
    public List<Categories> findAll() {
        return categoriesFacade.findAll();
    }
    
    // to delete an faq
    public void delete(Categories categories) {
        categoriesFacade.remove(categories);
    }
    
    
}
