package edu.thetakeaway.gui.reservations;

import edu.thetakeaway.entities.Table;
import edu.thetakeaway.services.TableService;
import edu.thetakeaway.utils.SharedData;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.services.ReservationService;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AvailableTablesScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private Button confirmerBtn;
    @FXML
    private AnchorPane resMap;
    @FXML
    private Text revDescriptionText;

    HashMap<CheckBox, Table> checkBoxs = new HashMap<CheckBox, Table>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Reservation rv = SharedData.preparedReservation;
            revDescriptionText.setText(
                    "Restaurant : " + rv.getRestaurant().getNom()
                    + "   Date : " + rv.getDate()
                    + "   Horaire : " + rv.getHeureArrive()
                    + " - " + rv.getHeureDepart() + "   Pour " + rv.getNbPersonne() + " personnes.");
            TableService ts = new TableService();
            List<Table> tables = ts.getTablesByRestaurant(SharedData.selectedRestaurant);

            addTablesToRestaurantMap(tables);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void addTablesToRestaurantMap(List<Table> tabs) {
        for (Table t : tabs) {
            CheckBox tabCB = new CheckBox(t.getNumero() + "");

            tabCB.setStyle("-fx-background-color: #0096FF;-fx-text-fill: #ffffff;-fx-font-size: 15;-fx-padding: 8 10;-fx-background-radius: 24;");
            tabCB.setLayoutX(t.getPosX());
            tabCB.setLayoutY(t.getPosY());
            checkBoxs.put(tabCB, t);
            resMap.getChildren().add(tabCB);
        }
    }

    @FXML
    private void confirm(ActionEvent actionEvent) {
        Reservation rv = SharedData.preparedReservation;
        for (CheckBox ch : checkBoxs.keySet()) {
            if(ch.isSelected()){
                rv.addTable(checkBoxs.get(ch));
            }
        }
        if(rv.getTables().size()==0){
            Alert a = new Alert(Alert.AlertType.ERROR, "Séléctionnez au moins une table pour pouvoir confirmer votre réservation!", ButtonType.APPLY.OK);
            a.setHeaderText("Pas De Tables Séléctionnés");
            a.setTitle("Error");
            a.showAndWait();
        }else{
            ReservationService rvs =new ReservationService();
            rvs.ajouter(rv);
            navigateToReservations(actionEvent);
        }
    }
    
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReservationsScreen.fxml");
    }
    @FXML
    private void navigateToReserve(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReserveScreen.fxml");
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
