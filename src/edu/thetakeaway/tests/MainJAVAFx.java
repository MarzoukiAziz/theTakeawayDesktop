
package edu.thetakeaway.tests;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainJAVAFx extends Application {

    @Override
    public  void start(Stage primaryStage) {
        try {
            //Admin Panel
            Parent root = FXMLLoader.load(getClass().getResource("../gui/dashboard/AdminDashboardScreen.fxml"));
            //User
            //Parent root = FXMLLoader.load(getClass().getResource("../gui/dashboard/UserDashboardScreen.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("TheTakeAway");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainJAVAFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}
