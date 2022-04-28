
package edu.thetakeaway.utils;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.Table;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.UserServices;
import java.util.ArrayList;
import java.util.HashSet;

public class SharedData {
    public static Restaurant selectedRestaurant;
    public static ArrayList<Integer> tablesNumber;
    public static Table selectedTable;
    public static User currentUser= new UserServices().getById(3);
    public static Boolean pickMapXY=false;
    public static Reservation preparedReservation;
    public static Reclamation selectedReclamation;
    public static Menu selectedMenu;
    public static HashSet<Menu> panier = new HashSet<Menu>();
    public static Commande selectedCommande;
}
