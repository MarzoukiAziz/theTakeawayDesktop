/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Commande;
import edu.workshopjdbc3a48.utils.DataSource;
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
 * @author abdelazizmezri
 */
public class ServiceCommande implements IService<Commande> {

    Connection cnx = DataSource.getInstance().getCnx();

       @Override
     public void ajouter(Commande p) {
        try {
            String req = "INSERT INTO `commande` (`client_id`,`restaurant_id`,`total`, `statut`,`date`,`methode`,`elements`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getClient_id());
            ps.setInt(2, p.getRestaurant_id());
            ps.setFloat(3, p.getTotal());
            ps.setString(4, p.getStatut());
            ps.setDate(5, p.getDate());
            ps.setString(6, p.getMethode());           
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `commande` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Commande p) {
        try {
            String req = "UPDATE `commande` SET `statut` = '" + p.getStatut()+ "' WHERE `commande`.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> getAll() {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Commande p = new Commande(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getDate(6), rs.getString(7));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
