/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.thetakeaway.gui.blog;

import edu.thetakeaway.entities.Blog;
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
import edu.thetakeaway.services.BlogService;
import java.sql.Date;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
            Date d = new Date(System.currentTimeMillis());
            Blog b = new Blog("1", titre.getText(), contenu.getText(),d, "En Attente");
            bs.ajouter(b);
            bs.send();
            Alert alertA = new Alert(Alert.AlertType.NONE,"Blog Ajouté!",ButtonType.APPLY);
            alertA.show();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("blogList.fxml"));
                ap.getChildren().setAll(pane);
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Empty field!",ButtonType.APPLY);
            alertA.show();
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
