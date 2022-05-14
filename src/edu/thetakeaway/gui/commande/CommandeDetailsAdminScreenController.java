package edu.thetakeaway.gui.commande;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.ElementDetails;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.CommandeService;
import edu.thetakeaway.services.ElementDetailsService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CommandeDetailsAdminScreenController implements Initializable {

    @FXML
    private Label clientLabel;
    @FXML
    private Button closeBtn1;
    @FXML
    private Label dateLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label statutLabel;
    @FXML
    private ChoiceBox<String> stautCB;
    @FXML
    private Button updateBtn;
    @FXML
    private TableView<ElementDetails> table;
    @FXML
    private TableColumn<ElementDetails, String> eleCl;
    @FXML
    private TableColumn<ElementDetails, Double> prixUniCl;
    @FXML
    private TableColumn<ElementDetails, Integer> quantiteCL;
    @FXML
    private TableColumn<ElementDetails, Double> prixCl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Commande c = SharedData.selectedCommande;
        stautCB.setItems(FXCollections.observableArrayList("En attente", "Accepté", "Rejeté", "Annulé", "En traitement", "Rejeté"));
        clientLabel.setText(c.getClient().getNom());
        dateLabel.setText(c.getDate() + "");
        prixLabel.setText(c.getPrix()+"");
        statutLabel.setText(c.getStatut());
        loadDetails(c);
    }

    public void loadDetails(Commande c) {
        ElementDetailsService em = new ElementDetailsService();
        List<ElementDetails> eds = em.getByCommande(c);
        ObservableList<ElementDetails> revData = FXCollections.observableArrayList(eds);
        eleCl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getElement().getNom()));
        prixUniCl.setCellValueFactory(cellData -> new SimpleObjectProperty<Double>(cellData.getValue().getElement().getPrix()));
        prixCl.setCellValueFactory(cellData -> new SimpleObjectProperty<Double>(cellData.getValue().getElement().getPrix() * cellData.getValue().getQuantite()));
        quantiteCL.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        table.setItems(revData);
    }

    @FXML
    private void delete(ActionEvent event) {
        CommandeService cs = new CommandeService();
        cs.supprimer(SharedData.selectedCommande);
        navigateTo(event, "CommandeAdminScreen.fxml");
    }

    @FXML
    private void updateStaut(ActionEvent event) {
        if (stautCB.getValue() != null) {
            CommandeService cs = new CommandeService();
            SharedData.selectedCommande.setStatut(stautCB.getValue());
            cs.update(SharedData.selectedCommande);
            navigateTo(event, "CommandeDetailsAdminScreen.fxml");
        }
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
