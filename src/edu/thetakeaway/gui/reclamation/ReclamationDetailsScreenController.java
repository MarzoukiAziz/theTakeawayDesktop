
package edu.thetakeaway.gui.reclamation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import edu.thetakeaway.entities.Reclamation;
import edu.thetakeaway.entities.Reponse;
import edu.thetakeaway.services.ReponseService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReclamationDetailsScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reclamation rec = SharedData.selectedReclamation;
        dateLabel.setText(rec.getDate()+" - "+rec.getHeure());
        sujetLabel.setText(rec.getSujet());
        statutLabel.setText(rec.getStatut());
        contenuText.setText(rec.getContenu());
        loadReponsesInPane();
        if(rec.getStatut().equals("Fermé")){
            repondreBtn.setDisable(true);
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
            Label author = new Label(r.getAuthor().getId()==SharedData.currentUser.getId()?" Vous ":"Admin");
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



    //Navigation
    @FXML
    private void navigateReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReclamationsScreen.fxml");
    }
    @FXML
    private void navigateNewReponse(ActionEvent actionEvent) {
        navigateTo(actionEvent, "NewReponseScreen.fxml");
    }
   @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "ReclamationsScreen.fxml");
    }
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/UserDashboardScreen.fxml");
    }
    @FXML
    private void navigateToReserve(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReserveScreen.fxml");
    }
    @FXML
    private void navigateToReservations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reservations/ReservationsScreen.fxml");
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
