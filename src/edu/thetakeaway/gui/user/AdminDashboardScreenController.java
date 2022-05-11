/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.user;

import edu.thetakeaway.services.CarteServices;
import edu.thetakeaway.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class AdminDashboardScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
     @FXML
    private TextField UserNumer;
      @FXML
    private TextField nbusers;
     
    @FXML
private TextField AdmineNumer;
    
       @FXML
    private TextField CarteNumer;
    
     @FXML
    private Button ClientsBnt;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
     @FXML
    private void openSignup(ActionEvent event) throws IOException {
          Stage stage= new Stage();
    stage.setTitle("Inscription");
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("InscriptionUser.fxml"))));
    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }  
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "AdminDashboardScreen.fxml");
    }
    @FXML
    private void navigateToTables(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../tables/TablesAdminScreen.fxml");
    }
    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsAdminScreen.fxml");
    }
    
    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        System.out.println("----------");
        navigateTo(actionEvent, "../reclamation/ReclamationsScreen.fxml");
        
    }
    @FXML
    private void navigateToUsers(ActionEvent actionEvent) {
        System.out.println("----------");
        navigateTo(actionEvent, "../user/ShowUser.fxml");
        
    }
    @FXML
    private void navigateToLogin(ActionEvent actionEvent) {
         
              String tilte = "Log Out ";
            String message = "user logout success D";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS );
            tray.showAndDismiss(Duration.millis(3000));
        navigateTo(actionEvent, "LoginUser.fxml");
    }
  
     @FXML
 private void navigateTo(ActionEvent actionEvent, String path) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource(path));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        }
    }
        @FXML
    private void CountUsers() {
         try {
             UserServices us = new UserServices();
             int i=0;
             
             i = us.NumberUsers();
             String s=String.valueOf(i);
             nbusers.setText(s);
         } catch (SQLException ex) {
             Logger.getLogger(AdminDashboardScreenController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
       @FXML
    private void CountAdmine() {
         try {
             UserServices us = new UserServices();
             int i=0;
             
             i = us.NumberAmin();
             String s=String.valueOf(i);
             AdmineNumer.setText(s);
         } catch (SQLException ex) {
             Logger.getLogger(AdminDashboardScreenController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      @FXML
    private void CountCartes() {
         try {
             CarteServices us = new CarteServices();
             int i=0;
             
             i = us.NumberCartes();
             String s=String.valueOf(i);
             CarteNumer.setText(s);
         } catch (SQLException ex) {
             Logger.getLogger(AdminDashboardScreenController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

  
    
    
}
