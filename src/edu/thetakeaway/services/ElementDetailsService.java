
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.ElementDetails;
import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ElementDetailsService {
     Connection cnx = DataSource.getInstance().getCnx();

    public void add(ElementDetails R) {

        try {
            String requete = "INSERT INTO `element_details` ( `element_id_id`, `commande_id`, `quantite`, `options`) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, R.getElement().getId() + "");
            pst.setString(2, R.getCmd().getId() + "");
            pst.setDouble(3, R.getQuantite());
            pst.setString(4, "");
            pst.executeUpdate();
            System.out.println("details ajouté");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }



    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `element_details` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("details deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<ElementDetails> getByCommande(Commande cmd) {
        ArrayList<ElementDetails> myList = new ArrayList<>();
        try {
            String rq = "SELECT * FROM `element_details` where commande_id = "+cmd.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                ElementDetails r = new ElementDetails(rs.getInt(1), new MenuService().getById(rs.getInt("element_id_id")),cmd,rs.getInt("quantite"));
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return myList;

    }

    
}
