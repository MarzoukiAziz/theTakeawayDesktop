/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.gui;
  
               
import edu.workshopjdbc.entities.Fournisseur;
import edu.workshopjdbc.services.ServiceFournisseur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.Z;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class AfficherFournisseurController implements Initializable {
        private StackEcrans stackEcran;


    
    public Integer id;
    private Label lblNom;
    private Label lblPrenom;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Button modifierfrbtn;
    @FXML
    private Button supprimerfrbtn;
    @FXML
    private TextField searchfr;
    @FXML
    private TextField frtelephone;
    @FXML
    private TextField frnom;
    @FXML
    private TextField fradresse;
    @FXML
    private TextField fremail;
    @FXML
    private TableView<Fournisseur> affichagerefournisseur;
    @FXML
    private TableColumn<Fournisseur, String> nomcol;
    @FXML
    private TableColumn<Fournisseur, String> adressecol;
    @FXML
    private TableColumn<Fournisseur, String> telephonecol;
    @FXML
    private TableColumn<Fournisseur, String> emailcol;
    @FXML
    private TableColumn<?, ?> idcol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                                  ServiceFournisseur rs = new ServiceFournisseur();

                                
                                  affichagerefournisseur.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    id = rs.liste2()
                        .get(affichagerefournisseur.getSelectionModel().getSelectedIndex())
                        .getId();
              
                frnom.setText(rs.liste2()
                        .get(affichagerefournisseur.getSelectionModel().getSelectedIndex())
                        .getNom());
                
                fradresse.setText(rs.liste2()
                        .get(affichagerefournisseur.getSelectionModel()
                                .getSelectedIndex())
                        .getAdresse());
                
                frtelephone.setText(rs.liste2()
                        .get(affichagerefournisseur.getSelectionModel()
                                .getSelectedIndex())
                        .getTelephone()
                );
                
  
                fremail.setText(rs.liste2()
                        .get(affichagerefournisseur.getSelectionModel()
                                .getSelectedIndex())
                        .getEmail()
                ); 
     
                };
            
               
         }); 
       
        
         ObservableList<Fournisseur> list2;      
         list2 = rs.getAll(); // TODO
        //sort wela recherche
                    idcol.setCellValueFactory(new PropertyValueFactory<>("id"));

        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephonecol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
       affichagerefournisseur.setItems(list2);
       
       
       
       
       //recherche
         
        ObservableList<Fournisseur> liste;
        liste = rs.getAll();
        FilteredList<Fournisseur> filteredData = new FilteredList<>(liste, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        searchfr.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Fournisseur -> {
                // If filter text is empty, display all persons.
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Fournisseur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (Fournisseur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                
                else
                    return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(affichagerefournisseur.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        affichagerefournisseur.setItems(sortedData);
                        affichagerefournisseur.refresh();



   
    }
        
        
    
    
    public void setNom(String nom){
        lblNom.setText(nom);
    }
    
    public void setPrenom(String prenom){
        lblPrenom.setText(prenom);
    }

    @FXML
    private void ajouterfournisseur(ActionEvent event) 
         {
             

         
         if(frnom.getText().isEmpty()|| frnom.getText().length() < 8){
             
              Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("problem");
                alert.setHeaderText(null);
                alert.setContentText(" nom non vide");
                alert.showAndWait();}
         
         if(frtelephone.getText().isEmpty()|| frtelephone.getText().contains("[a-zA-Z]")){
             
              Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("problem");
                alert.setHeaderText(null);
                alert.setContentText(" Telephone nom vide");
                alert.showAndWait();}
         
         if(fradresse.getText().isEmpty()){
             
              Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("problem");
                alert.setHeaderText(null);
                alert.setContentText(" adresse no vide");
                alert.showAndWait();}
         

      else if(fremail.getText().isEmpty() || !(fremail.getText().contains("@"))){
              Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("problem");
                alert.setHeaderText(null);
                alert.setContentText(" email invalide");
                alert.showAndWait();}         
         
         else {
        ServiceFournisseur p = new ServiceFournisseur();
        Fournisseur f = new Fournisseur(frnom.getText(), fradresse.getText(), frtelephone.getText(), fremail.getText());
        p.ajouter(f);}
                                affichagerefournisseur.refresh();

}
    

    @FXML
    private void modifierfournisseur(ActionEvent event)  throws SQLException {
         
        Fournisseur r = affichagerefournisseur.getSelectionModel().getSelectedItem();
      

       // r.setNom(repasnom.getText());
     //  String memid = select1.getValue();
       // int id = Integer.parseInt(idd);
        r.setNom(frnom.getText());

        r.setAdresse(fradresse.getText());
        r.setTelephone(frtelephone.getText()); 
       
        r.setEmail(fremail.getText());
        
       
        ServiceFournisseur rs = new ServiceFournisseur();
        
        
        
          try {
                       
               rs.modifier(id, r.getNom(),r.getAdresse(),r.getTelephone(),r.getEmail() );
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
       // int id, String nom, String description, int price, String category, String adresse, String img
       affichagerefournisseur.setItems(rs.getRepaslistnew());
                               affichagerefournisseur.refresh();

    }
  
    @FXML
    private void supprimerfournisseur(ActionEvent event) 
            {
         Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer cet annonce Fournisseur  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceFournisseur rs = new ServiceFournisseur();
            rs.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
            affichagerefournisseur.setItems(rs.getAll());
                                    affichagerefournisseur.refresh();


        } else {
            alert2.close();
        }
    }
  

    @FXML
    private void searchfrs(ActionEvent event) {
    }

    @FXML
    private void onclickfacture(ActionEvent event) {      
        stackEcran.setScreen("GestionFacture");

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
        navigateTo(event, "ChoixTables.fxml");

    }
    
}
