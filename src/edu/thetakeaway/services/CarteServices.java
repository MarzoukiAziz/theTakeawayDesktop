/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Carte;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafrafi
 */
public class CarteServices implements IService <Carte>{
 PreparedStatement store;
    Carte carte = new Carte();
        Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Carte p) {
            try {
               
         
            String req = "INSERT INTO  `cart_bancaire` (`numero`, `nom`,`datexp`,`cvv`,`client_id`) VALUES (?,?,?,?,?)";
    ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getNumero());
              
            ps.setString(2, p.getNom());
             
            ps.setString(3, p.getDatexp());
            ps.setInt(4,p.getCvv() );
            
            ps.setInt(5, p.getUser().getId());
           
            
            ps.executeUpdate();
            System.out.println("Carte Jouter ajouté");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
           try {
               
         String req = "DELETE FROM `cart_bancaire` WHERE id = " + id;
    ;
            PreparedStatement ps = cnx.prepareStatement(req);
            
           
            
            ps.executeUpdate();
            System.out.println("Carte Supprime ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Carte p) {
                        try {
                            int id =p.getUser().getId();
                    
            PreparedStatement ps = cnx.prepareStatement("UPDATE cart_bancaire SET `numero`=? , `nom`= ? , `datexp`= ? , `cvv`= ? WHERE id=  '"+id+"'");
           ps.setInt(1, p.getNumero());
              
            ps.setString(2, p.getNom());
             
            ps.setString(3, p.getDatexp());
            ps.setInt(4,p.getCvv() );
            
            

                System.out.println("Carte Modifieé avec succées ");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public Carte getCarteByid(int id) {
         Carte t = new Carte();
        
        try {
        
            String req = ("Select * from `cart_bancaire` WHERE numero = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req); 
            
            rs.next();               
                t.setId(rs.getInt("id"));
               
               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
        public void modifiere(Carte p,int id) {
                        try {
                            
                    
                      
            String req = "UPDATE `cart_bancaire` SET `numero` = '" + p.getNumero() + "', `nom` = '" + p.getNom() + "', `datexp` = '" + p.getDatexp() + "' WHERE `cart_bancaire`.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            
        
            

                System.out.println("Carte Modifieé avec succées ");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        

    @Override
    public List<Carte> getAll() {
        List<Carte> list = new ArrayList<>();
        try {
            String req = "Select * from cart_bancaire";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Carte u = new Carte();
                u.setId(rs.getInt(1));
                u.setNumero(rs.getInt("numero"));
                u.setNom(rs.getString("nom"));
                u.setDatexp(rs.getString("datexp"));
                u.setCvv(rs.getInt("cvv"));
               
                
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public List<Carte> getAllbyid(int id ) {
        List<Carte> list = new ArrayList<>();
        try {
            String req = "Select * from cart_bancaire where client_id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Carte u = new Carte();
                u.setId(rs.getInt(1));
                u.setNumero(rs.getInt("numero"));
                u.setNom(rs.getString("nom"));
                u.setDatexp(rs.getString("datexp"));
                u.setCvv(rs.getInt("cvv"));
               
                
                list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
          public User getByIda(int id) {
         User t = new User();
        
        try {
        
            String req = ("Select * from `client` WHERE num_tel = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req); 
            
            rs.next();               
                t.setId(rs.getInt("id"));
                t.setNom(rs.getString("nom"));
                t.setEmail(rs.getString("email"));
                t.setPassword(rs.getString("password"));
                t.setSecurityq(rs.getString("securityq"));
                t.setAnswer(rs.getString("answer"));
                  t.setNumtel(rs.getInt("num_tel"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
         public int NumberCartes() throws SQLException{
            int Compte = 0;
         String Num="";
         String req ="SELECT COUNT(id) FROM cart_bancaire As Compte  ";
         Statement st = cnx.createStatement();
         ResultSet rs = st.executeQuery(req);
         while(rs.next()){
              Compte = rs.getInt(1)+1;
             
             
         }
         System.out.println(Compte);
    return Compte;
    }
          public static boolean isValidCvv(String phone_number) {
        boolean isValid =  phone_number.matches("\\d{3}");
        System.out.println(phone_number+" : "+isValid);
        return isValid;
    }
           public int existeNumm(int email)  {
       try{
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from cart_bancaire WHERE numero ='" + email+ "'");
        int size = 0;

        rs.next();

        size = rs.getInt(1);

        return size;}
       catch(Exception ex){
           System.out.println("error");
       }
       return 0;
    }
   
}
