
package edu.thetakeaway.utils;

import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.Table;
import edu.thetakeaway.entities.User;
import java.util.ArrayList;

public class SharedData {
    public static Restaurant selectedRestaurant;
    public static ArrayList<Integer> tablesNumber;
    public static Table selectedTable;
    public static User currentUser= new User(4);
    public static Boolean pickMapXY=false;
    public static Restaurant selectedRestaurantForReserve= new Restaurant(2,"Rades");
    public static Reservation preparedReservation;
    public static Reclamation selectedReclamation;
}
