package edu.thetakeaway.services;

import edu.thetakeaway.entities.Admin;
import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.Table;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ReservationService implements IService<Reservation> {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservation` ( `client_id_id`, `admin_charge_id`, `date`, `heure_arrive`, `heure_depart`, `nb_personne`, `statut`, `restaurant_id`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getClient().getId() + "");
            ps.setString(2, r.getAdminCharge().getId() + "");
            ps.setString(3, r.getDate() + "");
            ps.setString(4, r.getHeureArrive() + "");
            ps.setString(5, r.getHeureDepart() + "");
            ps.setString(6, r.getNbPersonne() + "");
            ps.setString(7, r.getStatut() + "");
            ps.setString(8, r.getRestaurant().getId() + "");
            ps.executeUpdate();
            addSelectedTables(r);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addSelectedTables(Reservation r) {
        try {
            for (Table t : r.getTables()) {
                String req = "INSERT INTO `reservation_table` (`reservation_id`, `table_id`) VALUES (?, ?)";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setString(1, r.getId() + "");
                ps.setString(2, t.getId() + "");
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `reservation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `client_id_id` = '" + r.getClient().getId()
                    + "', `admin_charge_id` = '" + r.getAdminCharge()
                    + "', `date` = '" + r.getDate()
                    + "', `heure_arrive` = '" + r.getHeureArrive()
                    + "', `heure_depart` = '" + r.getHeureDepart()
                    + "', `nb_personne` = '" + r.getNbPersonne()
                    + "', `statut` = '" + r.getStatut()
                    + "', `restaurant_id` = '" + r.getRestaurant().getId()
                    + "' WHERE `reservation`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Restaurant r = new Restaurant();
                User u = new User();
                Admin a = new Admin();
                //find restaurant later with rs.getInt("restaurant_id_id")
                Reservation t = new Reservation(
                        rs.getInt("id"),
                        u,
                        a,
                        r,
                        rs.getDate("date"),
                        rs.getTime("heure_arrive"),
                        rs.getTime("heure_depart"),
                        rs.getInt("nb_personne"),
                        rs.getString("statut"),
                        new TableService().getTablesByReservation(rs.getInt("id")));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
