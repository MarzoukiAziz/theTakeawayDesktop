/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import entities.Blog;
import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.CommentaireService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BlogShowController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Label titre;
    @FXML
    private Label contenu;
    @FXML
    private VBox vbox;
    
    Blog blog;
    
    CommentaireService cs = new CommentaireService();
    @FXML
    private TextField send;
    @FXML
    private Label count;
    
    int x;

    
    void setBlog(Blog b) throws IOException {
        blog = b;
        x = 0;
        titre.setText(blog.getTitle());
        contenu.setText(blog.getContenu());
        for (Commentaire c : cs.getByIdBlog(blog.getId())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/commentaire.fxml"));
            Parent root = loader.load();
            CommentaireController controller = (CommentaireController) loader.getController();
            controller.setCommentaire(c);
            vbox.getChildren().add(root);
            x++;
            
        }
        this.count.setText("Commentaire: "+x);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        Commentaire c = new Commentaire("1", now.toString(), send.getText(), blog.getId());
        if(!send.getText().equals("")) {
            cs.ajouter(c);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/commentaire.fxml"));
            Parent root = loader.load();
            CommentaireController controller = (CommentaireController) loader.getController();
            controller.setCommentaire(c);
            vbox.getChildren().add(root);
            send.setText("");
            x++;
            this.count.setText("Commentaire: "+x);

        }
        
            
    }
     @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/blogList.fxml"));
                ap.getChildren().setAll(pane);
    }
}
