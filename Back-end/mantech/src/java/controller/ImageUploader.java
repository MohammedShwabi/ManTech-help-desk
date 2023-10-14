/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author dell
 */
public class ImageUploader {

    public static String upload(Part file, String folderName) throws IOException {
        // Get the current FacesContext
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Obtain the ServletContext to access the application's resources.
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();

        // Define the relative web path where the images will be stored.
        String relativeWebPath = "/resources/" + folderName + "/";

        // Get the absolute disk path corresponding to the relative web path.
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);

        // Generate a unique name for the uploaded image file.
        String uniqueImageName = generateUniqueImageName(file);

        // Create the complete path to the image file on the server.
        String imageFilePath = absoluteDiskPath + "/" + uniqueImageName;

        try (
                // Open an InputStream to read the uploaded file's content.
                InputStream input = file.getInputStream();
                // Open a OutputStream to write the file to the server's disk.
                OutputStream output = new FileOutputStream(imageFilePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Copy the uploaded file's content to the server's disk in chunks.
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            return uniqueImageName;
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IOException that occurs during the file upload.
            return "defult_image";
        }

    }

    public static void deleteOldPhoto(String folderName, String oldPhotoFileName) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String relativeWebPath = "/resources/" + folderName + "/";
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);

        // Construct the path to the old photo
        String oldPhotoPath = absoluteDiskPath + "/" + oldPhotoFileName;

        File oldPhotoFile = new File(oldPhotoPath);

        // Check if the old photo file exists, and if it does, delete it
        if (oldPhotoFile.exists()) {
            oldPhotoFile.delete();
        }
    }

    private static String generateUniqueImageName(Part part) {
        // Get the submitted file name
        String fileName = part.getSubmittedFileName();

        // Get the file extension
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        // Generate a random UUID and append the extracted file extension to it
        String uuid = UUID.randomUUID().toString();

        return uuid + extension;
    }

}
