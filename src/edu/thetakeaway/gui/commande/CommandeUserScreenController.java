package edu.thetakeaway.gui.commande;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.CommandeService;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CommandeUserScreenController implements Initializable {

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, String> restaurantCl;
    @FXML
    private TableColumn<Commande, Date> dateCl;
    @FXML
    private TableColumn<Commande, Double> prixCl;
    @FXML
    private TableColumn<Commande, String> methodeCl;
    @FXML
    private TableColumn<Commande, String> statutCl;
    @FXML
    private TableColumn<Commande, Commande> consulterCol;
    @FXML
    private Button waitingBtn;
    @FXML
    private Button acceptedBtn;
    @FXML
    private Button traitementBtn;
    @FXML
    private Button rejectedBtn;
    @FXML
    private Button canceledBtn;
    @FXML
    private Button allBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService rvs = new CommandeService();
        table.setSelectionModel(null);
        restaurantCl.setMinWidth(50);
        RestaurantService rs = new RestaurantService();
        List<Restaurant> restaurants = rs.getAll();

        //En attente Accepté Rejeté  Annulé En traitement
        waitingBtn.setOnAction(event -> {
            loadCommandes("En attente");
        });
        acceptedBtn.setOnAction(event -> {
            loadCommandes("Accepté");
        });
        rejectedBtn.setOnAction(event -> {
            loadCommandes("Rejeté");
        });
        canceledBtn.setOnAction(event -> {
            loadCommandes("Annulé");
        });
        traitementBtn.setOnAction(event -> {
            loadCommandes("En traitement");
        });
        allBtn.setOnAction(event -> {
            loadCommandes("");
        });
        loadCommandes("");
    }

    public void loadCommandes(String statut) {

        CommandeService em = new CommandeService();
        List<Commande> commandes = em.getByUserId(SharedData.currentUser);
        commandes = commandes.stream()
                .filter(r -> r.getStatut().contains(statut))
                .collect(Collectors.toCollection(ArrayList::new));

        ObservableList<Commande> revData = FXCollections.observableArrayList(commandes);
        restaurantCl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));
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
    }

    private void navigateToCommandeDetails(ActionEvent ac) {
        navigateTo(ac, "CommandeDetailsUserScreen.fxml");
    }
    @FXML
    private void navigateToDashboard(ActionEvent ac) {
        navigateTo(ac, "../dashboard/UserDashboardScreen.fxml");
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
