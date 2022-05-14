package edu.thetakeaway.gui.user;

import com.sun.security.ntlm.Client;
import edu.thetakeaway.entities.Carte;
import edu.thetakeaway.entities.User;
import edu.thetakeaway.services.CarteServices;
import edu.thetakeaway.services.UserServices;
import edu.thetakeaway.utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CarteShowController implements Initializable {

    @FXML
    private ChoiceBox<String> Years;
    private String[] choix = {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};

    @FXML
    private ChoiceBox<String> Monthes;
    private String[] choix2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    @FXML
    private Button btnupdate;
    @FXML
    private Button btnadd;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflast;
    @FXML
    private TextField tfmail;
    @FXML
    private TableView<Carte> TableUser;
    @FXML
    private TableColumn<Carte, Integer> cnumtel;
    @FXML
    private TableColumn<Carte, String> cNom;
    @FXML
    private TableColumn<Carte, String> cDate;
    @FXML
    private TableColumn<Carte, Integer> cvv;

    ObservableList<Carte> oblist1 = FXCollections.observableArrayList();

    ObservableList<Carte> ob1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        Years.getItems().addAll(choix);
        Monthes.getItems().addAll(choix2);  

    }

    @FXML
    private void add(ActionEvent event) {

        String nom = tfnom.getText();

        String datexp = Monthes.getValue() + " / " + Years.getValue();
        String m1 = Monthes.getValue();
        String m2 = Years.getValue();
        int numtel = Integer.parseInt(tfnum.getText());

        CarteServices rs = new CarteServices();
        UserServices es = new UserServices();

        if (!(es.isNumeric(tfnum.getText()))) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Numero", ButtonType.OK);
            a.showAndWait();
            tfnum.setText("");

            tfnum.requestFocus();

        } else if (es.isValidString(nom) == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre nom", ButtonType.OK);
            a.showAndWait();
            tfnom.setText("");

            tfnom.requestFocus();
        } else if (rs.existeNumm(numtel) != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Numero Deja existe", ButtonType.OK);
            a.showAndWait();
            tfnum.setText("");

            tfnum.requestFocus();
        } else if (tfnum.getText().isEmpty() || tfnom.getText().isEmpty() || tfmail.getText().isEmpty() || m1.isEmpty() || m2.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } else if (!(es.isNumeric(tfmail.getText()))) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Numero", ButtonType.OK);
            a.showAndWait();
            tfmail.setText("");

            tfmail.requestFocus();

        } else if (!(rs.isValidCvv(tfmail.getText()))) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifyer Votre Cvv", ButtonType.OK);
            a.showAndWait();
            tfmail.setText("");

            tfmail.requestFocus();

        } else {

            int cvv = Integer.parseInt(tfmail.getText());
            SharedData da = new SharedData();
            User cc = new User();
            UserServices ss = new UserServices();
            cc = ss.getUserByIda(da.CurrentUserId);
            Carte u = new Carte(numtel, cvv, nom, datexp, cc);
            System.out.println("heloooooooo");
            rs.ajouter(u);
            afficher();

        }

    }

    @FXML
    private void update(ActionEvent event) {

        int numtel = Integer.parseInt(tfnum.getText());
        String nom = tfnom.getText();

        String datexp = Monthes.getValue() + " / " + Years.getValue();
        int cvv = Integer.parseInt(tfmail.getText());
        if (Monthes.getValue().isEmpty() || Years.getValue().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "les champs sont vides!", ButtonType.OK);
            a.showAndWait();
        } else {

            SharedData ds = new SharedData();
            UserServices ss = new UserServices();
            User l1 = new User();

            l1 = ss.getById(ds.CurrentUserId);
            System.out.println(l1);

            Carte u = new Carte();
            u.setNumero(numtel);
            u.setNom(nom);
            u.setDatexp(datexp);
            u.setCvv(cvv);

            Carte user = TableUser.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Comfirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de modifier ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                CarteServices sc = new CarteServices();
                System.out.println("===================");
                System.out.println(user);
                int idd = l1.getId();
                System.out.println("===" + idd);
                Carte c2 = new Carte();

                c2 = sc.getCarteByid(u.getNumero());
                int isd = c2.getId();
                System.out.println("===" + isd);
                System.out.println(u);
                sc.modifiere(u, isd);
                afficher();
                //clearfields();
                // sound.playClick();
                // np.notifpush("rendezvous Modifier", "Conge modifier avec succes");
            }
        }
    }

    @FXML
    private void deleteU(ActionEvent event) {
        ObservableList<Carte> selectedRows, allPeople;
        allPeople = TableUser.getItems();
        selectedRows = TableUser.getSelectionModel().getSelectedItems();
        Carte u = TableUser.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            CarteServices rs = new CarteServices();
            rs.supprimer(u.getId());
            TableUser.getItems().clear();
            afficher();
            clearfields();

            //sound.playClick();
            //np.notifpush("Conge Deleted", "Conge Deleted avec sucees");
        }
    }

    @FXML
    private void afficher() {
        CarteServices sc = new CarteServices();
        SharedData cs = new SharedData();

        ObservableList obeListe = FXCollections.observableList(sc.getAllbyid(cs.CurrentUserId));

        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("datexp"));
        cvv.setCellValueFactory(new PropertyValueFactory<>("cvv"));
        cnumtel.setCellValueFactory(new PropertyValueFactory<>("Numero"));

        TableUser.setItems(ob1);

        TableUser.setItems(obeListe);

    }
    public Carte us;

    @FXML
    private void selectuser(MouseEvent event) {

        us = TableUser.getSelectionModel().getSelectedItem();
        if (us != null) {

            //iduu.setText(String.valueOf(cc.getIde_user()));
            tfnum.setText(String.valueOf(us.getNumero()));
            tfnom.setText(String.valueOf(us.getNom()));
            tflast.setText(String.valueOf(us.getDatexp()));
            tfmail.setText(String.valueOf(us.getCvv()));

        } else {
            System.out.println("heeeeeee");
        }
    }

    @FXML
    private void navigateToDashboard(ActionEvent actionEvent) {
        navigateTo(actionEvent, "UserDashboardScreen.fxml");
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

    private void clearfields() {
        tfnum.clear();
        tfnom.clear();
        tflast.clear();
        tfmail.clear();

    }
}
