/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.commande;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.ElementDetails;
import edu.thetakeaway.services.CommandeService;
import edu.thetakeaway.services.ElementDetailsService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class CommandeDetailsUserScreenController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private Label dateLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label statutLabel;
    @FXML
    private TableView<ElementDetails> table;
    @FXML
    private TableColumn<ElementDetails, String> eleCl;
    @FXML
    private TableColumn<ElementDetails,Double> prixUniCl;
    @FXML
    private TableColumn<ElementDetails, Integer> quantiteCL;
    @FXML
    private TableColumn<ElementDetails,Double> prixCl;

    @FXML
    private Label restaurantLabel;
    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private Label clientLabel;
    @FXML
    private Button backBtn;
    @FXML
    private Button qrBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Commande c = SharedData.selectedCommande;
        restaurantLabel.setText(c.getRestaurant().getNom());
        dateLabel.setText(c.getDate() + "");
        prixLabel.setText(c.getPrix()+"");
        statutLabel.setText(c.getStatut());
        loadDetails(c);
        if(c.getStatut().equals("En attente")){
            cancelBtn.setDisable(false);
        }
        
        Image imagex = new Image("assets/qrcodelogo.png");
        ImageView view = new ImageView(imagex);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        //Setting the size of the button
        qrBtn.setPrefSize(120, 120);
        //Setting a graphic to the button
        qrBtn.setGraphic(view);
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
    private void cancel(ActionEvent event) {
            CommandeService cs = new CommandeService();
            SharedData.selectedCommande.setStatut("Annulé");
            cs.update(SharedData.selectedCommande);
            navigateTo(event, "CommandeDetailsUserScreen.fxml");
        
    }
    
    
    
        @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../restaurant/RestaurantsUserScreen.fxml");
    }
    
    @FXML
    private void navigateToMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../menu/MenuUserScreen.fxml");
    }
    
    @FXML
    private void navigateToPromotions(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../promotions/PromotionsUserScreen.fxml");
    }

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/UserDashboardScreen.fxml");
    }
    @FXML
    private void navigateToReserve(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReserveScreen.fxml");
    }
    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsScreen.fxml");
    }
    
    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reclamation/ReclamationsScreen.fxml");
    }
    
    @FXML
    private void navigateToRCommandes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../commande/CommandeUserScreen.fxml");
    }
    
    @FXML
    private void navigateToBlog(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../blog/blogList.fxml");
    }
    
    @FXML
    private void navigateToCartes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../user/CarteShow.fxml");
    }
    
    

    @FXML
    private void navigateToCommandes(ActionEvent event) {
        navigateTo(event, "CommandeUserScreen.fxml");
    }
    @FXML
    private void navigateToQrcode(ActionEvent event) {
        navigateTo(event, "QRCodeScreen.fxml");
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
