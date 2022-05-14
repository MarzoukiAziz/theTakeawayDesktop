/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.promotions;

import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.entities.Promotion;
import edu.thetakeaway.services.PromotionService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
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
public class PromotionsAdminScreenController implements Initializable {

    @FXML
    private TableView<Promotion> table;
    @FXML
    private TableColumn<Promotion,Promotion> bannerCl;
    @FXML
    private TableColumn<Promotion,String> eleCl;
    @FXML
    private TableColumn<Promotion,Date> ddCl;
    @FXML
    private TableColumn<Promotion,Date> dfCl;
    @FXML
    private TableColumn<Promotion,Double> prixCl;
    @FXML
    private Button newMenuBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PromotionService rcs = new PromotionService();
        table.setSelectionModel(null);

        bannerCl.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        bannerCl.setCellFactory(param -> new TableCell<Promotion, Promotion>() {
            ImageView imgV = new ImageView();

            @Override
            protected void updateItem(Promotion t, boolean empty) {
                super.updateItem(t, empty);
                try {
                    Image img = new Image(getClass().getResourceAsStream("/upload/" + t.getBanner()));
                    imgV.setImage(img);
                    imgV.setFitHeight(150);
                    imgV.setFitWidth(150);
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(imgV);

            }
        });
        
        //Delete Button
        TableColumn<Promotion, Promotion> deleteCol = new TableColumn<>("Supprimer");
        deleteCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteCol.setCellFactory(param -> new TableCell<Promotion, Promotion>() {
            private final Button deleteButton = new Button("Supprimer");

            @Override
            protected void updateItem(Promotion t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                deleteButton.setStyle("-fx-background-color: #DC143C	;-fx-text-fill :#ffffff; ");
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    PromotionService ms = new PromotionService();
                    ms.supprimer(t);
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Promotion supprimée", ButtonType.APPLY.OK);
                    a.setHeaderText("Promotion supprimée avec succés !");
                    a.setTitle("Done");
                    a.showAndWait();
                    loadMenuInTable();
                });
            }
        });
        table.getColumns().addAll(deleteCol);
        loadMenuInTable();
    }

    public void loadMenuInTable() {
        PromotionService rm = new PromotionService();
        List<Promotion> promos = rm.getAll();
        ObservableList<Promotion> resData = FXCollections.observableArrayList(promos);
        ddCl.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dfCl.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        prixCl.setCellValueFactory(new PropertyValueFactory<>("prixPromo"));
        eleCl.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getElement().getNom());
        });
        table.setItems(resData);
    }
    
    @FXML
    private void navigateToNewPromotion(ActionEvent actionEvent) {
        navigateTo(actionEvent, "AddPromotionAdminScreen.fxml");
    }
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/AdminDashboardScreen.fxml");
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
