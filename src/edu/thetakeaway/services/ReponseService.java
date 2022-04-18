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

public class ReponseService implements IService<Reponse> {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Reponse r) {
        try {
            String req = "INSERT INTO `reponse` ( `reclamation_id`, `author_id`, `contenu`, `date`, `heure`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getReclamation().getId()+"");
            ps.setString(2, r.getAuthor().getId()+"");
            ps.setString(3, r.getContenu());
            ps.setString(4, r.getDate().toString());
            ps.setString(5, r.getHeure().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reponse` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reponse deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reponse r) {
        try {
            String req = "UPDATE `reponse` SET `reclamation_id` = '" + r.getReclamation().getId() + "', `author_id` = '" + r.getAuthor().getId()+ "', `contenu` = '" + r.getContenu() + "', `date` = '" + r.getDate() + "', `heure` = '" + r.getHeure() + "' WHERE `reponse`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reponse updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reponse> getAll() {
        List<Reponse> list = new ArrayList<>();
        try {
            String req = "Select * from `table`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User a = new User();
                Reclamation rec = new Reclamation();
                Reponse r = new Reponse(rs.getInt("id"), rec, a, rs.getString("contenu"), rs.getDate("date"), rs.getTime("heure"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

  
    
    public List<Reponse> getReponsesByReclamatioId(int recId) {
        List<Reponse> list = new ArrayList<>();
        try {
            String req = "Select * from `table` where reclamation_id = "+recId;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User a = new User();
                Reclamation rec = new Reclamation();
                Reponse r = new Reponse(rs.getInt("id"), rec, a, rs.getString("contenu"), rs.getDate("date"), rs.getTime("heure"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
