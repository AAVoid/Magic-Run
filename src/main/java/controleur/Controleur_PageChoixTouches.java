package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import service.UtiliserWS;

/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Controleur_PageChoixTouches implements Initializable {
	public static final String CHEMIN_FXML_PAGE_CHOIX_TOUCHES = "/vue/pageConfigurationCommandes.fxml";
	@FXML
	private JFXButton boutonSeDeconnecter;
	@FXML
	private Label nombreJoueurs;
	@FXML
	private Label pseudoJoueur;
	@FXML
	private JFXButton boutonPoursuivre;
	@FXML
	private JFXButton boutonPrecedent;
	@FXML
	private JFXTextField champToucheAccelerer;
	@FXML
	private JFXTextField champToucheTournerDroite;
	@FXML
	private JFXTextField champToucheFreiner;
	@FXML
	private JFXTextField champToucheTournerGauche;

	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static Media mediaSonContinuer;
	public static MediaPlayer mediaPlayerSonContinuer;
	public static Media mediaSonSelection;
	public static MediaPlayer mediaPlayerSonSelection;

	public static String nomToucheAccelerer;
	public static String nomToucheFreiner;
	public static String nomToucheTournerGauche;
	public static String nomToucheTournerDroite;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		//AFFICHAGE DU PSEUDO DU JOUEUR ET DU NOMBRE DE JOUEURS CONNECTES
		pseudoJoueur.setText(Controleur_PageAccueil.PSEUDONYME);
		try {
			nombreJoueurs.setText("" + UtiliserWS.service_getNombreJoueursConnectes());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		nomToucheAccelerer = "";
		nomToucheFreiner = "";
		nomToucheTournerGauche = "";
		nomToucheTournerDroite = "";
	}

	@FXML
	void keyPressedChampAccelerer(KeyEvent event) {
		//System.out.println("Accel" + "/" + event.getCode() + "/" + event.getText());
		champToucheAccelerer.setText(event.getCode().getName());
		Controleur_PageChoixTouches.nomToucheAccelerer = event.getCode().getName();
		//Activation ou non du bouton pour poursuivre
		boutonPoursuivre.setDisable(
				Controleur_PageChoixTouches.nomToucheAccelerer.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheFreiner.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerGauche.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerDroite.isEmpty()
				);
	}

	@FXML
	void keyPressedChampFreiner(KeyEvent event) {
		//System.out.println("Frein" + "/" + event.getCode() + "/" + event.getText());
		champToucheFreiner.setText(event.getCode().getName());
		Controleur_PageChoixTouches.nomToucheFreiner = event.getCode().getName();
		//Activation ou non du bouton pour poursuivre
		boutonPoursuivre.setDisable(
				Controleur_PageChoixTouches.nomToucheAccelerer.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheFreiner.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerGauche.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerDroite.isEmpty()
				);
	}

	@FXML
	void keyPressedChampTournerGauche(KeyEvent event) {
		//System.out.println("Gauche" + "/" + event.getCode() + "/" + event.getText());
		champToucheTournerGauche.setText(event.getCode().getName());
		Controleur_PageChoixTouches.nomToucheTournerGauche = event.getCode().getName();
		//Activation ou non du bouton pour poursuivre
		boutonPoursuivre.setDisable(
				Controleur_PageChoixTouches.nomToucheAccelerer.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheFreiner.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerGauche.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerDroite.isEmpty()
				);
	}

	@FXML
	void keyPressedChampTournerDroite(KeyEvent event) {
		//System.out.println("Droite" + "/" + event.getCode() + "/" + event.getText());
		champToucheTournerDroite.setText(event.getCode().getName());
		Controleur_PageChoixTouches.nomToucheTournerDroite = event.getCode().getName();
		//Activation ou non du bouton pour poursuivre
		boutonPoursuivre.setDisable(
				Controleur_PageChoixTouches.nomToucheAccelerer.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheFreiner.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerGauche.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerDroite.isEmpty()
				);
	}

	@FXML
	void afficherChoixCircuit(ActionEvent event) {
		mediaSonRetour = new Media(Controleur_PageCredits.CHEMIN_SON_RETOUR);
		mediaPlayerSonRetour = new MediaPlayer(mediaSonRetour);
		mediaPlayerSonRetour.setCycleCount(1);
		mediaPlayerSonRetour.play();
		//Changement de scene
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(Controleur_PageChoixCircuit.CHEMIN_FXML_PAGE_CHOIX_CIRCUIT));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
			scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
			Main.windowStage.setScene(scene);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	@FXML
	void seDeconnecter(ActionEvent event) {
		mediaSonRetour = new Media(Controleur_PageCredits.CHEMIN_SON_RETOUR);
		mediaPlayerSonRetour = new MediaPlayer(mediaSonRetour);
		mediaPlayerSonRetour.setCycleCount(1);
		mediaPlayerSonRetour.play();
		//DECONNEXION DU JOUEUR
		try {
			UtiliserWS.service_DeconnecterJoueur(Controleur_PageAccueil.PSEUDONYME);
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
		//RETOUR A LA PAGE D'ACCUEIL
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(Main.CHEMIN_FXML_PAGE_ACCUEIL));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
			scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
			Main.windowStage.setScene(scene);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	@FXML
	void lancerCourse(ActionEvent event) {
		mediaSonContinuer = new Media(Controleur_PageAccueil.CHEMIN_SON_MESSAGE);
		mediaPlayerSonContinuer = new MediaPlayer(mediaSonContinuer);
		mediaPlayerSonContinuer.setCycleCount(1);
		mediaPlayerSonContinuer.play();
	}
}



































