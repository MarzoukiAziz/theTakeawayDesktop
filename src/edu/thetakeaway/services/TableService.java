package edu.thetakeaway.services;

import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.Table;
import edu.thetakeaway.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableService implements IService<Table> {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Table t) {
        try {
            String req = "INSERT INTO `table` (`restaurant_id_id`, `pos_x`, `pos_y`, `nb_palces`, `numero`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getRestaurant().getId() + "");
            ps.setString(2, t.getPosX() + "");
            ps.setString(3, t.getPosY() + "");
            ps.setString(4, t.getNbPlaces() + "");
            ps.setString(5, t.getNumero() + "");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `table` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Table t) {
        try {
            String req = "UPDATE `table` SET `restaurant_id_id` = '" + t.getRestaurant().getId() + "', `pos_x` = '" + t.getPosX() + "', `pos_y` = '" + t.getPosY() + "', `nb_palces` = '" + t.getNbPlaces() + "', `numero` = '" + t.getNumero() + "' WHERE `table`.`id` = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Table updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Table> getAll() {
        List<Table> list = new ArrayList<>();
        try {
            String req = "Select * from `table`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Restaurant r = new Restaurant();
                //find restaurant later with rs.getInt("restaurant_id_id")
                Table t = new Table(rs.getInt("id"), rs.getInt("pos_x"), rs.getInt("pos_y"), rs.getInt("nb_palces"), rs.getInt("numero"), r);
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Table getById(int id) {
        Table t = null;
        try {
            String req = "Select * from `table` WHERE id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            Restaurant r = new Restaurant();
            rs.next();
            //find restaurant later with rs.getInt("restaurant_id_id")
             t = new Table(rs.getInt("id"), rs.getInt("pos_x"), rs.getInt("pos_y"), rs.getInt("nb_palces"), rs.getInt("numero"), r);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
    
    public ArrayList<Table> getTablesByReservation(int revId) {
        ArrayList<Table> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `reservation_table` WHERE reservation_id = "+revId;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(getById(rs.getInt("table_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
