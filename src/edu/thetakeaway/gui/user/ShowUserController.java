/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.thetakeaway.gui.user;

import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafrafi
 */
public class ShowUserController implements Initializable {

     @FXML
    private TableView<User> TableUser;
    @FXML
    private TableColumn< User, Integer> cnumtel;
    @FXML
    private TableColumn<User, String> cNom;
    @FXML
    private TableColumn<User, String> cPrenom;
    @FXML
    private TableColumn<User, String> cMail;
    @FXML
    private Button btnupdate;

    ObservableList<User> oblist1 = FXCollections.observableArrayList();
    
    
     
     ObservableList<User> ob1 = FXCollections.observableArrayList();
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflast;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfmdp;
    @FXML
    private Button btnadd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
       
    }    

    
    

    @FXML
    private void update(ActionEvent event) {
        
        int numtel = Integer.parseInt(tfnum.getText()) ;
        String nom=tfnom.getText();
        String prenom =tflast.getText();
        String mail =tfmail.getText();
        String mdp=tfmdp.getText();
           
        User u = new User();
        u.setNumtel(Integer.parseInt(tfnum.getText()));
        u.setNom(tfnom.getText());
        u.setPrenom(tflast.getText());
        u.setEmail(tfmail.getText());
        u.setPassword(tfmdp.getText());
        
        

        User user = TableUser.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           UserServices sc=new UserServices();
           sc.modifier2(user.getId(),u);
           afficher();
            //clearfields();
            // sound.playClick();
            // np.notifpush("rendezvous Modifier", "Conge modifier avec succes");
        }

        
        
    }

    @FXML
    private void deleteU(ActionEvent event) {
        ObservableList<User> selectedRows, allPeople;
        allPeople = TableUser.getItems();
        selectedRows = TableUser.getSelectionModel().getSelectedItems();
        User u = TableUser.getSelectionModel().getSelectedItem();
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Comfirmation");
          alert.setHeaderText(null);
          alert.setContentText("Êtes-vous sûr de supprimer ?");
          Optional<ButtonType> action = alert.showAndWait();
          if (action.get() == ButtonType.OK) {
            UserServices rs=new UserServices();
              rs.supprimer(u.getId());
        TableUser.getItems().clear();
            afficher(); 
            clearfields();
            
            //sound.playClick();
            //np.notifpush("Conge Deleted", "Conge Deleted avec sucees");
          }
    }

    @FXML
    private void afficher() {
        UserServices sc=new UserServices();

          ObservableList obeListe = FXCollections.observableList(sc.getAll());
        cnumtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cMail.setCellValueFactory(new PropertyValueFactory<>("email"));


      TableUser.setItems(ob1);
        
        TableUser.setItems(obeListe);
   
    }
   public User us;
    @FXML
    private void selectuser(MouseEvent event) {
        
        us = TableUser.getSelectionModel().getSelectedItem();
        if (us != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
           
            tfnum.setText(String.valueOf(us.getNumtel()));
            tfnom.setText(String.valueOf(us.getNom()));
            tflast.setText(String.valueOf(us.getPrenom()));
            tfmail.setText(String.valueOf(us.getEmail()));
            tfmdp.setText(String.valueOf(us.getPassword()));
    

        
    }
    }

    @FXML
    private void add(ActionEvent event) {
         
       
        String nom=tfnom.getText();
        String prenom =tflast.getText();
        String mail =tfmail.getText();
        String mdp=tfmdp.getText();
        String securityq="";
        String answer="";
           
        UserServices rs=new UserServices();
        
            if(!(rs.isNumeric(tfnum.getText()))) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Verifyer Votre Numero", ButtonType.OK);
           a.showAndWait();
            tfnum.setText("");
          
            tfnum.requestFocus();
           
       }
        
            else if (  tfnum.getText().isEmpty()||tfnom.getText().isEmpty()|| tflast.getText().isEmpty()|| tfmail.getText().isEmpty()||tfmdp.getText().isEmpty())
       {
           Alert a=new Alert(Alert.AlertType.ERROR,"les champs sont vides!", ButtonType.OK);
           a.showAndWait();
       }
        else if(!(rs.isValidPhoneNumber(tfnum.getText()))) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Verifyer Votre Numero", ButtonType.OK);
           a.showAndWait();
            tfnum.setText("");
          
            tfnum.requestFocus();
           
       }else if(!(rs.isValidEmailAddress(mail))) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Email  non Valide", ButtonType.OK);
           a.showAndWait();
            tfmail.setText("");
          
            tfmail.requestFocus();
           
       }
       else if(rs.existeMail2(mail)!=0) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Email Deja Existant", ButtonType.OK);
           a.showAndWait();
            tfnum.setText("");
          
            tfnum.requestFocus();
           
       }
       
   
           else {    
            int numtel = Integer.parseInt(tfnum.getText()) ;
            User u=new User(numtel, nom, prenom, mail, mdp,securityq,answer);
             rs.ajouter2(u);
             afficher();
        
    }
    
    }
    
      private void clearfields() {  
        tfnum.clear();
        tfnom.clear();
        tflast.clear();
        tfmail.clear();
        tfmdp.clear();

    } 
        @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "AdminDashboardScreen.fxml");
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
        System.out.println("----------");
        navigateTo(actionEvent, "ShowUser.fxml");
        
    }
     @FXML
    private void navigateToUsers(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ShowUser.fxml");
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
