package edu.thetakeaway.tests;

import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.services.ReservationService;
import edu.thetakeaway.services.TableService;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        System.out.println(new TableService().getById(4));
    }
}
