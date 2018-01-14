package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class Controleur_PageAccueil implements Initializable {
	private static final int NB_CHAR_MAX_PSEUDO = 50;
	@FXML
    private JFXTextField champPseudo;
	@FXML
    private JFXButton boutonSeConnecter;
	@FXML
    private JFXButton boutonCredits;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//...
		//System.out.println("Chargé");
		
		//ECOUTEUR SUR LE BOUTON POUR LE DESACTIVER SI CHAMP PSEUDO VIDE
		champPseudo.textProperty().addListener((observable, oldValue, newValue) -> {
			boutonSeConnecter.setDisable(newValue.trim().isEmpty());
			
			//LIMITE LE NOMBRE DE CARACTERES DU TEXT FIELD PSEUDO
			if(newValue.length() >= Controleur_PageAccueil.NB_CHAR_MAX_PSEUDO + 1)
				champPseudo.setText(oldValue); //On efface le caractère
		});
	}

    @FXML
    void seConnecter(ActionEvent event) {
    	System.out.println("CONNECTE !");
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information Dialog");
    	alert.setHeaderText("Look, an Information Dialog");
    	alert.setContentText("I have a great message for you!");

    	alert.showAndWait();
    }
    
    @FXML
    void afficherCredits(ActionEvent event) {
    	System.out.println("Afficher crédits !");
    }
}


















