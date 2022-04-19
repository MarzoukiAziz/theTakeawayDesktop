package edu.thetakeaway.tests;

import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.services.ReservationService;
import edu.thetakeaway.services.UserServices;
import java.util.List;

public class test {

    public static void main(String[] args) {
            UserServices us = new UserServices();
          
        System.out.println(us.getById(3));
    }
}
