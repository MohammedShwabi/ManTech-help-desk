/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Blogs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import model.BlogsFacade;

/**
 *
 * @author Almomyz
 */
@Named(value = "blogsManagedBean")
@SessionScoped
public class BlogsManagedBean implements Serializable {

    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    @EJB
    private BlogsFacade blogsFacade;
    private Blogs blogs = new Blogs();

    public Blogs getBlogs() {
        return blogs;
    }

    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }

    /**
     * Creates a new instance of BlogsManagedBean
     */
    public BlogsManagedBean() {
    }

    // for get all Blogs data
    public List<Blogs> findAll() {
        return blogsFacade.findAll();
    }

    // to go to update page and pass the current object to fill the form input
    public String gotoUpdate(Blogs blog) {
        this.blogs = blog;
        return "update?faces-redirect=true";
    }

    public String gotoAdd() {
        this.blogs = new Blogs();
        return "/admin/blogs/add?faces-redirect=true";
    }

    // to delete an Blogs
    public String delete(Blogs blog) {
        // Check if the complaint already has a photo
        if (blog.getPhoto() != null ) {
            // Delete the old photo
            ImageUploader.deleteOldPhoto("blogs_photos", blog.getPhoto());
        }
        
        blogsFacade.remove(blog);
        return "view?faces-redirect=true";
    }

    // Method to add a new question
    public String addBlog() throws IOException {

        if (file != null) {
            // uplaod the image
            String pathImage = ImageUploader.upload(file, "blogs_photos");

            // save the blog to the database
            blogs.setPhoto(pathImage);
            blogs.setCreatedAt(new Date());
            blogsFacade.create(blogs);

            // to reset the object
            this.resetBlogs();

            // Redirect to a view page
            return "view?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("image is required");
            context.addMessage("blog_form:photo", message); // "form" is the name of your form
            return null; // Return null to stay on the same page
        }
    }

    // reset the blogs object
    public void resetBlogs() {
        // clear the blogs object
        blogs = new Blogs();
        file = null;
    }

// to save edited question details
    public String update() throws IOException {

        if (file != null) {
            // upload the photo
            String pathImage = ImageUploader.upload(file, "blogs_photos");

            // Check if the blog already has a photo
            if (blogs.getPhoto() != null) {
                // Delete the old photo
                ImageUploader.deleteOldPhoto("blogs_photos", blogs.getPhoto());
            }

            blogs.setPhoto(pathImage);
        }

        blogsFacade.edit(blogs);
        this.resetBlogs();
        // Redirect to a view page
        return "view?faces-redirect=true";
    }
}
