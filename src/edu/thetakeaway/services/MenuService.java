
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void add(Menu R) {

        try {
            String requete = "INSERT INTO menu_element (nom , description , prix , categorie , image , options )"
                    + "VALUES(?,?,?,?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, R.getNom());
            pst.setString(2, R.getDescription());
            pst.setDouble(3, R.getPrix());
            pst.setString(4, R.getCategorie());
            pst.setString(5, R.getImage());
            pst.setString(6, "");
            pst.executeUpdate();
            System.out.println("menu ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void update(Menu R) {

        try {
            String req = "UPDATE `menu_element` SET `nom` = '" + R.getNom()
                    + "', `description` = '" + R.getDescription()
                    + "', `prix` = '" + R.getPrix()
                    + "', `categorie` = '" + R.getCategorie()
                    + "', `image` = '" + R.getImage()
                    
                    + "' WHERE `menu_element`.`id` = " + R.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `menu_element` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("menu deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Menu> getAll() {
        ArrayList<Menu> myList = new ArrayList<>();
        try {
            String rq = "SELECT * FROM `menu_element`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                Menu r = new Menu();
                r.setId(rs.getInt(1));
                r.setNom(rs.getString("nom"));
                r.setDescription(rs.getString("description"));
                r.setPrix(rs.getDouble("prix"));
                r.setImage(rs.getString("image"));
                r.setCategorie(rs.getString("categorie"));
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return myList;

    }
    
    public Menu getById(int id) {
        Menu r=new Menu();
        try {
            String rq = "SELECT * FROM `menu_element` where id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                r.setId(rs.getInt(1));
                r.setNom(rs.getString("nom"));
                r.setDescription(rs.getString("description"));
                r.setPrix(rs.getDouble("prix"));
                r.setImage(rs.getString("image"));
                r.setCategorie(rs.getString("categorie"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return r;

    }


}

