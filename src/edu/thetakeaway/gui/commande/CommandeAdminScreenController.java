
package edu.thetakeaway.gui.commande;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.CommandeService;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CommandeAdminScreenController implements Initializable {

    @FXML
    private ComboBox<Restaurant> restaurantPicker;
    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, String> clientCl;
    @FXML
    private TableColumn<Commande, Date> dateCl;
    @FXML
    private TableColumn<Commande, String> statutCl;
    @FXML
    private TableColumn<Commande, Double> prixCl;
    @FXML
    private TableColumn<Commande, String> methodeCl;
    @FXML
    private TableColumn<Commande,Commande> consulterCol;
    //les status
    //En attente Accepté Rejeté  Annulé En traitement Rejeté
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService rvs = new CommandeService();
        table.setSelectionModel(null);
        clientCl.setMinWidth(50);
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
                -> loadCommandes(newVal));

        if (SharedData.selectedRestaurant != null) {
            restaurantPicker.setValue(SharedData.selectedRestaurant);
            loadCommandes(SharedData.selectedRestaurant);
        }
    }

    public void loadCommandes(Restaurant r) {
        CommandeService em = new CommandeService();
        List<Commande> commandes = em.getByRestaurantId(r);
        ObservableList<Commande> revData = FXCollections.observableArrayList(commandes);
        clientCl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getNom()));
        dateCl.setCellValueFactory(new PropertyValueFactory<>("date"));
        prixCl.setCellValueFactory(new PropertyValueFactory<>("prix"));
        methodeCl.setCellValueFactory(new PropertyValueFactory<>("method"));
        statutCl.setCellValueFactory(new PropertyValueFactory<>("statut"));
        table.setItems(revData);
        consulterCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        consulterCol.setCellFactory(param -> new TableCell<Commande, Commande>() {
            private final Button consultBtn = new Button("Consulter");
            @Override
            protected void updateItem(Commande t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                consultBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");
                
                setGraphic(consultBtn);
                consultBtn.setOnAction(event -> {
                    SharedData.selectedCommande = t;
                    navigateToCommandeDetails(event);
                });
                
            }
        });
        SharedData.selectedRestaurant = r;
    }
@FXML
    private void navigateToDashboard(ActionEvent ac) {
        navigateTo(ac, "../dashboard/AdminDashboardScreen.fxml");
    }
   
    @FXML
    private void navigateToCommandeDetails(ActionEvent ac){
        navigateTo(ac, "CommandeDetailsAdminScreen.fxml");
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
