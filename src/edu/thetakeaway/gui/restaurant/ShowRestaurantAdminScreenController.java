package edu.thetakeaway.gui.restaurant;

import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.RestaurantService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShowRestaurantAdminScreenController implements Initializable {

    @FXML
    private Label xyLabel;
    @FXML
    private ImageView image;
    @FXML
    private Label nomLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label horaireLabel;
    @FXML
    private Label telLabel;
    @FXML
    private Button UpdateBtn;
    @FXML
    private Button deleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Restaurant res = SharedData.selectedRestaurant;
        nomLabel.setText(res.getNom());
        nomLabel.setStyle("-fx-text-fill :#add8e6;-fx-font-size:35; -fx-font-weight: bold;");
        adresseLabel.setText(res.getAdresse());
        adresseLabel.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
        descriptionLabel.setText(res.getDescription());
        descriptionLabel.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
        horaireLabel.setText(res.getHeure_ouverture() + " - " + res.getHeure_fermeture());
        horaireLabel.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
        telLabel.setText(res.getTelephone());
        telLabel.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
        xyLabel.setText(res.getX() + " , " + res.getY());
        xyLabel.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
        Image img = new Image(getClass().getResourceAsStream("/upload/" + res.getImage()));
        image.setImage(img);
        image.setClip(null);
        image.setEffect(new DropShadow(20, Color.ALICEBLUE));

    }
    
    @FXML
    private void navigateToEditRestaurant(ActionEvent actionEvent) {
        navigateTo(actionEvent, "EditRestaurantAdminScreen.fxml");
    }

    @FXML
    private void deleteRestaurant(ActionEvent actionEvent){
        //Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer "+SharedData.selectedRestaurant.getNom()+" ?", ButtonType.YES,ButtonType.NO);
        RestaurantService rss = new RestaurantService();
        rss.supprimer(SharedData.selectedRestaurant.getId());
        navigateToRestaurants(actionEvent);
    }
    
    @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "RestaurantsAdminScreen.fxml");
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
   
    @FXML
    private void navigateToMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../menu/MenuAdminScreen.fxml");
    }

    @FXML
    private void navigateToPromotions(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../promotions/PromotionsAdminScreen.fxml");
    }

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/AdminDashboardScreen.fxml");
    }

    @FXML
    private void navigateToTables(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../tables/TablesAdminScreen.fxml");
    }

    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsAdminScreen.fxml");
    }

    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reclamation/ReclamationsAdminScreen.fxml");
    }

    @FXML
    private void navigateToCommandes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../commande/CommandeAdminScreen.fxml");
    }
    
     @FXML
    private void navigateToSock(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../stock/choisirTable.fxml");
    }

    @FXML
    private void navigateToBlog(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../blog/blogListBack.fxml");
    }
    
    @FXML
    private void navigateToUsers(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../user/ShowUser.fxml");
    }

}
