/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.tests;

import edu.thetakeaway.entities.Blog_client;
import edu.thetakeaway.services.ServiceBlog_client;
import edu.thetakeaway.utils.DataSource;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        DataSource ds=new DataSource();
        ServiceBlog_client rs=new ServiceBlog_client();
        
        ServiceBlog_client sp = new ServiceBlog_client();
        //sp.afficher(new Blog_client("hh", "hh", "hh", "hhhhh"));
        System.out.println(rs.getAll());
     
        /*sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter2(p3);
        sp.ajouter2(p4);*/
        
        //sp.ajouter(new Blog_client("java", "hello world", "ouvert", "hh", "01/02/2000"));
        
    }
    
}
