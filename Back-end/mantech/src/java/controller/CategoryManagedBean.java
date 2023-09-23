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

    // The category being edited
    private Categories editingCategory = null;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Categories getEditingCategory() {
        return editingCategory;
    }

    public void setEditingCategory(Categories editingCategory) {
        this.editingCategory = editingCategory;
    }

    // checks if a category is currently being edited
    public boolean isEditing(Categories category) {
        return category.equals(editingCategory);
    }

    // start editing a category
    public void startEditing(Categories category) {
        editingCategory = category;
    }

    // stop editing a category
    public void stopEditing() {
        editingCategory = null;
    }

    // update the edited category
    public void updateCategory() {
        categoriesFacade.edit(editingCategory);
        stopEditing();
    }

    // cancel editing
    public void cancelEdit() {
        stopEditing();
    }

    // handles the logic for the "Update/OK" button
    public void checkUpdate(Categories cat) {
        if (isEditing(cat)) {
            updateCategory(); // If already editing, update the category
        } else {
            startEditing(cat); // If not editing, start editing this category
        }
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

    // to delete an category
    public void delete(Categories categories) {
        categoriesFacade.remove(categories);
    }

    // to add a new category
    public void addCategory() {
        categoriesFacade.create(categories);
        // Clear the input field
        categories = new Categories();
    }

}
