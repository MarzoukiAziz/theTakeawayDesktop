
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

public class ReclamationService  {
    Connection cnx = DataSource.getInstance().getCnx();

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

    public void supprimer(Reclamation rec) {
         try {
             ReponseService rps = new ReponseService();
            for(Reponse r:rps.getReponsesByReclamatioId(rec)){
                rps.supprimer(r.getId());
            }
            String req = "DELETE FROM `reclamation` WHERE id = " + rec.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Reclamation p) {
        try {
            
            String req = "UPDATE `reclamation` SET `statut` = '" + p.getStatut()+"' where id = "+p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reclamation> getAll() {
         List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                                
                Reclamation t = new Reclamation(
                        rs.getInt("id"),
                        new UserServices().getById(rs.getInt("client_id_id")),
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
    
    
    public List<Reclamation> getByUserId(User u) {
         List<Reclamation> list = new ArrayList<>();
        try {
            String req = "Select * from reclamation where client_id_id = "+u.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                                
                Reclamation t = new Reclamation(
                        rs.getInt("id"),
                        u,
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
