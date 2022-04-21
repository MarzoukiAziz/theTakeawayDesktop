package edu.thetakeaway.gui.tables;
import edu.thetakeaway.entities.Restaurant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import edu.thetakeaway.entities.Table;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.services.TableService;
import edu.thetakeaway.utils.SharedData;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TablesAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private ComboBox<Restaurant> restaurantPicker;
    @FXML
    private TableView<Table> table;
    @FXML
    private TableColumn<Table, Integer> cl1;
    @FXML
    private TableColumn<Table, Integer> cl2;
    @FXML
    private TableColumn<Table, Integer> cl3;
    @FXML
    private TableColumn<Table, Integer> cl4;
    @FXML
    private AnchorPane resMap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableService ts = new TableService();
        table.setSelectionModel(null);
        //Update Button
        TableColumn<Table, Table> updateCol = new TableColumn<>("Modifier");
        updateCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateCol.setCellFactory(param -> new TableCell<Table, Table>() {
            private final Button updateBtn = new Button("Modifier");

            @Override
            protected void updateItem(Table t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                updateBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");

                setGraphic(updateBtn);
                updateBtn.setOnAction(event -> {
                    SharedData.selectedTable = t;
                    navigateTo(event, "UpdateTableAdminScreen.fxml");
                });
            }
        });

        //Delete Button
        TableColumn<Table, Table> deleteCol = new TableColumn<>("Supprimer");
        deleteCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteCol.setCellFactory(param -> new TableCell<Table, Table>() {
            private final Button deleteButton = new Button("Supprimer");

            @Override
            protected void updateItem(Table t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                deleteButton.setStyle("-fx-background-color: #DC143C	;-fx-text-fill :#ffffff; ");
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    ts.supprimer(t.getId());
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Table supprimé", ButtonType.APPLY.OK);
                    a.setHeaderText("Table supprimé avec succés !");
                    a.setTitle("Error");
                    a.showAndWait();
                    loadTablesInTableView(SharedData.selectedRestaurant);
                });
            }
        });
        table.getColumns().addAll(updateCol, deleteCol);

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
                -> loadTablesInTableView(newVal));

        if (SharedData.selectedRestaurant != null) {
            restaurantPicker.setValue(SharedData.selectedRestaurant);
            loadTablesInTableView(SharedData.selectedRestaurant);
        }
    }

    public void loadTablesInTableView(Restaurant r) {
        resMap.getChildren().removeIf(x -> x instanceof Label);
        TableService em = new TableService();
        List<Table> tables = em.getTablesByRestaurant(r);
        ObservableList<Table> tablesData = FXCollections.observableArrayList(tables);
        cl1.setCellValueFactory(new PropertyValueFactory<>("numero"));
        cl2.setCellValueFactory(new PropertyValueFactory<>("posX"));
        cl3.setCellValueFactory(new PropertyValueFactory<>("posY"));
        cl4.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        table.setItems(tablesData);
        SharedData.selectedRestaurant = r;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        tables.stream().forEach(t -> nums.add(t.getNumero()));
        SharedData.tablesNumber = nums;
        addTablesToRestaurantMap(tables);
    }

    private void addTablesToRestaurantMap(List<Table> tabs) {
        for (Table t : tabs) {
            Label tabLabel = new Label(t.getNumero() + "");
            tabLabel.setStyle("-fx-background-color: #0096FF;-fx-text-fill: #ffffff;-fx-font-size: 15;-fx-padding: 2 5;-fx-background-radius: 24;");
            tabLabel.setLayoutX(t.getPosX());
            tabLabel.setLayoutY(t.getPosY());
            resMap.getChildren().add(tabLabel);
        }
    }

    @FXML
    private void addNewTableByClickingOnMap(MouseEvent me) {
        int x = (int) me.getX();
        int y = (int) me.getY();
        Table t = new Table();
        t.setPosX(x);
        t.setPosY(y);
        SharedData.selectedTable = t;
        SharedData.pickMapXY=true;
        if (restaurantPicker.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Choisir un restaurant avant pouvoir ajouter une nouvelle table!", ButtonType.OK);
            a.setHeaderText("Choisir un restaurant");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            try {
                Parent exercices_parent = FXMLLoader.load(getClass().getResource("AddTableAdminScreen.fxml"));
                Scene ex_section_scene = new Scene(exercices_parent);
                Stage second_stage = (Stage) ((Node) me.getSource()).getScene().getWindow();
                second_stage.setScene(ex_section_scene);
                second_stage.show();
            } catch (IOException ex) {

            }
        }
    }

    //Navigation
    @FXML
    private void navigateToTables(ActionEvent actionEvent) {
        navigateTo(actionEvent, "TablesAdminScreen.fxml");
    }

    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsAdminScreen.fxml");
    }
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/AdminDashboardScreen.fxml");
    }
    @FXML
    private void navigateToNewTable(ActionEvent actionEvent) {
        if (restaurantPicker.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Choisir un restaurant avant pouvoir ajouter une nouvelle table!", ButtonType.OK);
            a.setHeaderText("Choisir un restaurant");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            navigateTo(actionEvent, "AddTableAdminScreen.fxml");
        }

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
