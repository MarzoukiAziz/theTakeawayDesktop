/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.commande;

import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class QRCodeScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private Button backBtn;
    @FXML
    private ImageView qrcode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imagex = new Image("upload/qr/"+SharedData.selectedCommande.getId()+".png");
        qrcode.setImage(imagex);
        qrcode.setPreserveRatio(true);
    }    

    @FXML
    private void navigateToCommandeDetails(ActionEvent event) {
        navigateTo(event, "CommandeDetailsUserScreen.fxml");
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
