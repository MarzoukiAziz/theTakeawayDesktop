/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import entities.Blog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.BlogService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BlogaddController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private TextArea contenu;
    @FXML
    private TextField titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    BlogService bs = new BlogService();

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        if(!titre.getText().equals("") && !contenu.getText().equals("")) {
            Blog b = new Blog("1", titre.getText(), contenu.getText(),"", "En Attente");
            bs.ajouter(b);
            bs.send();
            Alert alertA = new Alert(Alert.AlertType.NONE,"Blog Ajouté!",ButtonType.APPLY);
            alertA.show();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/blogList.fxml"));
                ap.getChildren().setAll(pane);
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Empty field!",ButtonType.APPLY);
            alertA.show();
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/blogList.fxml"));
                ap.getChildren().setAll(pane);
    }
    
}
