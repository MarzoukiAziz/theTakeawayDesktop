/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import entities.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.DataSource;

/**
 *
 * @author USER
 */
public class BlogService {
    Connection cnx = DataSource.getInstance().getCnx();
           
    public void ajouter(Blog t) {
		try {
                
            String requete = "INSERT INTO blog_client (author_id, title, contenu, date, statut, image) VALUES (?, ?, ?, NOW(), ?, 'image')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, "1");
            pst.setString(2, t.getTitle());
            pst.setString(3, t.getContenu());
            pst.setString(4,t.getStatut());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
	}


	public void supprimer(Blog t) {
		  try {
            String requete = "DELETE FROM blog_client WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}


	public void modifier(Blog t) {
		   try {
            String requete = "Update blog_client set title = ?, contenu = ?, statut = ? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getTitle());
            pst.setString(2, t.getContenu());
            pst.setString(3,t.getStatut());
            pst.setString(4,t.getId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	}

	public List<Blog> list() {
		 List <Blog> blogs = new ArrayList();

        try {
            String requete = "SELECT * FROM blog_client";
            PreparedStatement pst = cnx.prepareStatement(requete);
            // resultset sert a la lecture du resultat w executeQuery trajaalek comme retour une liste d'objets
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(rs.getString("id"),rs.getString("author_id"),rs.getString("title"),rs.getString("contenu"),rs.getString("date"),rs.getString("statut")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return blogs;
	}
        
        public List<Blog> afficher() {
		 ObservableList <Blog> blogs = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM blog_client ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(rs.getString("id"),rs.getString("author_id"),rs.getString("title"),rs.getString("contenu"),rs.getString("date"),rs.getString("statut")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return blogs;
	}
        
        public List<Blog> search(String x) {
		 ObservableList <Blog> blogs = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM blog_client where title like ? or contenu like ? or date like ?  or statut like ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+x+"%");
			pst.setString(2, "%"+x+"%");
			pst.setString(3, "%"+x+"%");
			pst.setString(4, "%"+x+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(rs.getString("id"),rs.getString("author_id"),rs.getString("title"),rs.getString("contenu"),rs.getString("date"),rs.getString("statut")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return blogs;
	}
        
        public List<Blog> searchOpen(String x) {
		 ObservableList <Blog> blogs = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM blog_client where (title like ? or contenu like ? or date like ?  or statut like ?) and statut = 'Ouvert'";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+x+"%");
			pst.setString(2, "%"+x+"%");
			pst.setString(3, "%"+x+"%");
			pst.setString(4, "%"+x+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(rs.getString("id"),rs.getString("author_id"),rs.getString("title"),rs.getString("contenu"),rs.getString("date"),rs.getString("statut")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return blogs;
	}
        
         public List<Blog> open() {
		 ObservableList <Blog> blogs = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM blog_client where statut = 'Ouvert'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                blogs.add(new Blog(rs.getString("id"),rs.getString("author_id"),rs.getString("title"),rs.getString("contenu"),rs.getString("date"),rs.getString("statut")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
        return blogs;
	}
         
    public void send() {
        final String username = "02b595bf5b9f86";
        final String password = "20a2ee36e08930";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("admin@adm.in"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("admin@adm.in, admin@adm.in")
            );
            message.setSubject("Blog Ajouté");
            message.setText(" un client demande d'ajouter new blog! \n ");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    } 
    
    public void sms() {
        String sid = "AC7a79e22940375aa2a9d309803f0464d7";
        String token = "5bff59d53efa6fccadb17ea261a9366e";
        Twilio.init(sid, token);
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber("+21623344877"), new PhoneNumber("+14094074123"), 
                "votre blog est confimé").create();
    }
}
