package edu.thetakeaway.services;

import com.google.zxing.WriterException;
import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.ElementDetails;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.utils.DataSource;
import edu.thetakeaway.utils.QRCodeService;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandeService {

    Connection cnx = DataSource.getInstance().getCnx();

    public void add(Commande R ,ArrayList<ElementDetails> panier) {

        try {
            String requete = "INSERT INTO `commande` ( `restaurant_id`, `client_id`, `prix_total`,"
                    + " `statut`, `date`, `methode`, `point_utilisees`, `statut_paiement`)"
                    + " VALUES ( ?,?,?,?,?,?,?,?) ";
            PreparedStatement pst = cnx.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, R.getRestaurant().getId() + "");
            pst.setString(2, R.getClient().getId() + "");
            pst.setDouble(3, R.getPrix());
            pst.setString(4, R.getStatut());
            pst.setDate(5, R.getDate());
            pst.setString(6, R.getMethod());
            pst.setInt(7, 0);
            pst.setString(8, "done");
            pst.executeUpdate();
            System.out.println("commande ajouté");
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    R.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            String qrCodeText = R.toString();
            String filePath = "src/upload/qr/"+R.getId()+".png";
            int size = 125;
            String fileType = "png";
            File qrFile = new File(filePath);
            try {
                QRCodeService.createQRImage(qrFile, qrCodeText, size, fileType);
            } catch (WriterException ex) {
                Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
            }
            ElementDetailsService edSer = new ElementDetailsService();
                for (ElementDetails ed : panier) {
                    if(ed.getQuantite()>0){
                        ed.setCmd(R);
                        edSer.add(ed);
                    }
                }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void update(Commande R) {

        try {
            String req = "UPDATE `commande` SET `restaurant_id` = '" + R.getRestaurant().getId()
                    + "', `client_id` = '" + R.getClient().getId() + ""
                    + "', `prix_total` = '" + R.getPrix()
                    + "', `statut` = '" + R.getStatut()
                    + "', `date` = '" + R.getDate()
                    + "', `methode` = '" + R.getMethod()
                    + "' WHERE `commande`.`id` = " + R.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void supprimer(Commande cmd) {
        ElementDetailsService rps = new ElementDetailsService();
        for (ElementDetails r : rps.getByCommande(cmd)) {
            rps.supprimer(r.getId());
        }

        try {
            String req = "DELETE FROM `commande` WHERE id = " + cmd.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Commande> getAll() {
        ArrayList<Commande> myList = new ArrayList<>();
        try {
            String rq = "SELECT * FROM `commande`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rq);
            while (rs.next()) {
                Commande r = new Commande(rs.getInt(1), new RestaurantService().getById(rs.getInt("restaurant_id")),
                        new UserServices().getById(rs.getInt("client_id")), rs.getDouble("prix_total"), rs.getString("statut"), rs.getDate("date"), rs.getString("methode"));

                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return myList;

    }

    public ArrayList<Commande> getByRestaurantId(Restaurant res) {
        ArrayList<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande where restaurant_id = " + res.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                UserServices us = new UserServices();
                User u = us.getById(rs.getInt("client_id"));
                Commande r = new Commande(rs.getInt(1), res, u, rs.getDouble("prix_total"),
                        rs.getString("statut"), rs.getDate("date"), rs.getString("methode"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ArrayList<Commande> getByUserId(User user) {
        ArrayList<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande where client_id = " + user.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                UserServices us = new UserServices();
                User u = us.getById(user.getId());
                Commande r = new Commande(
                        rs.getInt(1),
                        new RestaurantService().getById(rs.getInt("restaurant_id")),
                        u, rs.getDouble("prix_total"), rs.getString("statut"), rs.getDate("date"), rs.getString("methode"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
