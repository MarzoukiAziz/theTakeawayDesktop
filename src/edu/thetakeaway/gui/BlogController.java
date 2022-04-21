/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui;

import edu.thetakeaway.entities.Blog_client;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.ServiceBlog_client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class BlogController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfContenu;
    @FXML
    private TextField tfStatut;
    @FXML
    private TextField tfImage;
    @FXML
    private TextField tfDate;
    @FXML
    private Button ajouterbtn;
     ObservableList<Blog_client> ob1 = FXCollections.observableArrayList();
    ObservableList<Blog_client> oblist1 = FXCollections.observableArrayList();
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private TableView<Blog_client> TableBlog;
    private TableColumn< Blog_client, String> cAuthor_id;
    @FXML
    private TableColumn<Blog_client,String> cTitle;
    @FXML
    private TableColumn<Blog_client, String> cContenu;
    @FXML
    private TableColumn<Blog_client, String> cStatut;
    @FXML
    private TableColumn<Blog_client, String> cImage;
    @FXML
    private TableColumn<Blog_client, String> cDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          afficher();
        // TODO
    }    
    
    ServiceBlog_client sc = new ServiceBlog_client();
    User u =new User(2);
    private void AjouterBlog(ActionEvent event) {
        String title =tfTitle.getText();
        String contenu=tfContenu.getText();
        String statut= tfStatut.getText();
        String image=tfImage.getText();
          String date=tfDate.getText();
        
       Blog_client c = new Blog_client(title,contenu,statut, image,date,u);
        ServiceBlog_client sc = new ServiceBlog_client();
        sc.ajouter(c);
        afficher();

//       // FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
//        
//        try {
//           // Parent root = loader.load();
//             //DetailsWindowController dwc = loader.getController();
//             dwc.settextNumtel(""+c.getNumtel());
//             dwc.settextNom(c.getNom());
//             dwc.settextEmail(c.getEmail());
//             
//             tfNumtel.getScene().setRoot(root);
//        } catch (IOException ex) {
//System.out.println("Error: "+ex.getMessage());
//        }
    }
    private void afficher(){
        
        ServiceBlog_client sc=new ServiceBlog_client();

            ObservableList obeListe = FXCollections.observableList(sc.getAll());
          
        cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        cContenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        cStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
         cImage.setCellValueFactory(new PropertyValueFactory<>("image"));
          cDate.setCellValueFactory(new PropertyValueFactory<>("date"));
      

      TableBlog.setItems(ob1);
        
        TableBlog.setItems(obeListe);
        
   
    }


 
       

    }

