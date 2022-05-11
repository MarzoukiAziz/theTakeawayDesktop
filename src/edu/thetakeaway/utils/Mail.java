/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
   
                
            public String sendEmail(String toEmailAddress, String emailMessage) {
                
            String userName = "middlefeastesprit@gmail.com";
            String password = "azertyqwerty123!";
            String emailSubject = "Utilisateur";
            try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            Session session = Session.getDefaultInstance(props, null);
            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            String[] recipientList = toEmailAddress.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient: recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject(emailSubject);
            message.setContent(emailMessage, "text/html");
            // Send smtp message
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", 587, userName, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
                System.out.println("Email Sent Successfully to : "+recipientAddress.toString());
            return emailMessage;
            } catch (MessagingException e) {
                return "Error in method sendEmailNotification: " + e;
            }
        }
}
