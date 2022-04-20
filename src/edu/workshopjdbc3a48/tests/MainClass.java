/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Personne;
import edu.workshopjdbc3a48.entities.Restaurant;
import edu.workshopjdbc3a48.services.RestaurantCRUD;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.utils.DataSource;
import edu.workshopjdbc3a48.utils.MyConnection;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        MyConnection mc =new MyConnection();
        
       RestaurantCRUD rcd = new RestaurantCRUD();
        System.out.println(rcd.afficherRestaurants());
       
       
       
        
    }
    
}
