/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Blog_client;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceBlog_client implements IService<Blog_client> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Blog_client b) {
            User u =new User(2);
      
      try {
            String req = "INSERT INTO `blog_client` (`id`, `author_id`, `title`, `contenu`, `date`, `statut`, `image`) VALUES ('" + b.getId() + "', '" + b.getU().getId() + "' ,'" + b.getTitle() + "' , '" + b.getContenu() + "', '" + b.getDate() + "', '" + b.getStatut() + "', '" + b.getImage() + "')";
     
                 PreparedStatement pst = cnx.prepareStatement(req);
           
             
          
            pst.setString(3, b.getTitle());
            pst.setString(4, b.getContenu());
             pst.setString(5, b.getDate());
              pst.setString(6, b.getStatut());
               pst.setString(7, b.getImage());

            pst.executeUpdate();
            pst.executeUpdate(req);
            System.out.println("Blog ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 @Override
    public void supprimer(int id) {
           try {
            String req = "DELETE FROM `blog_client` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("blog deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    @Override
    public void modifier(Blog_client b) {
         try {
            String req = "UPDATE `blog_client` SET title = '" + b.getU().getId()  +  "','" + b.getTitle() + "', contenu= '" + b.getContenu() + "',  date = '" + b.getDate()+ "', image = '" + b.getStatut()+  "', date = '" + b.getImage()+ "' WHERE blog.`id` = " + b.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("blog updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
   

//    @Override
//    public ObservalList<Blog_client> getAll() {
//         List<Blog_client> list = new ArrayList<>();
//        try {
//            String req = "Select * from 'blog_client'";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while(rs.next()){
//                Blog_client b = new Blog_client(rs.getString("title"), rs.getString("contenu"), rs.getString("statut"), rs.getString("image"), rs.getString("date"),rs.(""));
//                list.add(b);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }

    
   /**public List<Blog_client> getAll() {
        List<Blog_client> list =new ArrayList<>();
            
        try {
            Statement st = cnx.createStatement();
            String req="select * from 'blog_client'";
            ResultSet rs=st.executeQuery(req);
            while (rs.next()) {
                Blog_client p1=new Blog_client();
//                rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4);
                p1.setId(rs.getInt(1));     
                p1.setTitle(rs.getString(3));
                p1.setContenu(rs.getString(4));
                p1.setStatut(rs.getString(5));
                p1.setImage(rs.getString(6));
                p1.setDate(rs.getString(7));
                p1.setU(new User(rs.getInt(2)));
             
                list.add(p1);
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
             return list;
    }**/
    @Override
    public List< Blog_client> getAll() {
        List<Blog_client> list = new ArrayList<>();
        try {
            String req = "Select * from `blog_client`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Blog_client b = new Blog_client ();
                b.setId(rs.getInt(1));     
                b.setTitle(rs.getString(3));
                b.setContenu(rs.getString(4));
                b.setStatut(rs.getString(5));
                b.setImage(rs.getString(6));
                b.setDate(rs.getString(7));
                b.setU(new User(rs.getInt(2)));
                list.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}

   
  

  
   
