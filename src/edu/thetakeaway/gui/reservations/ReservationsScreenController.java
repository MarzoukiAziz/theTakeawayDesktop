
package edu.thetakeaway.gui.reservations;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.ReservationService;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ReservationsScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, String> resCl;
    @FXML
    private TableColumn<Reservation, Date> dateCl;
    @FXML
    private TableColumn<Reservation, Time> haCl;
    @FXML
    private TableColumn<Reservation, Time> hdCl;
    @FXML
    private TableColumn<Reservation, Integer> pourCl;
    @FXML
    private TableColumn<Reservation, String> tablesCl;
    @FXML
    private TableColumn<Reservation, String> statutCl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationService rvs = new ReservationService();
        table.setSelectionModel(null);

        TableColumn<Reservation, Reservation> updateCol = new TableColumn<>("Annuler");
        updateCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateCol.setMinWidth(80);
        updateCol.setCellFactory(param -> new TableCell<Reservation, Reservation>() {
            private final Button cancelBtn = new Button("Annuler");

            @Override
            protected void updateItem(Reservation t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                cancelBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");
                HBox p = new HBox();
                if (t.getStatut().equals("En Attente")) {
                    p.getChildren().addAll(cancelBtn);
                }
                setGraphic(p);
                
                cancelBtn.setOnAction(event -> {
                    t.setStatut("Annulé");
                    ReservationService rs = new ReservationService();
                    rs.modifier(t);
                    loadReservationsInTableView(SharedData.currentUser);
                });
            }
        });
        table.getColumns().add(updateCol);
        loadReservationsInTableView(SharedData.currentUser);
    }

    public void loadReservationsInTableView(User u) {
        ReservationService em = new ReservationService();
        List<Reservation> reservations = em.getByUserId(u);
        ObservableList<Reservation> revData = FXCollections.observableArrayList(reservations);
        //clientCl.setCellValueFactory(new PropertyValueFactory<>("user"));
        resCl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));
        dateCl.setCellValueFactory(new PropertyValueFactory<>("date"));
        haCl.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
        hdCl.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
        pourCl.setCellValueFactory(new PropertyValueFactory<>("nbPersonne"));
        statutCl.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tablesCl.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getTables().stream().map(tab -> tab.getNumero() + "(" + tab.getNbPlaces() + ")").reduce("", (t1, t2) -> t1 + " " + t2));
        });
        table.setItems(revData);
        SharedData.currentUser = u;
    }

    //Navigation
    

    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReservationsScreen.fxml");
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
