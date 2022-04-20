package edu.thetakeaway.tests;

import edu.thetakeaway.services.CarteService;
import edu.thetakeaway.services.UserServices;

public class test {

    public static void main(String[] args) {
            UserServices us = new UserServices();
            CarteService cc = new CarteService();
          
        System.out.println(us.getById(3));
        System.out.println(cc.getById(3));
    }
}
