package edu.thetakeaway.gui.restaurant;

import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.utils.SharedData;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.text.IconView;

/**
 * FXML Controller class
 *
 * @author marzo
 */
public class RestaurantsUserScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private GridPane grid;
    @FXML
    private TextField searchTF;
    @FXML
    private ImageView searchBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadRestaurants("");
        searchBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                loadRestaurants(searchTF.getText());
                event.consume();
            }
        });
    }

    private void loadRestaurants(String searchTerm) {
        grid.getChildren().clear();
        RestaurantService rs = new RestaurantService();
        ArrayList<Restaurant> resto = rs.getAll();
        grid.setHgap(10);
        grid.setVgap(10);
        int x = 0, y = 0;
        resto = resto
                .stream()
                .filter(r -> r.getNom().toUpperCase().contains(searchTerm.toUpperCase()) || r.getAdresse().toUpperCase().contains(searchTerm.toUpperCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        for (Restaurant r : resto) {
            VBox vb = new VBox();
            vb.setSpacing(5);

            HBox hb = new HBox();
            hb.setSpacing(50);
            hb.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, null)));
            hb.setPadding(new Insets(15, 12, 15, 32));
            hb.setStyle("-fx-border-radius: 15px; -fx-border-width: 2px; -fx-border-color: white; ");
            Label rname = new Label(r.getNom());
            rname.setStyle("-fx-text-fill :#ffffff;-fx-font-size:20; -fx-font-weight: bold;");
            Label raddress = new Label(r.getAdresse());
            raddress.setStyle("-fx-text-fill :#ffffff;-fx-padding:0 0 24 0;");
            Button btn = new Button("Consulter");
            btn.setOnAction(event -> {
                    SharedData.selectedRestaurant = r;
                    navigateToRestaurantDetails(event);
                });
            btn.setStyle("-fx-background-color: #134F90;-fx-text-fill :#ffffff;-fx-padding:5 15;-fx-font-size:17; ");
            ImageView rimg = new ImageView("/upload/" + r.getImage());
            rimg.setFitHeight(150);
            rimg.setFitWidth(150);
            rimg.setClip(null);
            // apply a shadow effect.
            rimg.setEffect(new DropShadow(20, Color.ALICEBLUE));
            // store the rounded image in the imageView.
            vb.getChildren().addAll(rname, raddress, btn);
            hb.getChildren().addAll(rimg, vb);

            grid.add(hb, x % 2, y / 2);
            x++;
            y++;
        };
    }
    
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/UserDashboardScreen.fxml");
    }
    
    @FXML
    private void navigateToRestaurantDetails(ActionEvent actionEvent){
        navigateTo(actionEvent,"RestaurantDetailsUserScreen.fxml");
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
