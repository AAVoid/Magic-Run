package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Controleur_PageJeu implements Initializable {
	public static final String CHEMIN_FXML_PAGE_JEU = "/vue/pageJeu.fxml";
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Dark_Souls_III_Soundtrack_OST_-_Iudex_Gundyr.mp3";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
	}
}






















