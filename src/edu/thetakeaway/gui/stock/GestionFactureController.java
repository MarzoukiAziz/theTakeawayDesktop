/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.stock;

import edu.thetakeaway.entities.Facture;
import edu.thetakeaway.entities.Fournisseur;
import edu.thetakeaway.entities.Ingrediant;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.entities.excel;
import edu.thetakeaway.services.ServiceFacture;
import edu.thetakeaway.services.ServiceFournisseur;
import edu.thetakeaway.services.ServiceIngrediant;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jxl.write.WriteException;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class GestionFactureController implements Initializable {

    Integer id;
    String nomf = "ff";
    @FXML
    private TextField frquantite;
    @FXML
    private TextField frprix;
    @FXML
    private TableColumn<Facture, String> frcol;
    @FXML
    private TableColumn<Facture, String> ingcol;
    @FXML
    private TableColumn<Facture, String> quantitecol;
    @FXML
    private TableColumn<Facture, Date> datecol;
    @FXML
    private TableColumn<Facture, Date> heurecol;
    @FXML
    private TableColumn<Facture, Float> pricxol;
    @FXML
    private TableView<Facture> affichagerefac;
    @FXML
    private ComboBox<String> cbfournisseur;
    @FXML
    private ComboBox<String> cbingrediant;
    ServiceFacture rs = new ServiceFacture();
    ServiceFournisseur sf = new ServiceFournisseur();
    ServiceIngrediant si = new ServiceIngrediant();
    ObservableList<String> fournisseur = FXCollections.observableArrayList(sf.getAllFournisseurName());
    ObservableList<String> ingrediant = FXCollections.observableArrayList(si.getAllIngrediantName());
    ObservableList<Facture> data = FXCollections.observableArrayList();
    @FXML
    private Button excel;
    @FXML
    private Button calcul;
    @FXML
    private Button closeBtn;
    @FXML
    private Button back;
    @FXML
    private AnchorPane stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbfournisseur.setItems(fournisseur);
        cbingrediant.setItems(ingrediant);

        refresh();

        TableColumn<Facture, Facture> consulterCol = new TableColumn<>("Consulter");
        consulterCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        consulterCol.setMinWidth(80);
        frcol.setMinWidth(50);
        consulterCol.setCellFactory(param -> new TableCell<Facture, Facture>() {
            private final Button consultBtn = new Button("Consulter");

            @Override
            protected void updateItem(Facture t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                consultBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");

                setGraphic(consultBtn);
                consultBtn.setOnAction(event -> {
                    SharedData.selectedFacture = t;
                    navigateFactureDetails(event);
                });

            }
        });
        affichagerefac.getColumns().add(consulterCol);
        refresh();

    }

    private void navigateFactureDetails(ActionEvent ae) {
        navigateTo(ae, "FactureDetails.fxml");
    }

    private void supprimerfacture(ActionEvent event) {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet annonce Facture  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceFacture rs = new ServiceFacture();
            rs.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
            affichagerefac.setItems(rs.getAll());
            affichagerefac.refresh();

        } else {
            alert2.close();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {

        if (frquantite.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("problem");
            alert.setHeaderText(null);
            alert.setContentText(" quantité invalide");
            alert.showAndWait();
        } else if (frprix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("problem");
            alert.setHeaderText(null);
            alert.setContentText(" prix invalide");
            alert.showAndWait();
        } else {
            ServiceFournisseur fs = new ServiceFournisseur();
            ServiceFacture sf = new ServiceFacture();
            ServiceIngrediant si = new ServiceIngrediant();
            HashMap<String, Fournisseur> fef = new HashMap<>();
            HashMap<String, Ingrediant> ief = new HashMap<>();

            // new ServiceRestaurant().getAll().stream().forEach(r->ref.put(r.getNom(),r));
            List<Fournisseur> fournisseur = fs.getAll();
            for (Fournisseur rrr : fournisseur) {
                fef.put(rrr.getNom(), rrr);
            }
            List<Ingrediant> ing = si.getAll();
            for (Ingrediant iii : ing) {
                ief.put(iii.getNom(), iii);
            }
            Facture f = new Facture(
                    fef.get(cbfournisseur.getValue()),
                    ief.get(cbingrediant.getValue()),
                    Integer.parseInt(frquantite.getText()),
                    Float.parseFloat(frprix.getText()),
                    Date.valueOf(LocalDate.now()), LocalTime.now());
            sf.ajouter(f);
            navigateTo(event, "GestionFacture.fxml");
        }

        refresh();
    }

    public void refresh() {

        data.clear();
        data = FXCollections.observableArrayList(rs.getAll());
        String a;
        frcol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFournisseur().getNom()));
        ingcol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIngrediant().getNom()));

        quantitecol.setCellValueFactory(new PropertyValueFactory<>("qunatite"));

        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        heurecol.setCellValueFactory(new PropertyValueFactory<>("heure"));
        pricxol.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        affichagerefac.setItems(data);
    }

    @FXML
    private void navigateToStock(ActionEvent event) {
        navigateTo(event, "ChoisirTable.fxml");

    }

    private void navigateTo(ActionEvent event, String path) {

        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource(path));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        }

    }

    @FXML
    private void excel(ActionEvent event) throws SQLException, IOException {

        excel exporter = new excel();
        try {
            exporter.Excel();
        } catch (WriteException ex) {
            Logger.getLogger(GestionFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void navigateToCalcul(ActionEvent event) {
        navigateTo(event, "calculator.fxml");

    }
         @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../restaurant/RestaurantsAdminScreen.fxml");
    }

    @FXML
    private void navigateToMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../menu/MenuAdminScreen.fxml");
    }

    @FXML
    private void navigateToPromotions(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../promotions/PromotionsAdminScreen.fxml");
    }

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/AdminDashboardScreen.fxml");
    }

    @FXML
    private void navigateToTables(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../tables/TablesAdminScreen.fxml");
    }

    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsAdminScreen.fxml");
    }

    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reclamation/ReclamationsAdminScreen.fxml");
    }

    @FXML
    private void navigateToCommandes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../commande/CommandeAdminScreen.fxml");
    }
    
     @FXML
    private void navigateToSock(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../stock/choisirTable.fxml");
    }

    @FXML
    private void navigateToBlog(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../blog/blogListBack.fxml");
    }
    
    @FXML
    private void navigateToUsers(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../user/ShowUser.fxml");
    }
    @FXML
    private void stat(ActionEvent event) {
        navigateTo(event, "Statistic.fxml");

    }

}
