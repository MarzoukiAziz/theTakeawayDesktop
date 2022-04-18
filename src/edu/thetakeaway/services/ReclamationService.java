
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.entities.Reponse;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService implements IService<Reclamation>  {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation r) {
        try {
            String req = "INSERT INTO `reclamation` ( `client_id_id`, `sujet`, `contenu`, `statut`, `date`, `heure`) VALUES ( ?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getUser().getId() + "");
            ps.setString(2, r.getSujet() );
            ps.setString(3, r.getContenu());
            ps.setString(4, r.getStatut()+ "");
            ps.setString(5, r.getDate()+ "");
            ps.setString(6, r.getHeure() + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int recId) {
         try {
            String req = "DELETE FROM `reclamation` WHERE id = " + recId;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation p) {
        
    }

    @Override
    public List<Reclamation> getAll() {
         List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User us = new User();                
                Reclamation t = new Reclamation(
                        rs.getInt("id"),
                        us,
                         rs.getString("sujet"),
                         rs.getString("contenu"),
                        rs.getString("statut"),
                        rs.getDate("date"),
                        rs.getTime("heure"));
                        
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
}
