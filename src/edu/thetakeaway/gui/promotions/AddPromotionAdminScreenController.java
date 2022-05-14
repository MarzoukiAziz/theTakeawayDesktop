package edu.thetakeaway.gui.promotions;

import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.services.MenuService;
import edu.thetakeaway.entities.Promotion;
import edu.thetakeaway.services.PromotionService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AddPromotionAdminScreenController implements Initializable {

    @FXML
    private DatePicker ddPicker;
    @FXML
    private Button addBtn;
    @FXML
    private DatePicker dfPicker;
    @FXML
    private ComboBox<Menu> elemntCB;
    @FXML
    private TextField Publisher;
    @FXML
    private TextField prixTF;
    File fileToUpload;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuService rs = new MenuService();
        List<Menu> menus = rs.getAll();
        elemntCB.setItems(FXCollections.observableArrayList(menus));
        elemntCB.setConverter(new StringConverter<Menu>() {
            @Override
            public String toString(Menu object) {
                return object.getNom();
            }

            @Override
            public Menu fromString(String string) {
                return new Menu();
            }
        });
    }

    @FXML
    private void addPromtion(ActionEvent event) {
        if (elemntCB.getValue() == null || Publisher.getText().isEmpty() || ddPicker.getValue() == null || dfPicker.getValue() == null || prixTF.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Tous les champs doivent être non vide!", ButtonType.APPLY.APPLY.OK);
            a.setHeaderText("Champ Vide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            double prix;
            try {
                prix = Double.parseDouble(prixTF.getText());
            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Le prix doit être numérique!", ButtonType.APPLY.OK);
                a.setHeaderText("Prix Invalide");
                a.setTitle("Error");
                a.showAndWait();
                return;
            }
            if (prix < 0) {

                Alert a = new Alert(Alert.AlertType.ERROR, "Le prix doit être positif", ButtonType.APPLY.OK);
                a.setHeaderText("Prix Invalide");
                a.setTitle("Error");
                a.showAndWait();

            } else {
                PromotionService ts = new PromotionService();
                String path = "src/upload/";
                try {
                    Files.copy(fileToUpload.toPath(),
                            (new File(path + fileToUpload.getName())).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                Date dd = new Date(ddPicker.getValue().getYear() - 1900, ddPicker.getValue().getMonthValue(), ddPicker.getValue().getDayOfMonth());
                Date df = new Date(dfPicker.getValue().getYear() - 1900, dfPicker.getValue().getMonthValue(), dfPicker.getValue().getDayOfMonth());
                ts.ajouter(
                        new Promotion(elemntCB.getValue(), dd, df, prix, Publisher.getText())
                );
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Promotion");
                tray.setMessage(elemntCB.getValue().getNom() + " est a seulement " + prix + "DT !");
                tray.setNotificationType(NotificationType.SUCCESS);
                Image imagex = new Image("assets/logo2.png");
                tray.setImage(imagex);
                tray.showAndDismiss(Duration.millis(3200));
                navigateToPromotions(event);
            }
        }
    }

    @FXML
    private void addimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Publisher.setText(selectedFile.getName());
            fileToUpload = selectedFile;
        } else {
            System.out.println(" Picture file is not valid");
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
