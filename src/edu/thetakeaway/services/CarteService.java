/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Carte;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafrafi
 */
public class CarteService implements IService<Carte> {
    Connection cnx = DataSource.getInstance().getCnx();
    

    @Override
    public void ajouter(Carte t) {
         try {
            String req = "INSERT INTO `cart_bancaire` (`id`, `numero`, `nomcomplet`, `datexp`, `cvv`, `client_id`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getId() + "");
            ps.setString(2, t.getNumero() + "");
            ps.setString(3, t.getNomcomplet() + "");
            ps.setString(4, t.getDatexp() + "");
            ps.setString(5, t.getCvv() + "");
            ps.setString(6, t.getUserid().getId() + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `cart_bancaire` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Carte deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Carte t) {
         try {
            String req = "UPDATE `cart_bancaire` SET `id` = '" + t.getId() + "', `numero` = '" + t.getNumero() + "', `nomcomplet` = '" + t.getNomcomplet() + "', `datexp` = '" + t.getDatexp() + "', `cvv` = '" + t.getCvv() + "', `client_id` = '" + t.getUserid().getId() + "' WHERE `cart_bancaire`.`id` = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Carte updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Carte> getAll() {
         List<Carte> list = new ArrayList<>();
        try {
            String req = "Select * from `cart_bancaire`";
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User r = new User();
                //find restaurant later with rs.getInt("restaurant_id_id")
                Carte p = new Carte(rs.getInt("id"), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),r);
                list.add(p);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public Carte getById(int id){
        int k;
        String m;
        Carte t = new Carte();
        try {
            String req = ("Select * from `cart_bancaire` WHERE id = " + id);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           
            rs.next();
                
                t.setId(rs.getInt("id"));
                t.setNumero(rs.getString("numero"));
                t.setNomcomplet(rs.getString("nomcomplet"));
                t.setDatexp(rs.getString("datexp"));
                t.setCvv(rs.getString("cvv"));
                
                
            
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    
}
