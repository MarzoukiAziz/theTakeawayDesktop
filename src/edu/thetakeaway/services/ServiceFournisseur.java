/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.services;
import edu.thetakeaway.entities.Fournisseur;
import edu.thetakeaway.entities.Restaurant;
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
 * @author Synda
 */
public class ServiceFournisseur implements IService<Fournisseur> {
      Connection cnx = DataSource.getInstance().getCnx();

      private ResultSet rs; 
    private Statement ste; 

    
    public void ajouter(Fournisseur p) {
        try {
            String req = "INSERT INTO fournisseur (nom, adresse,telephone,email) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, p.getAdresse());
            ps.setString(1, p.getNom());
                        ps.setString(3, p.getTelephone());
            ps.setString(4, p.getEmail());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM fournisseur WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Fournisseur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void modifier(int id, String nom, String adresse, String telephone, String email) {
        try {
            String req = "UPDATE fournisseur SET nom = '" +nom+ "', adresse = '" + adresse+ "', telephone = '" + telephone+ "', email = '" + email+ "' WHERE fournisseur.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Fournisseur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public ObservableList<Fournisseur> getRepaslistnew() throws SQLException {
        String req = "select  id,nom, adresse, telephone , email from fornisseur";
        ObservableList<Fournisseur> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Fournisseur r = new Fournisseur();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setAdresse(rs.getString("adresse"));
                r.setTelephone(rs.getString("telephone"));
                r.setEmail(rs.getString("email"));
               
                list.add(r);

            }

        } catch (SQLException ex) {
                Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
    public ObservableList<Fournisseur> getAll() {
        ObservableList<Fournisseur> fournisseurlist = FXCollections.observableArrayList();
                 List <Fournisseur> id = new ArrayList<>(); 

        try {
            String req = "Select * from fournisseur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Fournisseur p = new Fournisseur(rs.getInt(1), rs.getString("nom"), rs.getString(3), rs.getString(4), rs.getString(5));
                fournisseurlist.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return fournisseurlist;

    
}
        
    public List <Fournisseur> liste2()
    {
        String req = "select * from fournisseur"; 
        
       List <Fournisseur> list = new ArrayList<>(); 
       try {
       ste = cnx.createStatement(); 
       rs=ste.executeQuery(req); 
       
       while (rs.next())
       {  Fournisseur p = new Fournisseur(rs.getInt(1), rs.getString("nom"), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(p);
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }

    @Override
    public void modifier(Fournisseur p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<String> getAllFournisseurName(){
        List <String> list = new ArrayList<>();
          try {
              String req="select * from fournisseur";
              
              ste = cnx.createStatement();
              rs=ste.executeQuery(req);
              
              while (rs.next())
              {
                  
                  list.add(rs.getString("nom"));
              }  } catch (SQLException ex) {
              Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
          }
          return list;
        
    }
    public int getIdFournisseurByName(String name){
          try {
              String req="select * from fournisseur";
              
              ste = cnx.createStatement();
              rs=ste.executeQuery(req);
              
              while (rs.next())
              {
                  
                  return rs.getInt("id");
              }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
          }
          return 0;
    }

    public String getNameFournisseurbyId(int id_fournisseur) {
               try {
              String req="select * from fournisseur";
              
              ste = cnx.createStatement();
              rs=ste.executeQuery(req);
              
              while (rs.next())
              {
                  
                  return rs.getString("nom");
              }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
          }
          return "0";
        
        
        
        
    }
    
        
    public Fournisseur getById(int id) {
        Fournisseur p = null;
        try {
            String req = "Select * from `fournisseur` where id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
          p = new Fournisseur(rs.getInt(1), rs.getString("nom"), rs.getString(3), rs.getString(4), rs.getString(5));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
}
