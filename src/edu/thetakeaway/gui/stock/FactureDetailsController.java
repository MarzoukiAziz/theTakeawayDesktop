/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui.stock;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import edu.thetakeaway.entities.Facture;
import edu.thetakeaway.services.ServiceFacture;
import edu.thetakeaway.utils.SharedData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import edu.thetakeaway.entities.pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class FactureDetailsController implements Initializable {

    @FXML
    private Label dateLabel;
    @FXML
    private Label frLabel;
    @FXML
    private Label ingLabel;
    @FXML
    private Text prixText;
    @FXML
    private Button pdf;
    @FXML
    private Label quantite;
    @FXML
    private Label quanlabel;
    @FXML
    private Label prixlabel;
    @FXML
    private Label total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        Facture rec = SharedData.selectedFacture;
        dateLabel.setText(rec.getDate()+" - "+rec.getHeure());
        frLabel.setText(rec.getFournisseur().getNom());
        ingLabel.setText(rec.getIngrediant().getNom()); 
        float a =(rec.getQunatite()) * (rec.getPrix_unitaire());
        quanlabel.setText(String.valueOf(rec.getQunatite()));
        prixlabel.setText(String.valueOf(rec.getPrix_unitaire()));
        
        total.setText(String.valueOf(a));
        
   
        // TODO
    }    
        @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        pdf pd=new pdf();
        try{
        pd.GeneratePdf("list of Match");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void navigateStock(ActionEvent event) {
                              navigateTo(event, "GestionFacture.fxml");

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

    private void navigateTo(ActionEvent event, String path) {
  try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource(path));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            second_stage.setScene(ex_section_scene);
            second_stage.show();
        } catch (IOException ex) {

        }    }


    
}
