package edu.thetakeaway.services;

import edu.thetakeaway.entities.Promotion;
import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromotionService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Promotion r) {
        try {
            String req = "INSERT INTO `promotion` (`element_id`, `date_debut`, `date_fin`, `banner`, `prix_promo`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getElement().getId() + "");
            ps.setString(2, r.getDateDebut() + "");
            ps.setString(3, r.getDateFin() + "");
            ps.setString(4, r.getBanner() + "");
            ps.setString(5, r.getPrixPromo() + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Promotion p) {
        try {

            String req = "DELETE FROM `promotion` WHERE id = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("promotion deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Promotion> getAll() {
        ArrayList<Promotion> list = new ArrayList<>();
        try {
            String req = "Select * from promotion";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Promotion t = new Promotion(
                        rs.getInt("id"),
                        new MenuService().getById(rs.getInt("element_id")),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getDouble("prix_promo"),
                        rs.getString("banner"));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
