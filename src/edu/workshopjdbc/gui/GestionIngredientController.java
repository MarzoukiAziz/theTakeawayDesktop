/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.gui;

import edu.workshopjdbc.entities.Facture;
import edu.workshopjdbc.entities.Ingrediant;
import edu.workshopjdbc.entities.Restaurant;
import edu.workshopjdbc.services.ServiceRestaurant;
import edu.workshopjdbc.services.ServiceIngrediant;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import edu.workshopjdbc.entities.Facture;
import edu.workshopjdbc.entities.Ingrediant;
import edu.workshopjdbc.services.ServiceFacture;
import edu.workshopjdbc.services.ServiceFournisseur;
import edu.workshopjdbc.services.ServiceIngrediant;
import edu.workshopjdbc.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class GestionIngredientController implements Initializable {

    Integer id;
    Restaurant r;

    @FXML
    private Button ajoutbtn;
    @FXML
    private TextField frquantite;
    @FXML
    private TextField frnom;
    @FXML
    private TableView<Ingrediant> affichagereing;
    @FXML
    private TableColumn<Ingrediant, String> restaurantcol;
    @FXML
    private TableColumn<Ingrediant, String> nomcol;
    @FXML
    private TableColumn<Ingrediant, String> quantitecol;
    @FXML
    private Button supprimerfrbtn;
    private TextField frresto;
    @FXML
    private ComboBox<String> cbresto;

    ServiceRestaurant rs = new ServiceRestaurant();
    ServiceIngrediant ing = new ServiceIngrediant();

    ObservableList<String> restaurant = FXCollections.observableArrayList(rs.getAllRestaurantByName());
    ObservableList<Ingrediant> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Ingrediant,Ingrediant> updateCol;
    @FXML
    private ComboBox<Restaurant> restaurantPicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbresto.setItems(restaurant);


                affichagereing.setSelectionModel(null);

        
        
        
        
                
    //    updateCol.setCellFactory(param -> new TableCell<Ingrediant, Ingrediant>() {
      
      //      private final Button cancelBtn = new Button("Annuler");

        //    @Override
           // protected void updateItem(Ingrediant t, boolean empty) {
             //   super.updateItem(t, empty);

               // if (t == null) { setGraphic(null); return;  }
             //   cancelBtn.setStyle("-fx-background-color: #1E90FF;-fx-text-fill :#ffffff; ");
                
               // setGraphic(cancelBtn);
               
                //cancelBtn.setOnAction(event -> {
                  // ServiceIngrediant rss = new ServiceIngrediant();
                   //rss.supprimer(t.getId());
                    //navigateTo(event, "GestionIngredient.fxml");
           //     });}  });
       // affichagereing.getColumns().add(updateCol);
        
        
        
        
        
         ServiceRestaurant rs = new ServiceRestaurant();
        List<Restaurant> restaurants = rs.getAll();
        restaurantPicker.setItems(FXCollections.observableArrayList(restaurants));
        restaurantPicker.setConverter(new StringConverter<Restaurant>() {
            @Override
            public String toString(Restaurant object) {
                return object.getNom();
            }

            @Override
            public Restaurant fromString(String string) {
                return new Restaurant(0, "");
            }
        });
        restaurantPicker.valueProperty().addListener((obs, oldVal, newVal)
                -> loadFactureInTableView(newVal));

        if (SharedData.selectedRestaurant != null) {
            restaurantPicker.setValue(SharedData.selectedRestaurant);
            loadFactureInTableView(SharedData.selectedRestaurant);
        }
    }
        public void loadFactureInTableView(Restaurant r) {
        ServiceRestaurant em = new ServiceRestaurant();
        List<Ingrediant> ingrediant = em.getByRestaurantId(r);
        ObservableList<Ingrediant> revData = FXCollections.observableArrayList(ingrediant);
        //clientCl.setCellValueFactory(new PropertyValueFactory<>("user"));
           restaurantcol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantitecol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      
    
        affichagereing.setItems(revData);
        SharedData.selectedRestaurant = r;

    }
    
    


    @FXML
    private void ajoutering(ActionEvent event) {
        if (frnom.getText().isEmpty() || frquantite.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Tous les champs doivent être non vide!", ButtonType.APPLY.OK);
            a.setHeaderText("Champ Vide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            String nom;
            int qu;
            try {
                qu = Integer.parseInt(frquantite.getText());
                nom = frnom.getText();
            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "La quantité doit être numérique!", ButtonType.APPLY.OK);
                a.setHeaderText("Type Invalide");
                a.setTitle("Error");
                a.showAndWait();
                return;
            }
            if (nom.length() < 3) {

                Alert a = new Alert(Alert.AlertType.ERROR, "Le nom doit avoir au min 3 char!", ButtonType.APPLY.OK);
                a.setHeaderText("Nom Invalide");
                a.setTitle("Error");
                a.showAndWait();
            } else if (qu < 0) {
                Alert a = new Alert(Alert.AlertType.ERROR, "La quantité doit être >0", ButtonType.APPLY.OK);
                a.setHeaderText("Quantité Invalide");
                a.setTitle("Error");
                a.showAndWait();
            } else {
                ServiceIngrediant sf = new ServiceIngrediant();
                HashMap<String ,Restaurant> ref = new HashMap<>();
                
                // new ServiceRestaurant().getAll().stream().forEach(r->ref.put(r.getNom(),r));
                List<Restaurant > resto = rs.getAll();
                for(Restaurant rrr:resto){
                    ref.put(rrr.getNom(), rrr);
                }
                Ingrediant f = new Ingrediant(ref.get(cbresto.getValue()), Integer.parseInt(frquantite.getText()), frnom.getText());
                System.out.println(f);
                sf.ajouter(f);
                navigateTo(event, "GestionIngredient.fxml");
            }
        }

    }


    @FXML
    private void supprimering(ActionEvent event) {

        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer ce ingrediant  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceIngrediant rs = new ServiceIngrediant();
            rs.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
            affichagereing.setItems(rs.getAll());
            affichagereing.refresh();

        } else {
            alert2.close();
        }
    }

    public void refresh() {

        data.clear();
        data = FXCollections.observableArrayList(ing.getAll());
        restaurantcol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRestaurant().getNom()));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantitecol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        affichagereing.setItems(data);
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
    private void navigateToStock(ActionEvent event) {
        navigateTo(event, "ChoisirTable.fxml");

    }

    @FXML
    private void initialize(ActionEvent event) {
    }
}
