/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Personne;
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
        
        Personne p1 = new Personne(3,"Abdelaziz", "M");
        Personne p2 = new Personne("Tarak", "Ayari");
        Personne p3 = new Personne("Samir", "Sankou7");
        Personne p4 = new Personne("Sinda", "Hamdi");
        
        ServicePersonne sp = new ServicePersonne();
        
        /*sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter2(p3);
        sp.ajouter2(p4);*/
        
        sp.supprimer(3);
        
    }
    
}
