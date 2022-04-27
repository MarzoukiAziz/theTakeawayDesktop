/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import entities.Blog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.BlogService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BlogListBackController implements Initializable {

    @FXML
    private TableView<Blog> tv;
    @FXML
    private TableColumn<Blog, String> title;
    @FXML
    private TableColumn<Blog, String> contenu;
    @FXML
    private TableColumn<Blog, String> date;
    @FXML
    private TableColumn<Blog, String> statut;
    @FXML
    private TextField search;
    @FXML
    private AnchorPane ap;

        BlogService bs = new BlogService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tv.setItems((ObservableList<Blog>) bs.afficher());
    }    

    @FXML
    private void doubleClick(MouseEvent event) throws IOException {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                Blog b = tv.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/blogShowBack.fxml"));
                Parent root = loader.load();
                BlogShowBackController controller = (BlogShowBackController) loader.getController();
                controller.setBlog(b);
                ap.getChildren().setAll(root);
            }
        }
    }

    @FXML
    private void search(KeyEvent event) {
        tv.setItems((ObservableList<Blog>) bs.search(search.getText()));
    }
    
}
