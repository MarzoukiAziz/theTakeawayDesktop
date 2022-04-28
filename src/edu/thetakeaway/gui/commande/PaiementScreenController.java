/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.commande;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.thetakeaway.utils.PaiementService;
import edu.thetakeaway.utils.SharedData;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class PaiementScreenController implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;
    @FXML
    private JFXTextField montant;
    @FXML
    private JFXTextField usermail;
    @FXML
    private JFXTextField First;
    @FXML
    private JFXTextField Last;
    @FXML
    private JFXTextField Number;
    @FXML
    private JFXTextField Card;
    @FXML
    private JFXTextField CVC;
    @FXML
    private JFXButton Pay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usermail.setText(SharedData.currentUser.getEmail());
        montant.setText(40+"");
        First.setText(SharedData.currentUser.getNom());
        Card.setText("**** **** **** 5556");
        CVC.setText("101");
    }

    @FXML
    private void payerService(ActionEvent event) throws IOException {

        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                PaiementService P = new PaiementService();

                P.RetrieveCustomer();
                Integer Dueamount = Integer.parseInt(montant.getText());
                P.payement(Dueamount);

                try {
                    Desktop.getDesktop().browse(new URL("https://buy.stripe.com/test_00g00H5Iu53Ug2A9AA").toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            TimeUnit.SECONDS.sleep(40);
        } catch (InterruptedException ex) {
            Logger.getLogger(PaiementScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        emailExecutor.shutdown();
        
        
        String title = "Payement succesful";
        String message = "You payment  has been Approved";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3200));
navigateTo(event, "CommandeUserScreen.fxml");

    }
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

}
