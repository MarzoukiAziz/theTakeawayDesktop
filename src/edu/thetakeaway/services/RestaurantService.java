package edu.thetakeaway.services;

import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.utils.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RestaurantService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void add(Restaurant R) {

        try {
            String requete = "INSERT INTO restaurant (nom , adresse , description , heure_ouverture, heure_fermeture ,architecture , telephone,images,x,y)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, R.getNom());
            pst.setString(2, R.getAdresse());
            pst.setString(3, R.getDescription());
            pst.setTime(4, R.getHeure_ouverture());
            pst.setTime(5, R.getHeure_fermeture());
            pst.setString(6, "/assets/archi.png");
            pst.setString(7, R.getTelephone());
            pst.setString(8, R.getImage());
            pst.setDouble(9, R.getX());
            pst.setDouble(10, R.getY());
            pst.executeUpdate();
            System.out.println("Restaurant ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void update(Restaurant R) {

        try {
            String req = "UPDATE `restaurant` SET `nom` = '" + R.getNom()
                    + "', `adresse` = '" + R.getAdresse()
                    + "', `description` = '" + R.getDescription()
                    + "', `heure_ouverture` = '" + R.getHeure_ouverture()
                    + "', `heure_fermeture` = '" + R.getHeure_fermeture()
                    + "', `telephone` = '" + R.getTelephone()
                    + "', `architecture` = '" + "/assets/archi.png"
                    + "', `images` = '" + R.getImage()
                    + "', `x` = '" + R.getX()
                    + "', `y` = '" + R.getY()
                    + "' WHERE `restaurant`.`id` = " + R.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `restaurant` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("restaurant deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Restaurant> getAll() {
        ArrayList<Restaurant> myList = new ArrayList<>();
        try {
            String rq = "SELECT * FROM `restaurant`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                Restaurant r = new Restaurant();
                r.setId(rs.getInt(1));
                r.setNom(rs.getString("nom"));
                r.setAdresse(rs.getString("adresse"));
                r.setDescription(rs.getString("description"));
                r.setHeure_ouverture(rs.getTime("heure_ouverture"));
                r.setHeure_fermeture(rs.getTime("heure_fermeture"));
                r.setTelephone(rs.getString("telephone"));
                r.setImage(rs.getString("images"));
                r.setX(rs.getDouble("x"));
                r.setY(rs.getDouble("y"));
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return myList;

    }

    public Restaurant getById(int id) {
        Restaurant r = null;
        try {
            String rq = "SELECT * FROM `restaurant` where id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rq);
            rs.next();
            r = new Restaurant();
            r.setId(id);
            r.setNom(rs.getString("nom"));
            r.setAdresse(rs.getString("adresse"));
            r.setDescription(rs.getString("description"));
            r.setHeure_ouverture(rs.getTime("heure_ouverture"));
            r.setHeure_fermeture(rs.getTime("heure_fermeture"));
            r.setTelephone(rs.getString("telephone"));
            r.setImage(rs.getString("images"));

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return r;
    }

}
