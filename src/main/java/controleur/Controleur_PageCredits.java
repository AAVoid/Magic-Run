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
import javafx.scene.control.Label;
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
	@FXML
    private Label dateDeveloppement;
    @FXML
    private Label emailDeveloppeur;
    @FXML
    private Label versionDeveloppement;
    @FXML
    private Label nomDeveloppeur;
    @FXML
    private Label nomJeu;
	
	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static final String CHEMIN_SON_RETOUR = "file:/" + Main.CHEMIN_SON + "/retour.wav";
	public static final String DATE_DEVELOPPEMENT = "2018";
	public static final String EMAIL_DEVELOPPEUR = "abosoaymerik@gmail.com";
	public static final String VERSION_DEVELOPPEMENT = "1.0";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		//On place la valeur des textes ici pour ajuster dynamiquement par rapport au Main
		this.dateDeveloppement.setText(Controleur_PageCredits.DATE_DEVELOPPEMENT);
		this.emailDeveloppeur.setText(Controleur_PageCredits.EMAIL_DEVELOPPEUR);
		this.versionDeveloppement.setText(Controleur_PageCredits.VERSION_DEVELOPPEMENT);
		this.nomDeveloppeur.setText(Main.NOM_AUTEUR);
		this.nomJeu.setText(Main.NOM_APPLICATION + " ");
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


















