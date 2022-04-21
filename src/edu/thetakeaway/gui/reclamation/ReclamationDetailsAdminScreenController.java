
package edu.thetakeaway.gui.reclamation;

import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.entities.Reponse;
import edu.thetakeaway.services.ReclamationService;
import edu.thetakeaway.services.ReponseService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ReclamationDetailsAdminScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private Label clientLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label sujetLabel;
    @FXML
    private Label statutLabel;
    @FXML
    private Text contenuText;
    @FXML
    private ScrollPane reponsesPane;
    @FXML
    private Button repondreBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private Button reopenBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reclamation rec = SharedData.selectedReclamation;
        dateLabel.setText(rec.getDate()+" - "+rec.getHeure());
        sujetLabel.setText(rec.getSujet());
        clientLabel.setText(rec.getUser().getNom());
        statutLabel.setText(rec.getStatut());
        contenuText.setText(rec.getContenu());
        loadReponsesInPane();
        if(rec.getStatut().equals("Fermé")){
            repondreBtn.setDisable(true);
            reopenBtn.setDisable(false);
            closeBtn.setDisable(true);
        }else{
            repondreBtn.setDisable(false);
            reopenBtn.setDisable(true);
            closeBtn.setDisable(false);
        }
    }


    private void loadReponsesInPane(){
        Reclamation rec = SharedData.selectedReclamation;
        ReponseService rps = new ReponseService();
        List<Reponse> reponses = rps.getReponsesByReclamatioId(rec);
        VBox  p = new VBox ();
        p.setSpacing(10);
        p.setPadding(new Insets(10));
        for(Reponse r : reponses){
            VBox vb = new VBox();
            HBox hb = new HBox();
            Label author = new Label(r.getAuthor().getId()==SharedData.currentUser.getId()?" Client ":"Admin");
            author.setMinWidth(80);
            author.setStyle(
                    r.getAuthor().getId()==SharedData.currentUser.getId()? 
                            "-fx-background-color: #134F90;-fx-text-fill :#ffffff;-fx-padding:5 5;-fx-font-size:22; "
                            :"-fx-background-color: #FF1E90;-fx-text-fill :#ffffff; -fx-padding:5 5;-fx-font-size:22;");
            Label date = new Label(r.getDate()+" "+r.getHeure());
            date.setStyle("-fx-font-size:14;-fx-font-style:italic;-fx-text-fill :#554433;");
            Label content = new Label(r.getContenu());
            content.setStyle("-fx-font-size:18;-fx-font-style:bold;");
            vb.getChildren().addAll(date,content);
            hb.getChildren().addAll(author,vb);
            hb.setSpacing(50);
            p.getChildren().add(hb);
        }
        reponsesPane.setPannable(true);
        reponsesPane.setContent(p);
    }
    
    @FXML
    private void reopen(ActionEvent actionEvent){
        SharedData.selectedReclamation.setStatut("Ouvert");
        ReclamationService rs = new ReclamationService();
        rs.modifier(SharedData.selectedReclamation);
        navigateReclamationDetails(actionEvent);
    }
    @FXML
    private void close(ActionEvent actionEvent){
        SharedData.selectedReclamation.setStatut("Fermé");
        ReclamationService rs = new ReclamationService();
        rs.modifier(SharedData.selectedReclamation);
        navigateReclamationDetails(actionEvent);
    }
    @FXML
    private void delete(ActionEvent actionEvent){
        ReclamationService rs = new ReclamationService();
        rs.supprimer(SharedData.selectedReclamation);
        navigateReclamations(actionEvent);
    }



    //Navigation
    @FXML
    private void navigateReclamationDetails(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReclamationDetailsAdminScreen.fxml");
    }
    @FXML
    private void navigateReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReclamationsAdminScreen.fxml");
    }
    @FXML
    private void navigateNewReponse(ActionEvent actionEvent) {
        navigateTo(actionEvent, "NewReponseAdminScreen.fxml");
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
