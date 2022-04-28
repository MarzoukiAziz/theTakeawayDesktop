package edu.thetakeaway.gui.menu;

import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.services.MenuService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
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

public class NewMenuAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
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
    private void addMenu(ActionEvent event) {
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
                ts.add(new Menu(nomTF.getText(), descriptionTF.getText(), prix, categorieTF.getText()   , Publisher.getText()));
                navigateToMenu(event);
            }
        }
    }

    @FXML
    private void navigateToMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "MenuAdminScreen.fxml");
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
