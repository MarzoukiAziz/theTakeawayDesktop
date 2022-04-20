package edu.thetakeaway.gui.templates;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class TemplateAdminController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //exemple mta3 navigation 
    @FXML
    private void navigateToSomePage(ActionEvent actionEvent) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("!!!!!!path!!!!!!!"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        }
    }
}
