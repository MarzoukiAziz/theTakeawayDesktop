/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marzo
 */
public class RestaurantService {
    
    Connection cnx = DataSource.getInstance().getCnx();

   

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

  
  
}
