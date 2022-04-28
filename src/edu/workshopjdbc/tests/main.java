/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.tests;

import edu.workshopjdbc.entities.Facture;
import edu.workshopjdbc.entities.Fournisseur;
import edu.workshopjdbc.entities.Ingrediant;
import edu.workshopjdbc.entities.Restaurant;
import edu.workshopjdbc.services.ServiceFacture;
import edu.workshopjdbc.services.ServiceFournisseur;
import edu.workshopjdbc.services.ServiceIngrediant;
import edu.workshopjdbc.utils.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Synda
 */
public class main {

    public static void main(String[] args) {
//        
//        Fournisseur p1 = new Fournisseur ("sinda","Hamdi","fcghjk","dfhgjk");
//        Restaurant r1 = new Restaurant();
//                System.out.println("je suis le main console");
//
//        
//        Ingrediant p2 = new Ingrediant(r1, 80, "sucre");
//        Facture p3 = new Facture(p1, p2, 50,22, new Date(2022, 4, 11) , new Date(2022, 4, 11));
//        
//            ServiceIngrediant si = new ServiceIngrediant();
//            si.ajouter(p2);
//        
            ServiceFacture fc = new ServiceFacture();
            Facture f=new Facture(4, 3, 5, 20.0f, Date.valueOf(LocalDate.now()), LocalTime.now());
            System.out.println(fc.getAll());
        
 
       // DataSource ds = DataSource.getInstance(); //ajouter
      // DataSource ds1 = DataSource.getInstance(); //modifier
     //DataSource ds2 =DataSource.getInstance(); //supprim
        //singleton patron de conception applique aala ay class pour faire une seul instance : constructeur houwa li yaml les instance 
        //une instance : bch todkhelech baadhha wahda tebaath supp w wahda modif
        //objet :personne class :modelesation dun objet instance:sinda
   //interface generique :
    }

}
