package edu.thetakeaway.gui.promotions;

import edu.thetakeaway.entities.Promotion;
import edu.thetakeaway.services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class PromotionsUserScreenController implements Initializable {

    @FXML
    private ScrollPane pane;
    @FXML
    private ComboBox<String> filterCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterCB.getItems().addAll("", "À venir", "Courante", "Expirés");
        filterCB.valueProperty().addListener((obs, oldVal, newVal)
                -> loadPromotions(newVal));
        loadPromotions("");
    }

    private void loadPromotions(String filter) {
        pane.setContent(null);
        pane.setStyle("-fx-background:#131313;-fx-padding:30;");
        PromotionService rs = new PromotionService();
        ArrayList<Promotion> promos = rs.getAll();
        promos = promos
                .stream()
                .filter(new Predicate<Promotion>() {
                    @Override
                    public boolean test(Promotion r) {
                        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                        if ("".equals(filter)) {
                            return true;
                        } else if ("À venir".equals(filter)) {
                            if (r.getDateDebut().after(now)) {
                                return true;
                            }
                        } else if ("Expirés".equals(filter)) {
                            if (r.getDateFin().before(now)) {
                                return true;
                            }
                        } else if ("Courante".equals(filter)) {
                            System.out.println(r.getDateDebut());
                            System.out.println(now);
                            if (r.getDateDebut().before(now) && r.getDateFin().after(now)) {
                                return true;
                            }
                        }
                        return false;
                    }
                })
                .collect(Collectors.toCollection(ArrayList::new));
        VBox vv = new VBox();
        vv.setSpacing(20);
        for (Promotion r : promos) {
            VBox vb = new VBox();
            vb.setSpacing(5);
            HBox hb = new HBox();
            hb.setSpacing(120);
            hb.setMinWidth(800);
            hb.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
            hb.setPadding(new Insets(15, 12, 15, 32));
            hb.setStyle("-fx-border-radius: 15px; -fx-border-width: 2px; -fx-border-color: white; ");
            Label rname = new Label(r.getElement().getNom() + " En Promo");
            rname.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
            Label raddress = new Label(r.getDateDebut() + " - " + r.getDateFin());
            raddress.setStyle("-fx-text-fill :#ffffff;-fx-padding:0 0 24 0;");
            Label prixLabel = new Label(r.getPrixPromo() + " Dt");
            prixLabel.setStyle("-fx-border-radius: 15px; -fx-border-width: 2px; -fx-border-color: white;-fx-text-fill :#dc143c;-fx-padding:5 15;-fx-font-size:25;-fx-font-weight: bold; ");
            ImageView rimg = new ImageView("/upload/" + r.getBanner());
            rimg.setFitHeight(200);
            rimg.setFitWidth(200);
            rimg.setClip(null);
            // apply a shadow effect.
            rimg.setEffect(new DropShadow(20, Color.ALICEBLUE));
            // store the rounded image in the imageView.
            vb.getChildren().addAll(rname, raddress, prixLabel);
            hb.getChildren().addAll(rimg, vb);
            vv.getChildren().add(hb);
        };
        pane.setContent(vv);
    }

    private void navigateToRestaurantDetails(ActionEvent actionEvent) {
        navigateTo(actionEvent, "RestaurantDetailsUserScreen.fxml");
    }
        @FXML
    private void navigateToRestaurants(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../restaurant/RestaurantsUserScreen.fxml");
    }
    
    @FXML
    private void navigateToMenu(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../menu/MenuUserScreen.fxml");
    }
    
    @FXML
    private void navigateToPromotions(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../promotions/PromotionsUserScreen.fxml");
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
    
    @FXML
    private void navigateToReclamations(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../reclamation/ReclamationsScreen.fxml");
    }
    
    @FXML
    private void navigateToRCommandes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../commande/CommandeUserScreen.fxml");
    }
    
    @FXML
    private void navigateToBlog(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../blog/blogList.fxml");
    }
    
    @FXML
    private void navigateToCartes(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../user/CarteShow.fxml");
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
