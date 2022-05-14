package edu.thetakeaway.gui.reclamation;

import edu.thetakeaway.entities.Reponse;
import edu.thetakeaway.services.ReponseService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NewReponseAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private TextArea reponseText;
    @FXML
    private Button answerBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addReponse(ActionEvent actionevent) {
        if (reponseText.getText().length() < 10) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Le réponse doit contenir au moins 10 caractéres!", ButtonType.APPLY.OK);
            a.setHeaderText("Réponse Invalide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            ReponseService rp = new ReponseService();
            Date d = new Date(System.currentTimeMillis());
            Time t = new Time(System.currentTimeMillis());
            Reponse reponse = new Reponse(SharedData.selectedReclamation, SharedData.currentUser, reponseText.getText(), d, t);
            rp.ajouter(reponse);
            navigateReclamationDetails(actionevent);
        }
    }

    @FXML
    private void resetText(ActionEvent actionevent) {
        reponseText.setText("");
    }

    @FXML
    private void navigateReclamationDetails(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReclamationDetailsAdminScreen.fxml");
    }
    
    
    
    
    @FXML
    private void navigateToNewPromotion(ActionEvent actionEvent) {
        navigateTo(actionEvent, "AddPromotionAdminScreen.fxml");
    }
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/AdminDashboardScreen.fxml");
    }
        @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../restaurant/RestaurantsAdminScreen.fxml");
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
