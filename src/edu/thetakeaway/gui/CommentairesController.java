/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class CommentairesController implements Initializable {

    @FXML
    private TextField tfContenu1;
    @FXML
    private TextField tfdATE1;
    @FXML
    private Button ajouterbtn1;
    @FXML
    private Button supprimerbtn1;
    @FXML
    private TableView<?> tableCommentaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
