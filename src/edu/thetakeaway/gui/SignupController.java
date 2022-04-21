/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.thetakeaway.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.UserServices;
import edu.thetakeaway.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class SignupController {

    
    @FXML
    private TextField UsernameTF;
    
    
    @FXML
    private TextField EmailTF;
    @FXML
    private TextField PasswordTF;
    
    @FXML
    private TextField EmailLogin;
    
    @FXML
    private TextField PassLogin;


    /**
     * Initializes the controller class.
     */
       Connection cnx = DataSource.getInstance().getCnx();
       UserServices su = new UserServices();
    @FXML
    private Label ErrorLabel;


    @FXML
    public  void AjoutUser(ActionEvent event) throws SQLException, IOException {
        
      if(UsernameTF.getText()=="")
      {
          ErrorLabel.setText("Le username est Obligatoire");
      
      }else if (EmailTF.getText()=="")
      {
          ErrorLabel.setText("L'email est obligatoire! ");
      }else if (PasswordTF.getText()=="")
      {
          ErrorLabel.setText("Le password est obligatoire! ");
      }
      else {
          User utilisateur = new User();
            UserServices nu = new UserServices();
            utilisateur.setNom(UsernameTF.getText());
                utilisateur.setEmail(EmailTF.getText());
                utilisateur.setPassword(PasswordTF.getText());
                utilisateur.setRoles("[\"ROLE_USER\"]");
               
                
                if(nu.signup(utilisateur)==0){
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Signup.fxml"));
           Parent root = (Parent) loader.load();
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setUserData(utilisateur);
           // SignUp2Controller signUp2Controller=loader.getController();
           //   signUp2Controller.returnUser(LoginTextField1.getText());
           //  signUp2Controller.show(LoginTextField1.getText());
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
                }
                   else if (nu.signup(utilisateur)==1)
       {
           ErrorLabel.setText("L'adresse email deja existe");
       }else if (nu.signup(utilisateur)==2)
       {
           ErrorLabel.setText("Le login est deja utilisé \n Login Disponible :"+nu.Login_Dispo(utilisateur)+"  !");

       }
    
            
      }
    }

@FXML
    public  void LoginUser(ActionEvent event) throws SQLException, IOException {
        
      if(EmailLogin.getText()=="")
      {
          ErrorLabel.setText("Le Email est Obligatoire");
      
      }else if (PassLogin.getText()=="")
      {
          ErrorLabel.setText("L'Motdepass est obligatoire! ");
      }
      else {
          User utilisateur = new User();
            UserServices nu = new UserServices();
           
                utilisateur.setEmail(EmailLogin.getText());
                utilisateur.setPassword(PassLogin.getText());
                String Email=utilisateur.getEmail();
                String Pass=utilisateur.getPassword();
                
                
               
                
                if((nu.existeMail(utilisateur)==0)&&(nu.verifPassword(Email,Pass)==true)){
                       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Signup.fxml"));
           Parent root = (Parent) loader.load();
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setUserData(utilisateur);
           // SignUp2Controller signUp2Controller=loader.getController();
           //   signUp2Controller.returnUser(LoginTextField1.getText());
           //  signUp2Controller.show(LoginTextField1.getText());
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
                }
                   else if (nu.existeMail(utilisateur)!=0)
       {
           ErrorLabel.setText("L'adresse email ne existe pas");
       }else if (nu.verifPassword(Email,Pass)==false)
       {
           ErrorLabel.setText("le Mot de pass est faux!");

       }
    
            
      }
    }
    
    
}
