/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import edu.workshopjdbc.entities.Facture;
import edu.workshopjdbc.services.ServiceFacture;
import edu.workshopjdbc.utils.SharedData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import edu.workshopjdbc.entities.pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
