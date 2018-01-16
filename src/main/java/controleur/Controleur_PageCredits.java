package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */



public class Controleur_PageCredits implements Initializable {
	@FXML
	private JFXButton boutonRetourAccueil;
	
	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static final String CHEMIN_SON_RETOUR = "file:/" + Main.CHEMIN_SON + "/retour.wav";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
	}
	
	@FXML
	void afficherAccueil(ActionEvent event) {
		//System.out.println("Affichage accueil");
		try {
			mediaSonRetour = new Media(Controleur_PageCredits.CHEMIN_SON_RETOUR);
			mediaPlayerSonRetour = new MediaPlayer(mediaSonRetour);
			mediaPlayerSonRetour.setCycleCount(1);
			mediaPlayerSonRetour.play();
			Parent root = FXMLLoader.load(getClass().getResource(Main.CHEMIN_FXML_PAGE_ACCUEIL));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
	        scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
	        Main.windowStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


















