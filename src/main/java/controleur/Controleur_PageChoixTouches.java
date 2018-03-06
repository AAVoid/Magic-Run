package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	public static final String CHEMIN_SON_ENGAGEMENT = "file:/" + Main.CHEMIN_SON + "/engagement.wav";
	public static Media mediaSonEngagement;
	public static MediaPlayer mediaPlayerSonEngagement;
	public static final String CHEMIN_MUSIQUE_FOND_JEU = "file:/" + Main.CHEMIN_MUSIQUE + "/Dark_Souls_III_Soundtrack_OST_-_Lorian_Elder_Prince_Lothric_Younger_Prince.mp3";
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static String nomToucheAccelerer;
	public static String nomToucheFreiner;
	public static String nomToucheTournerGauche;
	public static String nomToucheTournerDroite;
	public static ArrayList<String> listeTouchesDeJeu;
	private static final String texteInformationsJeu = 
			"Le principe d'une course en jeu est celui du contre-la-montre. \r\n" + 
			"\r\n" + 
			"Dans la partie supérieure gauche de l'écran il y a un chronomètre. Il est lancé dès l'instant où vous allez utiliser les commandes de jeu configurées.\r\n" + 
			"Il s'arrête automatiquement après avoir fait le nombre de tours de terrain choisi.\r\n" + 
			"\r\n" + 
			"Les joueurs peuvent réaliser leurs tours de terrain en même temps en faisant la course ou peuvent le faire quand bon leur semble. Le chronomètre juge des performances de chacun.\r\n" + 
			"\r\n" + 
			"Vous pouvez quitter la course grâce à l'icône dans la partie inférieure droite de l'écran.\r\n" + 
			"\r\n" + 
			"La vitesse de votre voiture est affichée dans la partie inférieure gauche de l'écran.\r\n" + 
			"\r\n" + 
			"La difficulté imposée aux joueurs : une seule touche de jeu est utilisable à la fois ! Il est donc impossible d'accélérer et de tourner en même temps par exemple !";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		Controleur_PageChoixTouches.listeTouchesDeJeu = new ArrayList<String>();
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
		final int TEMPS_ATTENTE_AVANT_ENGAGEMENT = 300;

		mediaSonContinuer = new Media(Controleur_PageAccueil.CHEMIN_SON_MESSAGE);
		mediaPlayerSonContinuer = new MediaPlayer(mediaSonContinuer);
		mediaPlayerSonContinuer.setCycleCount(1);
		mediaPlayerSonContinuer.play();
		//Modification des statistiques de la voiture en fonction de la voiture choisie
		try {
			UtiliserWS.service_ModifierCaracteristiques(Controleur_PageAccueil.PSEUDONYME, Controleur_PageChoixVoiture.statistiqueVoiture);
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
		mediaSonEngagement = new Media(Controleur_PageChoixTouches.CHEMIN_SON_ENGAGEMENT);
		mediaPlayerSonEngagement = new MediaPlayer(mediaSonEngagement);
		mediaPlayerSonEngagement.setCycleCount(1);
		mediaPlayerSonEngagement.play();
		Controleur_PageAccueil.mediaPlayerMusiqueFond.stop();
		if(mediaMusiqueFond == null) { //ON LANCE LA MUSIQUE QUE SI ELLE N'EST PAS DEJA EN TRAIN D'ETRE JOUEE
			mediaMusiqueFond = new Media(Controleur_PageChoixTouches.CHEMIN_MUSIQUE_FOND_JEU);
			mediaPlayerMusiqueFond = new MediaPlayer(mediaMusiqueFond);
			mediaPlayerMusiqueFond.setCycleCount(Timeline.INDEFINITE); //Lecture de la musique en boucle
			mediaPlayerMusiqueFond.play();
		}
		//On enregistre les touches de jeu
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheAccelerer);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheFreiner);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheTournerGauche);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheTournerDroite);
		//AFFICHAGE DES INFORMATIONS DE JEU VIA UNE BOITE DE DIALOGUE
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Déroulement d'une partie");
		alert.setHeaderText("3..., 2..., 1..., GO !!!");
		alert.setContentText(Controleur_PageChoixTouches.texteInformationsJeu);
		alert.showAndWait();
		//On joue le son d'engagement de partie, on arrête la musique de fond actuelle et on lance la nouvelle musique de fond
		try {
			Thread.sleep(TEMPS_ATTENTE_AVANT_ENGAGEMENT);
		} catch (InterruptedException e1) {
			//e1.printStackTrace();
		}
		//On lance le jeu
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(Controleur_PageJeu.CHEMIN_FXML_PAGE_JEU));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
			scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
			Main.windowStage.setScene(scene);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}



































