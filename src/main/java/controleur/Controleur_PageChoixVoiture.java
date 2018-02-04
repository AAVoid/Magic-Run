package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */



public class Controleur_PageChoixVoiture implements Initializable {
	@FXML
	private JFXButton boutonSeDeconnecter;
	@FXML
	private Label nombreJoueurs;
	@FXML
	private Label pseudoJoueur;
	@FXML
	private JFXButton boutonPoursuivre;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Chargé");
		//Affichage du pseudo du joueur
		//Affichage du nombre de joueurs connectés
	}
	
	@FXML
    void seDeconnecter(ActionEvent event) {
		System.out.println("Deconnection !!!");
    }

    @FXML
    void choisirTerrain(ActionEvent event) {
    	System.out.println("Choix du terrain !!!");
    }
}





































