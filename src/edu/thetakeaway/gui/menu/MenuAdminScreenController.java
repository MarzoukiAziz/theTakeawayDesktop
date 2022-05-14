package edu.thetakeaway.gui.menu;

import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.services.MenuService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
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

public class MenuAdminScreenController implements Initializable {

    @FXML
    private TableView<Menu> table;
    @FXML
    private TableColumn<Menu, String> nomCl;
    @FXML
    private TableColumn<Menu, String> descriptionCl;
    @FXML
    private TableColumn<Menu, Double> prixCl;
    @FXML
    private TableColumn<Menu, String> categorieCl;
    @FXML
    private TableColumn<Menu, Menu> imageCl;

    @FXML
    private Button newMenuBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuService rcs = new MenuService();
        table.setSelectionModel(null);

        imageCl.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        imageCl.setCellFactory(param -> new TableCell<Menu, Menu>() {
            ImageView imgV = new ImageView();

            @Override
            protected void updateItem(Menu t, boolean empty) {
                super.updateItem(t, empty);
                try {
                    Image img = new Image(getClass().getResourceAsStream("/upload/" + t.getImage()));
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
        
        
        TableColumn<Menu, Menu> updateCol = new TableColumn<>("Modifier");
        updateCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateCol.setCellFactory(param -> new TableCell<Menu, Menu>() {
            private final Button updateBtn = new Button("Modifier");

            @Override
            protected void updateItem(Menu t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                updateBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");

                setGraphic(updateBtn);
                updateBtn.setOnAction(event -> {
                    SharedData.selectedMenu = t;
                    navigateTo(event, "EditMenuAdminScreen.fxml");
                });
            }
        });

        //Delete Button
        TableColumn<Menu, Menu> deleteCol = new TableColumn<>("Supprimer");
        deleteCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteCol.setCellFactory(param -> new TableCell<Menu, Menu>() {
            private final Button deleteButton = new Button("Supprimer");

            @Override
            protected void updateItem(Menu t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                deleteButton.setStyle("-fx-background-color: #DC143C	;-fx-text-fill :#ffffff; ");
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    MenuService ms = new MenuService();
                    ms.supprimer(t.getId());
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Menu supprimé", ButtonType.APPLY.OK);
                    a.setHeaderText("Menu supprimé avec succés !");
                    a.setTitle("Done");
                    a.showAndWait();
                    loadMenuInTable();
                });
            }
        });
        table.getColumns().addAll(updateCol, deleteCol);
        loadMenuInTable();
    }

    public void loadMenuInTable() {
        MenuService rm = new MenuService();
        List<Menu> menus = rm.getAll();
        ObservableList<Menu> resData = FXCollections.observableArrayList(menus);
        nomCl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixCl.setCellValueFactory(new PropertyValueFactory<>("prix"));
        descriptionCl.setCellValueFactory(new PropertyValueFactory<>("description"));
        categorieCl.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        table.setItems(resData);
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
    private void navigateToNewMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "NewMenuAdminScreen.fxml");
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
