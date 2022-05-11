package edu.thetakeaway.gui.menu;

import com.jfoenix.controls.JFXScrollPane;
import edu.thetakeaway.entities.Menu;
import edu.thetakeaway.services.MenuService;
import edu.thetakeaway.utils.SharedData;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuUserScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private ScrollPane grid;
    @FXML
    private ComboBox<String> categorieCB;
    @FXML
    private Button panier;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grid.setStyle("-fx-background:#414a4c;-fx-padding:30;");
        categorieCB.getItems().addAll("MEALS", "SANDWICHES", "BOXES", "SIDES");
        categorieCB.valueProperty().addListener((obs, oldVal, newVal)
                -> loadMenu(newVal));
        Image imagex = new Image("assets/cloche.png");
        ImageView view = new ImageView(imagex);
        view.setFitHeight(40);
        view.setPreserveRatio(true);
        panier.setGraphic(view);
        panier.setText("Panier (0)");
        panier.setStyle("-fx-border-radius: 10px; -fx-border-width: 2px; -fx-border-color: white;-fx-background-color:#414a4c;");
        loadMenu("");
    }

    private void loadMenu(String categorie) {
        grid.setContent(null);
        MenuService rs = new MenuService();
        ArrayList<Menu> menus = rs.getAll();
        menus = menus
                .stream()
                .filter(r -> r.getCategorie().contains(categorie))
                .collect(Collectors.toCollection(ArrayList::new));
        VBox vmain = new VBox();
        vmain.setSpacing(5);
        for (Menu r : menus) {
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
            Button btn = new Button("Ajouter Au Panier");
            btn.setOnAction(event -> {
                SharedData.panier.add(r);
                panier.setText("Panier ("+SharedData.panier.size()+")");
            });
            btn.setStyle("-fx-background-color: #134F90;-fx-text-fill :#ffffff;-fx-padding:5 15;-fx-font-size:17; ");
            ImageView rimg = new ImageView("/upload/" + r.getImage());
            rimg.setFitHeight(150);
            rimg.setFitWidth(150);
            rimg.setClip(null);
            // apply a shadow effect.
            rimg.setEffect(new DropShadow(20, Color.ALICEBLUE));
            // store the rounded image in the imageView.
            vb.getChildren().addAll(rname, rcat, rdes, rprix, btn);
            hb.getChildren().addAll(rimg, vb);

            vmain.getChildren().add(hb);
        };
        grid.setContent(vmain);
    }
    
    @FXML 
    private void navigateToPanier(ActionEvent event){
        navigateTo(event, "../commande/PanierScreen.fxml");
    }
    
    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "../dashboard/UserDashboardScreen.fxml");
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
