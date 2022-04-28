package edu.thetakeaway.gui.restaurant;

import com.dlsc.gmapsfx.GoogleMapView;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RestaurantDetailsUserScreenController implements Initializable {

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
    private Button commanderBtn;
    @FXML
    private Button reservaerBtn;
    @FXML
    private ImageView image;
    @FXML
    private Button mapBtn;

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
        Image img = new Image(getClass().getResourceAsStream("/upload/" + res.getImage()));
        image.setImage(img);
        image.setClip(null);
        image.setEffect(new DropShadow(20, Color.ALICEBLUE));
        Image imagex = new Image("assets/maps.png");
        ImageView view = new ImageView(imagex);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        //Setting the size of the button
        mapBtn.setPrefSize(120, 120);
        //Setting a graphic to the button
        mapBtn.setGraphic(view);
    }
    
    @FXML
    private void navigateToReserve(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReserveScreen.fxml");
    }
    
    
    @FXML
    private void navigateToLocalisation(ActionEvent actionEvent) {
        navigateTo(actionEvent, "RestaurantLocalisationScreen.fxml");
    }

    @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "RestaurantsUserScreen.fxml");
    }
    
     @FXML
    private void navigateToMenu(ActionEvent actionEvent){
        navigateTo(actionEvent,"../menu/MenuUserScreen.fxml");
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
