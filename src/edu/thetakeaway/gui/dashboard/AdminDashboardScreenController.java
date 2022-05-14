package edu.thetakeaway.gui.dashboard;

import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.entities.Restaurant;
import edu.thetakeaway.services.CarteServices;
import edu.thetakeaway.services.ReservationService;
import edu.thetakeaway.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminDashboardScreenController implements Initializable {

    @FXML
    private Button reservationsBtn;
    @FXML
    private Button reclamationsBtn;
    @FXML
    private Pane panne;
    @FXML
    private Label usersLabel;
    @FXML
    private TextField nbusers;
    @FXML
    private TextField nbusers1;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnadd1;
    @FXML
    private Button btnadd11;
    @FXML
    private Label salesLabel;
    @FXML
    private TextField CarteNumer;
    @FXML
    private Label salesLabel1;
    @FXML
    private TextField AdmineNumer;

    public void CountUsers() {
        try {
            UserServices us = new UserServices();
            int i = 0;

            i = us.NumberUsers();
            String s = String.valueOf(i);
            nbusers.setText(s);
        } catch (SQLException ex) {
        }
    }

    public void CountAdmine() {
        try {
            UserServices us = new UserServices();
            int i = 0;

            i = us.NumberAmin();
            String s = String.valueOf(i);
            AdmineNumer.setText(s);
        } catch (SQLException ex) {
        }
    }

    public void CountCartes() {
        try {
            CarteServices us = new CarteServices();
            int i = 0;

            i = us.NumberCartes();
            String s = String.valueOf(i);
            CarteNumer.setText(s);
        } catch (SQLException ex) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CountAdmine();
        CountCartes();
        CountUsers();
        PieChart piechart = new PieChart();
        try {
            piechart.setData(getChartData());
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setLegendSide(Side.BOTTOM);

        piechart.setTitle("Les status des reservations");
        piechart.setClockwise(false);
        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        double total = 0;
                        for (PieChart.Data d : piechart.getData()) {
                            total += d.getPieValue();
                        }
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        String text = String.format("%.1f%%", 100 * data.getPieValue() / total);
                        caption.setText(text);
                    }
            );
        }

        //BAR Chart
        ReservationService rs = new ReservationService();
        HashMap<String, Integer> restoRev = new HashMap<String, Integer>();
        for (Reservation key : rs.getAll()) {
            if (restoRev.containsKey(key.getRestaurant().getNom()))//will check if a particular key exist or not 
            {
                restoRev.put(key.getRestaurant().getNom(), restoRev.get(key.getRestaurant().getNom()) + 1);
            } else {
                restoRev.put(key.getRestaurant().getNom(), 1);// make a new entry into the hashmap
            }
        }
        System.out.println(restoRev);

        //Configuring category and NumberAxis   
        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(1, 28, 1);
        xaxis.setLabel("Restaurants");
        yaxis.setLabel("Reservations");

        //Configuring BarChart   
        BarChart<String, Integer> bar = new BarChart(xaxis, yaxis);
        bar.setTitle("Les reservations par restaurant");

        //Configuring Series for XY chart   
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (String s : restoRev.keySet()) {
            series.getData().add(new XYChart.Data(s, restoRev.get(s)));
        }

        //Adding series to the barchart   
        bar.getData().add(series);
        ;
        HBox hb = new HBox();
        piechart.setMaxWidth(400);
        hb.getChildren().add(piechart);
        hb.getChildren().add(bar);
        panne.getChildren().add(hb);

    }

    //The method sets the data to the pie-chart.
    private ObservableList<PieChart.Data> getChartData() throws SQLException {
        ReservationService rs = new ReservationService();

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        int a = 0;
        int r = 0;
        int w = 0;
        int c = 0;
        for (Reservation rv : rs.getAll()) {
            switch (rv.getStatut()) {
                case "Accepté":
                    a++;
                    break;
                case "Réfusé":
                    r++;
                    break;
                case "En Attente":
                    w++;
                    break;
                default:
                    c++;
                    break;
            }
        }
        list.addAll(
                new PieChart.Data("Accepté", a),
                new PieChart.Data("Réfusé", r),
                new PieChart.Data("En Attente", w),
                new PieChart.Data("Annulé", c)
        );

        return list;

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
