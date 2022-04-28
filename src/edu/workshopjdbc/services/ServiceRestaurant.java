/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.services;

import edu.workshopjdbc.entities.Facture;
import edu.workshopjdbc.entities.Ingrediant;
import edu.workshopjdbc.entities.Restaurant;
import edu.workshopjdbc.utils.DataSource;
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
 * @author Synda
 */
public class ServiceRestaurant implements Iservice<Restaurant>{
      Connection cnx = DataSource.getInstance().getCnx();
           private ResultSet rs; 
    private Statement ste; 

    @Override
    public void ajouter(Restaurant p) {
        

    }

    @Override
    public void supprimer(int id) {  }

    @Override
    public void modifier(Restaurant p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public List<String> getAllRestaurantByName(){
        List <String> list = new ArrayList<>();
          try {
              String req="select * from restaurant";
              
              ste = cnx.createStatement();
              rs=ste.executeQuery(req);
              
              while (rs.next())
              {
                  
                  list.add(rs.getString("nom"));
              }  } catch (SQLException ex) {
              Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
          }
          return list;
        
    }
 public List<Restaurant> getAll() {
        List<Restaurant> list = new ArrayList<>();
        try {
            String req = "Select * from `restaurant`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Restaurant r = new Restaurant(rs.getInt("id"), rs.getString("nom"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public int getIdrestaurantByName(Object value) {
         try {
              String req="select * from restaurant";
              
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
    
    public Restaurant getById(int id) {
        Restaurant r = null;
        try {
            String req = "Select * from `restaurant` where id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                 r = new Restaurant(rs.getInt("id"), rs.getString("nom"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }


    public List<Ingrediant> getByRestaurantId(Restaurant r) {
        List<Ingrediant> list = new ArrayList<>();
        try {
            String req = "Select * from ingrediant where restaurant_id = " + r.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
               while (rs.next()) {
                Ingrediant i = new Ingrediant();

                i.setRestaurant(r);
                i.setId(rs.getInt("id"));
                i.setNom(rs.getString("nom"));
                i.setQuantite(rs.getInt("quantite"));

                list.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


    
}
