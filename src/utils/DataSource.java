/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mehdibehira
 */
public class DataSource {
    
        private static DataSource instance;
    private Connection cnx;
    // pour mamp serveur sur mac changer le port sur 8889 pour autre serveur
    //windows 3308 ou verifier le port
    private final String URL = "jdbc:mysql://localhost:3306/thetakeaway";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
