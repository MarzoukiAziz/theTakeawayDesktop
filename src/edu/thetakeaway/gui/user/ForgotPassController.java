/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.thetakeaway.gui.user;

import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.UserServices;
import edu.thetakeaway.utils.DataSource;
import edu.thetakeaway.utils.Mail;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author rafrafi
 */
public class ForgotPassController implements Initializable {
       @FXML
    private TextField Emaile;
         @FXML
    private TextField Question;
           @FXML
    private TextField Answer;
             @FXML
    private TextField newpass;
             
                @FXML
    private TextField mail;
                   @FXML
    private TextField mailcode;
                      @FXML
    private Button sendmail;
   
    @FXML
    private Button save;
    @FXML
    private Button back;
    @FXML
    private Button Search;  
    @FXML
    private Button inscribtn;
    @FXML
    private Button btnlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage= new Stage();
    stage.setTitle("Login");
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LoginUser.fxml"))));
    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();

    }
      @FXML
    private void sendmail(ActionEvent event) throws IOException {
       
        
        
      Random rand = new Random(); //instance of random class
      int upperbound = 25555;
        //generate random values from 0-24
      int numer = rand.nextInt(upperbound); 
       
     
      
         String mailin=mail.getText();
         Mail m = new Mail();
            String sub="Bienvenue sur notre thetakway";
                 
           String  content="Bonjour Mr/Mme "+". Votre code est = "+numer;
           String finalcontant=content;
           System.out.println(numer);
           m.sendEmail(mailin,finalcontant);
           SharedData da =  new SharedData();
           da.GenratedCode=numer;
           System.out.println(da.GenratedCode);

    }
     @FXML
    private void Search(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

      SharedData da =  new SharedData();
        int numtel = Integer.parseInt(Emaile.getText()) ;
     
       
       
        String Numberr = Emaile.getText();
        
         
        UserServices us = new UserServices();
        User U = new User();
         Connection cnx = DataSource.getInstance().getCnx();
        
        if(Emaile.getText().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR,"les Numero est vides!", ButtonType.OK);
           a.showAndWait();
        }else if(us.isValidPhoneNumber(Numberr)!=true){
           Alert a=new Alert(Alert.AlertType.ERROR,"Verifyer Votre Numero", ButtonType.OK);
           a.showAndWait();
            Emaile.setText("");
            Question.setText("");
          
            Emaile.requestFocus();
       }
       else{
            U=us.getByIda(numtel);
            
        System.out.println(U);
        Question.setText(U.getSecurityq());
        
            
            
        }       
       
        
    } 
         @FXML
      private void openSignup(ActionEvent event) throws IOException {
         
          Stage stage= new Stage();
    stage.setTitle("Inscription");
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("InscriptionUser.fxml"))));
    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }  
      
      
      
      
       @FXML
        private void save(ActionEvent event) throws IOException {
              SharedData da =  new SharedData();
          
        
        int code = Integer.parseInt(mailcode.getText()) ;
        String mailin=mail.getText();
              UserServices us = new UserServices();
      
             int numtel = Integer.parseInt(Emaile.getText()) ;
        String Questions=Question.getText();
        String Answers =Answer.getText();
        String pass =newpass.getText();
        if( Emaile.getText().isEmpty()|| Question.getText().isEmpty()|| Answer.getText().isEmpty()||newpass.getText().isEmpty()){
            Alert a=new Alert(Alert.AlertType.ERROR,"les champs sont vides!", ButtonType.OK);
           a.showAndWait();
        }
        else if (!(us.isValidAnswe(numtel, Answers))){
             Alert a=new Alert(Alert.AlertType.ERROR,"Votre reponse est faux", ButtonType.OK);
           a.showAndWait();
           Answer.setText("");
           
          
            Answer.requestFocus();
            
        }else if (us.existeMail2(mailin)==0){
           Alert a=new Alert(Alert.AlertType.ERROR,"Verifyer Votre Email", ButtonType.OK);
           a.showAndWait();
            mail.setText("");
           
          
            mail.requestFocus();
           
       }else if (code != da.GenratedCode ){
           
            Alert a=new Alert(Alert.AlertType.ERROR,"Verifyer Votre Code de verification", ButtonType.OK);
           a.showAndWait();
            mail.setText("");
           
          
            mail.requestFocus();
       }else if (us.existeMailnum(mailin, numtel)==0){
             Alert a=new Alert(Alert.AlertType.ERROR,"le Email ne corespend pas au numero", ButtonType.OK);
           a.showAndWait();
            mail.setText("");
            Emaile.setText("");
           
          
            Emaile.requestFocus();
            
       }
        else{
           
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de modifier ?");
        Optional<ButtonType> action = alert.showAndWait();
            System.out.println("User Updated");
             User U = new User();
            U=us.getByIda(numtel);
            U.setPassword(pass);
            System.out.println(U.getId());
            System.out.println(U.getPassword());
          us.modifier2(U.getId(),U);
              String tilte = "Resset Password Success ";
            String message = "un message a etes envoyer ";
          TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
          Mail m = new Mail();
          
         String sub="Reset-Password Affected sur notre thetakway";
                 String sub2=" Votre nouveaux Mot de pass est: "+U.getPassword();
           String  content="Bonjour Mr/Mme "+U.getNom()+". Au nom de tous les membres du thetakway, je vous informer que le mot de passe est change avec succes ";
           String finalcontant=sub+content+sub2;
           m.sendEmail(U.getEmail(),finalcontant);
            Stage stage= new Stage();
    stage.setTitle("Login");
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LoginUser.fxml"))));
    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
            
        }
        
    }  
    
}
