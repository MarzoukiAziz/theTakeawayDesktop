package edu.thetakeaway.gui.restaurant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.RestaurantService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javafx.scene.control.TextField;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewRestaurantAdminScreenController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField Publisher;
    @FXML
    private TextField adresseTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField telphoneTF;
    @FXML
    private JFXTimePicker ho;
    @FXML
    private JFXTimePicker hf;
    @FXML
    private TextField xTF;
    @FXML
    private TextField yTF;
    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;

    private File fileToUpload;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void addimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);
        fileToUpload=selectedFile;
        if (selectedFile != null) {
            Publisher.setText(selectedFile.getName());
        } else {
            System.out.println(" Picture file is not valid");
        }

    }

    @FXML
    private void addRestaurant(ActionEvent event) {
        if (nomTF.getText().isEmpty() || Publisher.getText().isEmpty() || adresseTF.getText().isEmpty() || descriptionTF.getText().isEmpty() || telphoneTF.getText().isEmpty() || xTF.getText().isEmpty() || yTF.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Tous les champs doivent être non vide!", ButtonType.APPLY.APPLY.OK);
            a.setHeaderText("Champ Vide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            int num;
            double x, y;
            try {
                num = Integer.parseInt(telphoneTF.getText());
                x = Double.parseDouble(xTF.getText());
                y = Double.parseDouble(yTF.getText());
            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Le numéro et les coordonnes doivent être numériques!", ButtonType.APPLY.OK);
                a.setHeaderText("Type Invalide");
                a.setTitle("Error");
                a.showAndWait();
                return;
            }
            if (telphoneTF.getText().length() != 8) {

                Alert a = new Alert(Alert.AlertType.ERROR, "Le numéro doit être de taille 8", ButtonType.APPLY.OK);
                a.setHeaderText("Numéro Invalide");
                a.setTitle("Error");
                a.showAndWait();

            } else {
                RestaurantService ts = new RestaurantService();
                String path="src/upload/";
                try {
                    Files.copy(fileToUpload.toPath(),
                            (new File(path + fileToUpload.getName())).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                ts.add(new Restaurant(nomTF.getText(), adresseTF.getText(), descriptionTF.getText(), telphoneTF.getText(), new Time(ho.getValue().getHour(), ho.getValue().getMinute(), 0), new Time(hf.getValue().getHour(), hf.getValue().getMinute(), 0), Publisher.getText(), x, y));
                navigateToRestaurants(event);
            }
        }
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

}
