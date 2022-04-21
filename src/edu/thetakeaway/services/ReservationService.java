package edu.thetakeaway.services;

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

public class ReservationService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservation` ( `client_id_id`, `date`, `heure_arrive`, `heure_depart`, `nb_personne`, `statut`, `restaurant_id`) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, r.getClient().getId() + "");
            ps.setString(2, r.getDate() + "");
            ps.setString(3, r.getHeureArrive() + "");
            ps.setString(4, r.getHeureDepart() + "");
            ps.setString(5, r.getNbPersonne() + "");
            ps.setString(6, r.getStatut() + "");
            ps.setString(7, r.getRestaurant().getId() + "");

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    r.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            
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

    public void modifier(Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `client_id_id` = '" + r.getClient().getId()
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

    public List<Reservation> getByRestaurantId(Restaurant r) {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation where restaurant_id = " + r.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                UserServices us = new UserServices();
                User u = us.getById(rs.getInt("client_id_id"));
                Reservation t = new Reservation(
                        rs.getInt("id"),
                        u,
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

    public List<Reservation> getByUserId(User user) {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation where client_id_id = " + user.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation t = new Reservation(
                        rs.getInt("id"),
                        user,
                        new RestaurantService().getById(rs.getInt("restaurant_id")),
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

    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Restaurant r = new Restaurant(1, "sdaa");
                User u = new User();
                Reservation t = new Reservation(
                        rs.getInt("id"),
                        u,
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
