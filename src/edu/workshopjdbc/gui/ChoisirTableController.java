/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.gui;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class ChoisirTableController implements Initializable {

    @FXML
    private Button foutbtn;
    @FXML
    private Button ingbtn;
    @FXML
    private Button facbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navigateToFournisseur(ActionEvent event) {
                               navigateTo(event, "AfficherFournisseur.fxml");

    }

    @FXML
    private void navigateToIngredient(ActionEvent event) {
                 navigateTo(event, "GestionIngredient.fxml");

    }

    @FXML
    private void navigateToFacture(ActionEvent event) {
                         navigateTo(event, "GestionFacture.fxml");

    }
    
    
        private void navigateTo(ActionEvent event, String path) {
        
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource(path));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        
    }
    
}
    
}
