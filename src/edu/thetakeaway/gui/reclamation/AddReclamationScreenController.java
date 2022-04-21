
package edu.thetakeaway.gui.reclamation;

import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.services.ReclamationService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddReclamationScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private TextField sujetTf;
    @FXML
    private TextArea contenuTf;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void addReclamation(ActionEvent actionEvent){
        Date d = new Date(System.currentTimeMillis());
        Time t= new Time(System.currentTimeMillis());
        Reclamation rec= new Reclamation(SharedData.currentUser, sujetTf.getText(), contenuTf.getText(), "Ouvert", d, t);
        ReclamationService rs = new ReclamationService();
        rs.ajouter(rec);
        navigateToReclamations( actionEvent);
    }

    @FXML
    private void resetTextFields(ActionEvent event) {
        sujetTf.setText("");
        contenuTf.setText("");
    }

   
    
    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReclamationsScreen.fxml");
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
