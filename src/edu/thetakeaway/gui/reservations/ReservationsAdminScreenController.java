/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.reservations;

import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.Table;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.ReservationService;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.services.TableService;
import edu.thetakeaway.utils.MailService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class ReservationsAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private ComboBox<Restaurant> restaurantPicker;
    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, String> clientCl;
    @FXML
    private TableColumn<Reservation, Date> dateCl;
    @FXML
    private TableColumn<Reservation, Integer> pourCl;
    @FXML
    private TableColumn<Reservation, String> tablesCl;
    @FXML
    private TableColumn<Reservation, String> statutCl;
    @FXML
    private TableColumn<Reservation, Time> haCl;
    @FXML
    private TableColumn<Reservation, Time> hdCl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationService rvs = new ReservationService();
        table.setSelectionModel(null);

        TableColumn<Reservation, Reservation> updateCol = new TableColumn<>("Mettre à jour");
        updateCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateCol.setMinWidth(80);
        clientCl.setMinWidth(50);
        updateCol.setCellFactory(param -> new TableCell<Reservation, Reservation>() {
            private final Button acceptBtn = new Button("Accepter");
            private final Button refuseBtn = new Button("Réfuser");
            private final Button cancelBtn = new Button("Annuler");

            @Override
            protected void updateItem(Reservation t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                acceptBtn.setStyle("-fx-background-color: #1EFF90;-fx-text-fill :#ffffff; ");
                refuseBtn.setStyle("-fx-background-color: #FF1E90;-fx-text-fill :#ffffff; ");
                cancelBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");
                HBox p = new HBox();
                if (t.getStatut().equals("Accepté")) {
                    p.getChildren().addAll(cancelBtn);
                } else if (t.getStatut().equals("En Attente")) {
                    p.getChildren().addAll(acceptBtn, refuseBtn);
                }
                setGraphic(p);
                acceptBtn.setOnAction(event -> {
                    t.setStatut("Accepté");
                    ReservationService rs = new ReservationService();
                    rs.modifier(t);
                    loadReservationsInTableView(SharedData.selectedRestaurant);
                    String mailBody="Nous avons le plaisir de vous informer que votre réservation pour "+
                            t.getClient().getNom()
                            +" le "
                            +t.getDate()
                            + " , a été acceptée.";
                    MailService.sendMail(t.getClient().getEmail(), "Reservation Accpetée", mailBody);
                });
                refuseBtn.setOnAction(event -> {
                    t.setStatut("Réfusé");
                    ReservationService rs = new ReservationService();
                    rs.modifier(t);
                    String mailBody="Nous sommes désolé de vous informer que votre réservation pour "+
                            t.getClient().getNom()
                            +" le "
                            +t.getDate()
                            + " , a été réfusée.";
                    MailService.sendMail(t.getClient().getEmail(), "Reservation Réfusé", mailBody);
                    loadReservationsInTableView(SharedData.selectedRestaurant);
                });
                cancelBtn.setOnAction(event -> {
                    t.setStatut("Annulé");
                    ReservationService rs = new ReservationService();
                    rs.modifier(t);
                    loadReservationsInTableView(SharedData.selectedRestaurant);
                });
            }
        });
        table.getColumns().add(updateCol);

        RestaurantService rs = new RestaurantService();
        List<Restaurant> restaurants = rs.getAll();
        restaurantPicker.setItems(FXCollections.observableArrayList(restaurants));
        restaurantPicker.setConverter(new StringConverter<Restaurant>() {
            @Override
            public String toString(Restaurant object) {
                return object.getNom();
            }

            @Override
            public Restaurant fromString(String string) {
                return new Restaurant(0, "");
            }
        });
        restaurantPicker.valueProperty().addListener((obs, oldVal, newVal)
                -> loadReservationsInTableView(newVal));

        if (SharedData.selectedRestaurant != null) {
            restaurantPicker.setValue(SharedData.selectedRestaurant);
            loadReservationsInTableView(SharedData.selectedRestaurant);
        }
    }

    public void loadReservationsInTableView(Restaurant r) {
        ReservationService em = new ReservationService();
        List<Reservation> reservations = em.getByRestaurantId(r);
        ObservableList<Reservation> revData = FXCollections.observableArrayList(reservations);
        //clientCl.setCellValueFactory(new PropertyValueFactory<>("user"));
        clientCl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getNom()));
        dateCl.setCellValueFactory(new PropertyValueFactory<>("date"));
        haCl.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
        hdCl.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
        pourCl.setCellValueFactory(new PropertyValueFactory<>("nbPersonne"));
        statutCl.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tablesCl.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getTables().stream().map(tab -> tab.getNumero() + "(" + tab.getNbPlaces() + ")").reduce("", (t1, t2) -> t1 + " " + t2));
        });
        table.setItems(revData);
        SharedData.selectedRestaurant = r;
    }

    //Navigation
    @FXML
    private void navigateToTables(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../tables/TablesAdminScreen.fxml");
    }

    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReservationsAdminScreen.fxml");
    }
    
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/AdminDashboardScreen.fxml");
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
