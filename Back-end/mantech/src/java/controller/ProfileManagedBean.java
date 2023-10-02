/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Blogs;
import entities.Departments;
import entities.Employees;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import model.DepartmentsFacade;
import model.EmployeesFacade;

/**
 *
 * @author Almomyz
 */
@Named(value = "profileManagedBean")
@SessionScoped
public class ProfileManagedBean implements Serializable {
  int id=2;
    @EJB
    private EmployeesFacade employeesFacade;
    @EJB
    private DepartmentsFacade departmentsFacade ;
    private  Employees employees=new Employees();
    Employees user;

    String OldPassword;
    String NewPassword;
    String ConfirmNewPassword;
    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
  

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String OldPassword) {
        this.OldPassword = OldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }

    public String getConfirmNewPassword() {
        return ConfirmNewPassword;
    }

    public void setConfirmNewPassword(String ConfirmNewPassword) {
        this.ConfirmNewPassword = ConfirmNewPassword;
    }
    
    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
    
    
    public Employees getUser(){
        //System.out.println(user.getDepId());
        Employees user= employeesFacade.find(2) ;
         
       return user;
    }
    

    
    public String goToPasswordReset(Employees user){
    this.user=user;
    return "password_reset";
    }
    
    public String passwordReset(){
       String after=""; 
   if(OldPassword.compareTo(user.getPassword())==0&&NewPassword.compareTo(ConfirmNewPassword)==0){
     user.setPassword(NewPassword);
     employeesFacade.edit(user);
     OldPassword="";
     NewPassword="";
     ConfirmNewPassword="";
      after="profile?faces-redirect=true";
   }else{
   //doing some thing here
   return after;
   }
    
    return after;
    }

    public void editImage()throws IOException{
   if(file==null){
            //doing something here
        }else{
            String pathImage=  upload();
            user.setPhoto(pathImage);
        }
        
    employeesFacade.edit(user);
    }
    
                
                
        public String upload() throws IOException {
        String fileName = file.getSubmittedFileName();
        InputStream fileContent = file.getInputStream();
         String uploadDirectory = "C:\\Users\\Almomyz\\Documents\\GitHub\\ManTech-help-desk\\Back-end\\mantech\\web\\upload\\profiles_photos\\";
        String filePath = uploadDirectory + fileName;
         try {
            
            
            
            // Process the uploaded file as needed (e.g., save it to a directory, store it in a database, etc.)
            
            // Example: Save the file in a specific directory inside your project
           
           
            
            // Create a file object representing the destination file
            File destinationFile = new File(filePath);
            
            // Use Java I/O streams to save the file
            FileOutputStream outputStream = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();
            
            
            
            // Optionally, you can show a success message to the user
        } catch (IOException e) {
            // Handle any exceptions that may occur during the file upload process
        e.printStackTrace();
        }
         
         System.out.println(filePath);
        
        return  fileName;
    }

     
    
    
    /**
     * Creates a new instance of ProfileManagedBean
     */
    public ProfileManagedBean() {
    }
    
}
