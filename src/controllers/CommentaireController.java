/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.xml.stream.events.Comment;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label contenu;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setCommentaire(Commentaire c) {
        contenu.setText(c.getContenu());
        date.setText(c.getDate());
    }
    
}
