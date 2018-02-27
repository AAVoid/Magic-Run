package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import service.UtiliserWS;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Controleur_PageJeu implements Initializable {
	@FXML
	private AnchorPane anchorLayout;
	@FXML
    private Button boutonEcouteurKey;

	public static final String CHEMIN_FXML_PAGE_JEU = "/vue/pageJeu.fxml";
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Dark_Souls_III_Soundtrack_OST_-_Iudex_Gundyr.mp3";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
	}
	
	@FXML
    void getKeyJeu(KeyEvent event) {
		if(event.getCode().getName().equals(Controleur_PageChoixTouches.nomToucheAccelerer)) {
			System.out.println("Avancer");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "avancer", 0);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		else if(event.getCode().getName().equals(Controleur_PageChoixTouches.nomToucheFreiner)) {
			System.out.println("Freiner");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "reculer", 0);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		else if(event.getCode().getName().equals(Controleur_PageChoixTouches.nomToucheTournerGauche)) {
			System.out.println("Gauche");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "gauche", 0);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		else if(event.getCode().getName().equals(Controleur_PageChoixTouches.nomToucheTournerDroite)) {
			System.out.println("Droite");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "droite", 0);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
    }
}






















