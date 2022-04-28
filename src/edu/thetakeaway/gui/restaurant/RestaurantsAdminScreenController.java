package edu.thetakeaway.gui.restaurant;

import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RestaurantsAdminScreenController implements Initializable {

    @FXML
    private TableView<Restaurant> table;
    @FXML
    private Button newRestoBtn;
    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private TableColumn<Restaurant, String> nomCl;
    @FXML
    private TableColumn<Restaurant, String> adresseCl;
    @FXML
    private TableColumn<Restaurant, String> descriptionCl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RestaurantService rcs = new RestaurantService();
        table.setSelectionModel(null);

        TableColumn<Restaurant, Restaurant> consulterCol = new TableColumn<>("Consulter");
        consulterCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        consulterCol.setMinWidth(80);
        consulterCol.setCellFactory(param -> new TableCell<Restaurant, Restaurant>() {
            private final Button consultBtn = new Button("Consulter");

            @Override
            protected void updateItem(Restaurant t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                consultBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");
                setGraphic(consultBtn);
                consultBtn.setOnAction(event -> {
                    SharedData.selectedRestaurant = t;
                    navigateTo(event, "ShowRestaurantAdminScreen.fxml");
                    
                });

            }
        });
        table.getColumns().add(consulterCol);
        loadRestaurantsInTable();
    }

    public void loadRestaurantsInTable() {
        RestaurantService rm = new RestaurantService();
        List<Restaurant> restaurants = rm.getAll();
        ObservableList<Restaurant> resData = FXCollections.observableArrayList(restaurants);
        nomCl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseCl.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        descriptionCl.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.setItems(resData);
    }
     @FXML
    private void navigateToNewRestaurant(ActionEvent actionEvent){
        navigateTo(actionEvent, "NewRestaurantAdminScreen.fxml");
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
