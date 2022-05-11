package edu.thetakeaway.gui.commande;

import edu.thetakeaway.entities.Commande;
import edu.thetakeaway.entities.ElementDetails;
import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.services.CommandeService;
import edu.thetakeaway.services.ElementDetailsService;
import edu.thetakeaway.services.MenuService;
import edu.thetakeaway.utils.QRCodeService;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PanierScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private ScrollPane grid;

    ArrayList<ElementDetails> panier = new ArrayList<ElementDetails>();
    @FXML
    private Label total;
    @FXML
    private ChoiceBox<String> methodChoice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grid.setStyle("-fx-background:#414a4c;-fx-padding:30;");
        methodChoice.setItems(FXCollections.observableArrayList("Cash", "Carte Bancaire"));
        Image imagex = new Image("assets/cloche.png");
        ImageView view = new ImageView(imagex);
        view.setFitHeight(40);
        view.setPreserveRatio(true);
        calculePrixTotal();
        loadPanier();
    }

    private void loadPanier() {
        grid.setContent(null);
        MenuService rs = new MenuService();
        HashSet<Menu> menus = SharedData.panier;
        VBox vmain = new VBox();
        vmain.setSpacing(5);
        for (Menu r : menus) {
            ElementDetails e = new ElementDetails(r, 1);
            panier.add(e);
            VBox vb = new VBox();
            vb.setSpacing(5);

            HBox hb = new HBox();
            hb.setSpacing(50);
            hb.setMaxWidth(800);
            hb.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
            hb.setPadding(new Insets(15, 12, 15, 32));
            hb.setStyle("-fx-border-radius: 15px; -fx-border-width: 2px; -fx-border-color: white; ");
            Label rname = new Label(r.getNom());
            rname.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
            Label rcat = new Label(r.getCategorie());
            rcat.setStyle("-fx-text-fill :#da5ebb;-fx-padding:0 0 24 0;-fx-font-weight: bold;-fx-font-size:22");
            Text rdes = new Text(r.getDescription());
            rdes.setStyle("-fx-text-fill :#ffffff;-fx-padding:0 0 24 0;");
            rdes.setWrappingWidth(400);
            rdes.setFill(Color.WHITE);
            Label rprix = new Label(r.getPrix() + " Dt");
            rprix.setStyle("-fx-text-fill :#ddaa33;-fx-padding:0 0 24 0;-fx-font-weight: bold;-fx-font-size:22");

            HBox quantiteManager = new HBox();

            Button btnMinus = new Button("-");

            btnMinus.setStyle("-fx-background-color: RED;-fx-text-fill :#ffffff;-fx-padding:5 15;-fx-font-size:17; ");

            Label quantite = new Label("1");
            quantite.setStyle("-fx-text-fill :#ffffff;-fx-padding:0 50;-fx-font-weight: bold;-fx-font-size:24");
            btnMinus.setOnAction(event -> {
                if (e.getQuantite() > 0) {
                    e.setQuantite(e.getQuantite() - 1);
                    quantite.setText(e.getQuantite() + "");
                    calculePrixTotal();
                }

            });
            Button btnPlus = new Button("+");
            btnPlus.setOnAction(event -> {
                e.setQuantite(e.getQuantite() + 1);
                quantite.setText(e.getQuantite() + "");
                calculePrixTotal();
            });
            btnPlus.setStyle("-fx-background-color: GREEN;-fx-text-fill :#ffffff;-fx-padding:5 15;-fx-font-size:17; ");
            quantiteManager.getChildren().addAll(btnMinus, quantite, btnPlus);
            ImageView rimg = new ImageView("/upload/" + r.getImage());
            rimg.setFitHeight(150);
            rimg.setFitWidth(150);
            rimg.setClip(null);
            // apply a shadow effect.
            rimg.setEffect(new DropShadow(20, Color.ALICEBLUE));
            // store the rounded image in the imageView.
            vb.getChildren().addAll(rname, rcat, rdes, rprix, quantiteManager);
            hb.getChildren().addAll(rimg, vb);
            vmain.getChildren().add(hb);
        };
        grid.setContent(vmain);
    }

    private void calculePrixTotal() {
        double sum = 0;
        for (ElementDetails e : panier) {
            sum += e.getElement().getPrix() * e.getQuantite();
        }
        total.setText("Total : " + sum + " Dt");
    }

    private double getPrixTotal() {
        double sum = 0;
        for (ElementDetails e : panier) {
            sum += e.getElement().getPrix() * e.getQuantite();
        }
        return sum;
    }

    @FXML
    private void nextStep(ActionEvent e) {
        if (methodChoice.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Methode De Paiement", ButtonType.APPLY.OK);
            a.setHeaderText("Séléctionnez une methode de paiement pour continuer !");
            a.setTitle("Error");
            a.showAndWait();
        } else if (getPrixTotal() == 0) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Panier Vide", ButtonType.APPLY.OK);
            a.setHeaderText("Pas d'élément dans la panier !");
            a.setTitle("Error");
            a.showAndWait();
        } else {
            if (methodChoice.getValue() == "Cash") {
                java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Commande cmd = new Commande(SharedData.selectedRestaurant, SharedData.currentUser, getPrixTotal(), "En attente", now, "Cash");
                CommandeService cmdService = new CommandeService();
                cmdService.add(cmd, panier);
                
                navigateToCommandes(e);
                
            } else if (methodChoice.getValue() == "Carte Bancaire") {
                java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Commande cmd = new Commande(SharedData.selectedRestaurant, SharedData.currentUser, getPrixTotal(), "En attente", now, "Carte Bancaire");
                CommandeService cmdService = new CommandeService();
                cmdService.add(cmd, panier);
                
                navigateToPaiement(e);
            }
        }
    }
    
    @FXML
    private void navigateToPaiement(ActionEvent ac){
        navigateTo(ac, "PaiementScreen.fxml");
    }
        
    @FXML
    private void navigateToCommandes(ActionEvent ac){
        navigateTo(ac, "CommandeUserScreen.fxml");
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
