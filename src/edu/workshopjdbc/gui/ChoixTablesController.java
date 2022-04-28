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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class ChoixTablesController implements Initializable {

    @FXML
    private TextField textField;
    @FXML
    private Text savedNumbers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void fournisseur(ActionEvent event) {
               navigateTo(event, "AfficherFournisseur.fxml");

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

    private void navigateToFournisseur(ActionEvent event) {
                       navigateTo(event, "AfficherFournisseur.fxml");

    }

    private void navigateToIngredient(ActionEvent event) {
         navigateTo(event, "GestionIngredient.fxml");
    }

    private void navigateToFacture(ActionEvent event) {
                 navigateTo(event, "GestionFacture.fxml");

    }

    @FXML
    private void button1Clicked(ActionEvent event) {
    }

    @FXML
    private void button2Clicked(ActionEvent event) {
    }

    @FXML
    private void button3Clicked(ActionEvent event) {
    }

    @FXML
    private void button4Clicked(ActionEvent event) {
    }

    @FXML
    private void button5Clicked(ActionEvent event) {
    }

    @FXML
    private void button6Clicked(ActionEvent event) {
    }

    @FXML
    private void button7Clicked(ActionEvent event) {
    }

    @FXML
    private void button8Clicked(ActionEvent event) {
    }

    @FXML
    private void button9Clicked(ActionEvent event) {
    }

    @FXML
    private void button0Clicked(ActionEvent event) {
    }

    @FXML
    private void calculate(ActionEvent event) {
    }

    @FXML
    private void clearTextField(ActionEvent event) {
    }

    @FXML
    private void addAction(ActionEvent event) {
    }

    @FXML
    private void minusAction(ActionEvent event) {
    }

    @FXML
    private void divideAction(ActionEvent event) {
    }

    @FXML
    private void multiplicationAction(ActionEvent event) {
    }
    


}