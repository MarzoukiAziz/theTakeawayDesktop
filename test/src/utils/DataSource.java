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
 * @author MIMA
 */
// jdbc java data base connectivity howa el driver w mysql api permettant l'acces lel base de donnees
public class DataSource {
    
        private static DataSource instance;
    private Connection cnx;
 
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
//patern singleton mtena w fonct mteo fou9ha 
    public Connection getCnx() {
        return cnx;
    }
    
    
}
