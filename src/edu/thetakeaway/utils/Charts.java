package edu.thetakeaway.utils;

import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.services.ReservationService;
import javax.swing.JFrame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * @author tfifha youssef
 */
public class Charts extends JFrame {
    Stage dialogStage = new Stage ();
    Scene scene;
    public Charts(String appTitle,String chartTitle) throws Exception
    {

        PieChart piechart = new PieChart();
        piechart.setData(getChartData());
        piechart.setLegendSide(Side.LEFT);
        piechart.setTitle("Status of Orders");
        piechart.setClockwise(false);
        StackPane root = new StackPane();
        root.getChildren().add(piechart);
        Scene scene = new Scene(root,800,600);
        dialogStage.setScene(scene);
        dialogStage.setTitle("PieChart :Status of Orders");
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
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
                        String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                        caption.setText(text);
                    }
            );
        }
        dialogStage.show();


    }
    //The method sets the data to the pie-chart.
    private ObservableList<PieChart.Data> getChartData() throws SQLException {
        ReservationService rs =new ReservationService ();

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        int a=0;
        int r=0;
        int w=0;
        int c=0;
        for(Reservation rv :rs.getAll()){
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
}



