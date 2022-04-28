package edu.thetakeaway.gui.reservations;

import edu.thetakeaway.utils.SharedData;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import edu.thetakeaway.entities.Reservation;
import java.io.IOException;
import java.time.LocalTime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReserveScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private DatePicker datePick;
    @FXML
    private ComboBox<Time> heureCB;
    @FXML
    private ComboBox<Integer> departCB;
    @FXML
    private ComboBox<Integer> nbpCB;
    @FXML
    private Button tabBtn;
    @FXML
    private TextField restf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        restf.setText(SharedData.selectedRestaurant.getNom());

        //limit date picking in [today ; today+15days]
        datePick.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isAfter(LocalDate.now().plusDays(15)) || item.isBefore(LocalDate.now()));
            }
        });

        List<Integer> hours = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
        for (int h : hours) {
            heureCB.getItems().add(new Time(h, 0, 0));
            heureCB.getItems().add(new Time(h, 15, 0));
            heureCB.getItems().add(new Time(h, 30, 0));
            heureCB.getItems().add(new Time(h, 45, 0));
        }
        List<Integer> departTimes = Arrays.asList(15, 30, 45);
        departCB.getItems().addAll(departTimes);
        List<Integer> nbPersonnes = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        nbpCB.getItems().addAll(nbPersonnes);

    }

    @FXML
    private void showAvailableTables(ActionEvent actionEvent) {
        if (datePick.getValue() == null || heureCB.getValue() == null || departCB.getValue() == null || nbpCB.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Tous les champs doivent être non vide!", ButtonType.APPLY.OK);
            a.setHeaderText("Champ Vide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            LocalTime hd = heureCB.getValue().toLocalTime();
            hd=hd.plusMinutes(departCB.getValue());
            Date date = new Date(datePick.getValue().getYear()-1900, datePick.getValue().getMonthValue(), datePick.getValue().getDayOfMonth());
            Reservation rv = new Reservation(SharedData.currentUser, SharedData.selectedRestaurant, date, new Time(heureCB.getValue().getTime()), new Time(hd.getHour(), hd.getMinute(), 0), nbpCB.getValue(), "En Attente");
            SharedData.preparedReservation = rv;
            navigateToAvailableTables(actionEvent);
        }
    }
    @FXML
    private void navigateToAvailableTables(ActionEvent actionEvent) {
        navigateTo(actionEvent, "AvailableTablesScreen.fxml");
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
