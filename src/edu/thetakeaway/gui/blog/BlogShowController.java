/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.thetakeaway.gui.blog;

import edu.thetakeaway.entities.Blog;
import edu.thetakeaway.entities.Commentaire;
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
import edu.thetakeaway.services.CommentaireService;
import java.sql.Date;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;

    
    void setBlog(Blog b) throws IOException {
        blog = b;
        x = 0;
        titre.setText(blog.getTitle());
        contenu.setText(blog.getContenu());
        for (Commentaire c : cs.getByIdBlog(blog.getId())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commentaire.fxml"));
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
        Date d = new Date(System.currentTimeMillis()); 
        Commentaire c = new Commentaire("1", d, send.getText(), blog.getId());
        if(!send.getText().equals("")) {
            cs.ajouter(c);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("commentaire.fxml"));
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("blogList.fxml"));
                ap.getChildren().setAll(pane);
    }
    
        @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../restaurant/RestaurantsUserScreen.fxml");
    }
    
    @FXML
    private void navigateToMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../menu/MenuUserScreen.fxml");
    }
    
    @FXML
    private void navigateToPromotions(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../promotions/PromotionsUserScreen.fxml");
    }

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/UserDashboardScreen.fxml");
    }
    @FXML
    private void navigateToReserve(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReserveScreen.fxml");
    }
    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsScreen.fxml");
    }
    
    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reclamation/ReclamationsScreen.fxml");
    }
    
    @FXML
    private void navigateToRCommandes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../commande/CommandeUserScreen.fxml");
    }
    
    @FXML
    private void navigateToBlog(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../blog/blogList.fxml");
    }
    
    @FXML
    private void navigateToCartes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../user/CarteShow.fxml");
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
