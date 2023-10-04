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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import model.BlogsFacade;

/**
 *
 * @author Almomyz
 */
@Named(value = "blogsManagedBean")
@SessionScoped
//@RequestScoped
public class BlogsManagedBean implements Serializable {

    @Inject
    private LoginManagedBean loginBean;

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

    public String gotoAdd() {
        // Reset the Employee object
        blogs = new Blogs();
        return "add";
    }

    // to go to update page and pass the current object to fill the form input
    public String gotoUpdate(Blogs blog) {
        this.blogs = blog;
        return "update?faces-redirect=true";
    }

    // to delete an Blogs
    public void delete(Blogs blogs) {
        blogsFacade.remove(blogs);
    }

    // Method to add a new question
    public String addBlog() throws IOException {

        if (file == null) {
            //doing something here
            return "image is required";
        } else {
            String pathImage = upload();
            blogs.setPhoto(pathImage);
            blogs.setCreatedAt(new Date());
            blogsFacade.create(blogs);
            this.resetBlogs();

            return "view?faces-redirect=true";
            // Redirect to a view page

        }
    }

    // reset the question object
    public void resetBlogs() {
        // clear the question object
        blogs = new Blogs();
    }

// to save edited question details
    public String update() throws IOException {

        if (file != null) {
            String name = upload();
            blogs.setPhoto(name);
        }
        blogsFacade.edit(blogs);
        this.resetBlogs();
        // Redirect to a view page
        return "view?faces-redirect=true";
    }

    //upload image blog
    public String upload() throws IOException {
        String fileName = file.getSubmittedFileName();
        InputStream fileContent = file.getInputStream();
        String uploadDirectory = "C:\\Users\\Almomyz\\Documents\\GitHub\\ManTech-help-desk\\Back-end\\mantech\\web\\upload\\blogs_photos\\";
        String filePath = uploadDirectory + fileName;
        try {

            File destinationFile = new File(filePath);

            FileOutputStream outputStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println(filePath);

        return fileName;
    }

}
