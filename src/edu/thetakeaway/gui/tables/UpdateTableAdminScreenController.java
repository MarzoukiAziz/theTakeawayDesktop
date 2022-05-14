/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.tables;

import edu.thetakeaway.entities.Table;
import edu.thetakeaway.services.TableService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class UpdateTableAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private TextField numtf;
    @FXML
    private TextField xtf;
    @FXML
    private TextField ytf;
    @FXML
    private TextField nbtf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numtf.setText(SharedData.selectedTable.getNumero()+"");
        xtf.setText(SharedData.selectedTable.getPosX()+"");
        ytf.setText(SharedData.selectedTable.getPosY()+"");
        nbtf.setText(SharedData.selectedTable.getNbPlaces()+"");
    }    

    @FXML
    void updateTable(ActionEvent actionEvent) {
        if (numtf.getText().isEmpty() || xtf.getText().isEmpty() || ytf.getText().isEmpty() || nbtf.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Tous les champs doivent être non vide!", ButtonType.APPLY.OK);
            a.setHeaderText("Champ Vide");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            int num,x,y,nb;
            try {
                 num = Integer.parseInt(numtf.getText());
                 x = Integer.parseInt(xtf.getText());
                 y = Integer.parseInt(ytf.getText());
                 nb = Integer.parseInt(nbtf.getText());
            } catch (NumberFormatException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR,"Tous les champs doivent être numériques!" , ButtonType.APPLY.OK);
                a.setHeaderText("Type Invalide");
                a.setTitle("Error");
                a.showAndWait();
                return;
            }
            if(num<1 || num>50 || SharedData.tablesNumber.contains(num)){
                
                Alert a = new Alert(Alert.AlertType.ERROR,"Le numéro doit être unique et entre 1 et 50" , ButtonType.APPLY.OK);
                a.setHeaderText("Numéro Invalide");
                a.setTitle("Error");
                a.showAndWait();
            }else if(x<0 ||x>700){
                Alert a = new Alert(Alert.AlertType.ERROR,"La Position Horizontale doit être  entre 0 et 700" , ButtonType.APPLY.OK);
                a.setHeaderText("Position Horizontale Invalide");
                a.setTitle("Error");
                a.showAndWait();
            }else if(y<0 || y>450){
                Alert a = new Alert(Alert.AlertType.ERROR, "La Position Verticale doit être  entre 0 et 450", ButtonType.APPLY.OK);
                a.setHeaderText("Position Verticale Invalide");
                a.setTitle("Error");
                a.showAndWait();
            }else if(nb<1 || nb>20){
                Alert a = new Alert(Alert.AlertType.ERROR,"Le Nombre des places doit être  entre 1 et 20" , ButtonType.APPLY.OK);
                a.setHeaderText("Nombre des places Invalide");
                a.setTitle("Error");
                a.showAndWait();
            }else{
                TableService ts = new TableService();
                Table t= SharedData.selectedTable;
                t.setNumero(num);
                t.setPosX(x);
                t.setPosY(y);
                t.setNbPlaces(nb);
                ts.modifier(t);
                navigateToTables(actionEvent);
            }
        }
    }

    @FXML
    void resetTextFields() {
        numtf.setText("");
        xtf.setText("");
        ytf.setText("");
        nbtf.setText("");
    }

    //Navigation
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
