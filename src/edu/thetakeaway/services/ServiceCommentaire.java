/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.services;
import edu.thetakeaway.entities.commentaire;
import edu.thetakeaway.services.IService;
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
 * @author Oumaima
 */
public class ServiceCommentaire implements IService <commentaire> {
    

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(commentaire l) {
        try { 
            String req = "INSERT INTO `commentaire`(contenu, date) "
                    + "VALUES ('" + l.getContenu() + "', '" + l.getDate() + "' )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commentaire ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(commentaire l) {
        try {
            String req = "INSERT INTO `commentaire`(contenu, date) VALUES (?,?)";
            PreparedStatement pl = cnx.prepareStatement(req);
            pl.setString(1, l.getContenu());
            pl.setString(2, l.getDate());
           
           
            pl.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `commentaire` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commentaire supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
//    public void modifier(Livraison l) {
//        try {
//            String req = "UPDATE livraison SET codepostal = '" + l.getCodepostal() + "', adresse = '" + l.getAdresse()+ "', ville = '" + l.getVille()+ "', datelivraison = '" + l.getDatelivraison()+ "' WHERE livraison.`id` = " + l.getId();
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Livraison modifiée !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    
    public List<commentaire> getAll() {
        List<commentaire> list = new ArrayList<>();
        try {
            String req = "Select * from `commentaire`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                commentaire l = new commentaire( rs.getString("contenu"), rs.getString("date"));
                list.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

      
  

    public void modifier(int id,commentaire l) {
         try {
            PreparedStatement ps= cnx.prepareStatement("UPDATE `commentaire` SET `contenu`=? , `date`= ?   WHERE `id`= '"+id+"'");
           ps.setString(1, l.getContenu());
            ps.setString(2, l.getDate());
        
      
            ps.execute();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void modifier(commentaire p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   

}

