/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Synda
 */
public class DataSource {

    //connection maa base de donnes
    private Connection cnx;
    private static DataSource instance;
    
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/thetakeaway";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
               
        }
    }
    public static DataSource getInstance()
    {     
    if(instance == null)
    
      instance = new DataSource();
    return instance;
    
            
    }

    public Connection getCnx() {
        return cnx;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
