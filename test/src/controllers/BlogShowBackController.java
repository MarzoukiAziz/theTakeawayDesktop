/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import entities.Blog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.BlogService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BlogShowBackController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private TextArea contenu;
    @FXML
    private TextField titre;
    @FXML
    private ComboBox<String> cb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb.getItems().add("En Attente");
        cb.getItems().add("Ouvert");
        cb.getItems().add("Ferme");
    }    
    
    Blog blog;
        BlogService bs = new BlogService();

    void setBlog(Blog b) {
        blog = b;
        titre.setText(b.getTitle());
        contenu.setText(b.getContenu());
        cb.setValue(b.getStatut());
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        if(!titre.getText().equals("") && !contenu.getText().equals("")) {
            blog.setStatut(cb.getValue());
            bs.modifier(blog);
            bs.sms();
            Alert alertA = new Alert(Alert.AlertType.NONE,"Blog Validé!",ButtonType.APPLY);
            alertA.show();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/blogListBack.fxml"));
                ap.getChildren().setAll(pane);
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Empty field!",ButtonType.APPLY);
            alertA.show();
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        bs.supprimer(blog);
            Alert alertA = new Alert(Alert.AlertType.NONE,"Blog supprimé!",ButtonType.APPLY);
            alertA.show();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/blogListBack.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/blogListBack.fxml"));
                ap.getChildren().setAll(pane);
    }
    
}
