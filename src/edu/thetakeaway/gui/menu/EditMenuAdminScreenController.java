/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.menu;

import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.services.MenuService;
import edu.thetakeaway.utils.SharedData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditMenuAdminScreenController implements Initializable {

    @FXML
    private TextField nomTF;
    @FXML
    private TextField Publisher;
    @FXML
    private TextField prixTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private TextField categorieTF;
    
    File fileToUpload;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Menu m = SharedData.selectedMenu;
        nomTF.setText(m.getNom());
        Publisher.setText(m.getImage());
        prixTF.setText(m.getPrix()+"");
        descriptionTF.setText(m.getDescription());
        categorieTF.setText(m.getCategorie());
    }    


    @FXML
    private void addimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Publisher.setText(selectedFile.getName());
            fileToUpload=selectedFile;
        } else {
            System.out.println(" Picture file is not valid");
        }

    }

    @FXML
    private void modifyMenu(ActionEvent event) {
        if (nomTF.getText().isEmpty() || Publisher.getText().isEmpty() || prixTF.getText().isEmpty() || descriptionTF.getText().isEmpty() || categorieTF.getText().isEmpty() ) {
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
            if (prix <0) {

                Alert a = new Alert(Alert.AlertType.ERROR, "Le prix doit être positif", ButtonType.APPLY.OK);
                a.setHeaderText("Prix Invalide");
                a.setTitle("Error");
                a.showAndWait();

            } else {
                MenuService ts = new MenuService();
                String path="src/upload/";
                try {
                    Files.copy(fileToUpload.toPath(),
                            (new File(path + fileToUpload.getName())).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                ts.update(new Menu(SharedData.selectedMenu.getId(), nomTF.getText(), descriptionTF.getText(), prix, categorieTF.getText()   , Publisher.getText()));
                navigateToMenu(event);
            }
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
