/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc.gui;

import edu.workshopjdbc.services.ServiceFacture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Synda
 */
public class StatisticController implements Initializable {

    @FXML
    private BarChart<?, ?> barchart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    XYChart.Series set1= new XYChart.Series<>();
         ServiceFacture S = new ServiceFacture();
     
            set1.getData().add(new XYChart.Data("afif",S.Statistiquee().get(0)));
            set1.getData().add(new XYChart.Data("sinda SOCIETE",S.Statistiquee().get(1)));
            set1.getData().add(new XYChart.Data("SAGAP SARL",S.Statistiquee().get(2)));
             
      
    barchart.getData().setAll(set1);
    
    }    
    
}
