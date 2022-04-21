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
