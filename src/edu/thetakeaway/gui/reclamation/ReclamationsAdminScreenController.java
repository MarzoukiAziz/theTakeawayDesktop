
package edu.thetakeaway.gui.reclamation;

import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.services.ReclamationService;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class ReclamationsAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation,String> clientCl;
    @FXML
    private TableColumn<Reclamation,Date> dateCl;
    @FXML
    private TableColumn<Reclamation,String> sujetCl;
    @FXML
    private TableColumn<Reclamation,String> statutCl;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rcs = new ReclamationService();
        table.setSelectionModel(null);

        TableColumn<Reclamation, Reclamation> consulterCol = new TableColumn<>("Consulter");
        consulterCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        consulterCol.setMinWidth(80);
        clientCl.setMinWidth(50);
        consulterCol.setCellFactory(param -> new TableCell<Reclamation, Reclamation>() {
            private final Button consultBtn = new Button("Consulter");


            @Override
            protected void updateItem(Reclamation t, boolean empty) {
                super.updateItem(t, empty);

                if (t == null) {
                    setGraphic(null);
                    return;
                }
                consultBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");
                
                setGraphic(consultBtn);
                consultBtn.setOnAction(event -> {
                    SharedData.selectedReclamation = t;
                    navigateReclamationDetails(event);
                });
                
            }
        });
        table.getColumns().add(consulterCol);
        loadReclamationsInTable();
    }

    public void loadReclamationsInTable() {
        ReclamationService rm = new ReclamationService();
        List<Reclamation> reclamations = rm.getAll();
        ObservableList<Reclamation> recData = FXCollections.observableArrayList(reclamations);
        clientCl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNom()));
        dateCl.setCellValueFactory(new PropertyValueFactory<>("date"));
        sujetCl.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        statutCl.setCellValueFactory(new PropertyValueFactory<>("statut"));
        table.setItems(recData);
    }

    @FXML
    private void navigateReclamationDetails(ActionEvent ae){
        navigateTo(ae, "ReclamationDetailsAdminScreen.fxml");
    }
    
    @FXML
    private void navigateToReservations(MouseEvent event) {
    }

    @FXML
    private void navigateToTables(MouseEvent event) {
    }

    @FXML
    private void navigateToTables(ActionEvent event) {
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
