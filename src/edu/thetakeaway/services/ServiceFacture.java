/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.services;

import edu.thetakeaway.entities.Facture;
import edu.thetakeaway.entities.Fournisseur;
import edu.thetakeaway.entities.Ingrediant;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Synda
 */
public class ServiceFacture implements IService<Facture> {

    Connection cnx = DataSource.getInstance().getCnx();
    private ResultSet rs;
    private Statement ste;

    @Override
    public void ajouter(Facture p) {
        try {
            String req = "INSERT INTO `facture` ( `fournisseur_id`, `ingrediant_id`, `quantite`, `date`, `heure`, `prix_unitaire`) VALUES ('" + p.getFournisseur().getId() + "','" + p.getIngrediant().getId() + "', '" + p.getQunatite() + "', '" + p.getDate() + "', '" + p.getHeure() + "', '" + p.getPrix_unitaire() + "')";

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `facture` WHERE id=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("facture deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Facture p) {

        try {
            String req = "UPDATE facture SET fournisseur_id = '" + p.getId_fournisseur() + "', ingrediant_id = '" + p.getId_ingrediant() + "', quantite = '" + p.getQunatite() + "', date = '" + p.getDate() + "', heure = '" + p.getHeure() + "', prix = '" + p.getPrix_unitaire() + "' WHERE id =" + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("facture updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Facture> getAll() {
        ObservableList<Facture> list = FXCollections.observableArrayList();
        try {

            String req = "select * from facture";
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Facture p = new Facture(
                        new ServiceFournisseur().getById(rs.getInt("fournisseur_id")),
                        new ServiceIngrediant().getById(rs.getInt("ingrediant_id")),
                        rs.getInt("quantite"),
                        rs.getFloat("prix_unitaire"),
                        rs.getDate("date"),
                        rs.getTime("heure").toLocalTime());
                p.setId(rs.getInt("id"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Facture> liste2() {
        String req = "select * from facture";

        List<Facture> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                Ingrediant in = new Ingrediant();
                Fournisseur f = new Fournisseur();
                //get restaruant by id later 
                Facture p = new Facture(rs.getInt("id"), f, in, rs.getInt("quantite"), rs.getFloat("prix"), rs.getDate("date"), rs.getTime("heure").toLocalTime());
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceIngrediant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Facture> getRepaslistnew() throws SQLException {
        String req = "select  * from facture";
        ObservableList<Facture> list = FXCollections.observableArrayList();

        try {
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Facture r = new Facture();
                Ingrediant i = new Ingrediant();
                Fournisseur f = new Fournisseur();
                r.setFournisseur(f);
                r.setIngrediant(i);
                r.setId(rs.getInt("id"));
                r.setQunatite(rs.getInt("quantite"));
                r.setDate(rs.getDate("date"));
                r.setHeure(rs.getTime("heure").toLocalTime());

                r.setPrix_unitaire(rs.getInt("prix_unitaire"));

                list.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Integer> Statistiquee() {
        String req1 = "Select COUNT(*) from facture INNER JOIN fournisseur where nom='Carla Dawson' and fournisseur.id=facture.fournisseur_id";
        String req2 = "Select COUNT(*) from facture INNER JOIN fournisseur where nom='Steel Moon' and  fournisseur.id=facture.fournisseur_id";
        String req3 = "Select COUNT(*) from facture INNER JOIN fournisseur where nom='amir yasi' and  fournisseur.id=facture.fournisseur_id";
        List<Integer> a = new ArrayList<>();

        Statement st1, st2, st3, st4;
        try {
            st1 = cnx.createStatement();
            st2 = cnx.createStatement();
            st3 = cnx.createStatement();

            ResultSet rs1 = st1.executeQuery(req1);
            ResultSet rs2 = st2.executeQuery(req2);
            ResultSet rs3 = st3.executeQuery(req3);

            int Carla = 0, Steel = 0, amir = 0, Somme = 0, prctypeA = 0, prctypeB = 0, prctypeC = 0;

            while (rs1.next()) {
                Steel = rs1.getInt(1);
            }
            while (rs2.next()) {
                Carla = rs2.getInt(1);

            }
            while (rs3.next()) {
                amir = rs3.getInt(1);

            }

            Somme = Carla + Steel + amir;
            prctypeA = Carla * 100 / Somme;
            prctypeB = Steel * 100 / Somme;
            prctypeC = amir * 100 / Somme;

            a.add(prctypeA);
            a.add(prctypeB);
            a.add(prctypeC);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

}
