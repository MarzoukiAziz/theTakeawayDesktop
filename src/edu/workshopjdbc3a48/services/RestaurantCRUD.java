/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Restaurant;
import edu.workshopjdbc3a48.utils.MyConnection;
import java.sql.Array;
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
 * @author Bouga
 */
public class RestaurantCRUD {
    
    public void ajouterRestaurant(Restaurant R){
        
        
        try {
            String requete ="INSERT INTO restaurant (nom , adresse , description , heure_ouverture, heure_fermeture ,architecture , telephone,images)"
                    + "VALUES(?,?,?,?,?,?,?,?) " ;
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
              pst.setString(1, R.getNom());
            pst.setString(2, R.getAdresse());
            pst.setString(3, R.getDescription());
            pst.setDate(4, R.getHeure_ouverture());
            pst.setDate(5,R.getHeure_fermeture());
            pst.setString(6,R.getArchitecture());
            pst.setInt(7, R.getTelephone());
            pst.setArray(8, (Array) R.getImages());
            
            pst.executeUpdate();
             System.out.println("Votre restaurant est ajoutée    ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }   
      public List<Restaurant> afficherRestaurants(){
          
           List<Restaurant> myList = new ArrayList<>();
          
        try {
           
            String requete3 = "SELECT *FROM restaurant";
            Statement st =new MyConnection().getCnx().createStatement();
            ResultSet rs =  st.executeQuery(requete3);
            while (rs.next()){
                Restaurant r = new Restaurant( ) ;
                r.setId(rs.getInt(1));
                r.setNom(rs.getString("nom"));
                r.setAdresse(rs.getString("adresse"));
                r.setDescription(rs.getString("description"));
                r.getHeure_ouverture(rs.getDate("heure_ouverture"));
                r.getHeure_fermeture(rs.getDate("heure_fermeture"));
                r.getTelephone(rs.getInt("telephone"));
                r.getImages(rs.getArray("images"));
                myList.add(r);
                
                
                
                
            }
             
           
        } catch (SQLException ex) {
              System.err.println(ex.getMessage());
        }
        return myList ; 
        
    }
    
    
}
