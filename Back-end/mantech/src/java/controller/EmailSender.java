/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dell
 */

public class EmailSender {
    
    private final String senderEmail = "hikayatiapp@gmail.com";
    
    // TODO : change this to "complaint@helpdesk.com"
    private final String recipientEmail = "complaint@helpdesk.com";
    
    private final String username = "hikayatiapp";
    private final String password = "mphkqbhaanokmhtz";
    private final String host = "smtp.gmail.com";
    private final String port = "587";
    
    private Properties properties = null;
    private Session session = null;
    
    
    public boolean sendNewComplaintEmail(String subject, String body) {
        boolean result = false;
        
        // Set up properties for the email server
        properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Use TLS for security

        // Create a session with the properties
        session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);

            // Set the sender's email address
            message.setFrom(new InternetAddress(senderEmail));

            // Set the recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject with a short description of the complaint
            message.setSubject("New Complaint: " + subject);

            // Set the email content
            String emailContent = "Description: " + body;
            
            message.setContent(emailContent, "text/html");

            // Send the email
            Transport.send(message);

            result = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
        return result;
    }
}

