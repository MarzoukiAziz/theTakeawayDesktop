/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.BCrypt;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rafrafi
 */
public class UserServices {
    PreparedStatement store;
    User user = new User();
        Connection cnx = DataSource.getInstance().getCnx();
  
    boolean existe = false;
    
     public int existe(User u) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from client WHERE email = '" + u.getEmail() + "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }

    public int existeMail(User u)  {
       try{
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from client WHERE email ='" + u.getEmail() + "'");
        int size = 0;

        rs.next();

        size = rs.getInt(1);

        return size;}
       catch(Exception ex){
           System.out.println("error");
       }
       return 0;
    }

 
    public void ajouter(User utilisateur)  {
       
        String query = "INSERT INTO `client` (`id`, `nom`, `email`, `password`,  `roles`) VALUES (?, ?, ?, ?, ?);";
        int x;
            x = existeMail(utilisateur);
            
        
        if (x == 0) {
            try {
                String password= BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt());
                PreparedStatement ste = cnx.prepareStatement(query);
                ste.setInt(1, utilisateur.getId());
                ste.setString(2, utilisateur.getNom());
                ste.setString(3, utilisateur.getEmail());
                ste.setString(4, password);
                ste.setString(5,"[\"ROLE_USER\"]");

               
                ste.executeUpdate();
                System.out.println("User Added Successfully");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("user already exists");
        }
        
    }

    public List<User> afficher() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM client";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.executeQuery();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                User utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
              
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setPassword(rs.getString("password"));
                utilisateur.setRoles(rs.getString("roles"));
              
                users.add(utilisateur);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    

    public void modifier(User t) {
        
                        String password= BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
         String query = "UPDATE client SET " +
                "nom = '" + t.getNom()+
              
                "', email= '" + t.getEmail()+
                "', password = '" + password+
                "', roles = '" + t.getRoles()+
               
                "' where id="+t.getId();
         
         System.out.println(query);
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("User Updated Successfully ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

       
    public void supprimer(User utilisateur) {
        String query = "DELETE FROM client WHERE id = '" +utilisateur.getId()+ "'";
        try {
            Statement ste = cnx.createStatement();
            int deleted = ste.executeUpdate(query);
            System.out.println(deleted);
            if (deleted > 0)
                System.out.println("Deleted successfully");
            else
                System.out.println("Nothing deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean verifPassword(String nom, String password) {
        try {
            Statement ste = cnx.createStatement();
            
            ResultSet rs = ste.executeQuery("select e.* from client e where email='" + nom +"'");
          
            while(rs.next()){
                String passBase=rs.getString("password");
                if(BCrypt.checkpw(password, passBase)){
                    return true;
                }else
                    return false;
             
            }
            
        } catch (SQLException sq) {
            return false;
        }
        return false;
    }
    
    public int signup(User utilisateur) throws SQLException  {
       
        String query = "INSERT INTO `client` (`id`, `nom`, `email`, `password`,  `roles`) VALUES (?, ?, ?, ?, ?);";
        
        UserServices us = new UserServices();
        
        int x;
            x = us.existe(utilisateur);
            
         int y = us.existeMail(utilisateur);
        
        if (x == 0) {
            if(y==0) {
                String password= BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt());
                PreparedStatement ste = cnx.prepareStatement(query);
                ste.setInt(1, utilisateur.getId());
                ste.setString(2, utilisateur.getNom());
              
                ste.setString(3, utilisateur.getEmail());
                ste.setString(4, password);
                ste.setString(5,"[\"ROLE_USER\"]");

                ste.executeUpdate();
                System.out.println("User Added Successfully");
            return 0 ; 
            } else 
            {
                    return 1 ; 
                    
                    }
        }
            else {
                    return 2 ;
                    }
            
        
    }
    
    public String Login_Dispo(User u) throws SQLException {
        Random rand = new Random(); //instance of random class
        int upperbound = 1000;
        int int_random = rand.nextInt(upperbound);
        String Newlogin=u.getEmail()+""+int_random;
        u.setEmail(Newlogin);
        while (existe(u)!=0)
        {
            int_random = rand.nextInt(upperbound);
            Newlogin=u.getEmail()+""+int_random;
        }
          return Newlogin;
    }
     public User getById(int id) {
         User t = new User();
        
        try {
            String req = ("Select * from `client` WHERE id = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           
            rs.next();
                
                t.setId(rs.getInt("id"));
                t.setNom(rs.getString("nom"));
                t.setEmail(rs.getString("email"));
                t.setPassword(rs.getString("password"));
                t.setRoles(rs.getString("roles"));
               
                
            
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
}