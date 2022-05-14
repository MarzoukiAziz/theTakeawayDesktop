
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Fournisseur;
import edu.thetakeaway.entities.Ingrediant;
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
public class ServiceIngrediant implements IService<Ingrediant> {

    Connection cnx = DataSource.getInstance().getCnx();
    private ResultSet rs;
    private Statement ste;

    @Override
    public void ajouter(Ingrediant p) {
        try {
            String req = " INSERT INTO `ingrediant` ( `restaurant_id`, `nom`, `quantite`) VALUES ('" + p.getRestaurant().getId() + "','" + p.getNom() + "','" + p.getQuantite() + "')";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `ingrediant` WHERE `ingrediant`.`id` = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ingrediant deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//3wed nafss eli 3mlto

    @Override
    public void modifier(Ingrediant p) {
        try {
            String req = "";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ingrediant updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override

    public ObservableList<Ingrediant> getAll() {
        ObservableList<Ingrediant> list = FXCollections.observableArrayList();
        List<Ingrediant> id = new ArrayList<>();
        try {
            String req = "Select * from ingrediant";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Ingrediant p = new Ingrediant(new RestaurantService().getById(rs.getInt("restaurant_id")), rs.getInt("id"), rs.getInt("quantite"), rs.getString("nom"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);

        return list;

    }

    public List<Ingrediant> liste2() {
        String req = "select * from ingrediant";

        List<Ingrediant> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                Restaurant r = new Restaurant();
                //get restaruant by id later 
                Ingrediant p = new Ingrediant(r, rs.getInt("id"), rs.getInt("quantite"), rs.getString("nom"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceIngrediant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Ingrediant> getRepaslistnew() throws SQLException {
        String req = "select  id,restaurant_id, nom, quantite from ingrediant";
        ObservableList<Ingrediant> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Ingrediant r = new Ingrediant();
                Restaurant s = new Restaurant();

                r.setRestaurant(s);
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setQuantite(rs.getInt("quantite"));

                list.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<String> getAllIngrediantName() {
        List<String> list = new ArrayList<>();
        try {
            String req = "select * from ingrediant";

            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {

                list.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public int getIdIngrediantByName(String name) {
        try {
            String req = "select * from ingrediant";

            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {

                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void getIdrestaurantByName(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public Ingrediant getById(int id) {
        Ingrediant p = null;
        try {
            String req = "Select * from `ingrediant` where id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
p = new Ingrediant(new RestaurantService().getById(rs.getInt("restaurant_id")), rs.getInt("id"), rs.getInt("quantite"), rs.getString("nom"));                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

}
