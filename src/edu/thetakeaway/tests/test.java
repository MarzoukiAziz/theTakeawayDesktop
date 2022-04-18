package edu.thetakeaway.tests;

import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.services.ReservationService;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        ReservationService rs = new ReservationService();
        List<Reservation> revs = rs.getAll();
        System.out.println(revs.get(0).getTables());
    }
}
