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
	@FXML
	private JFXTextField champToucheRetour;

	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static Media mediaSonContinuer;
	public static MediaPlayer mediaPlayerSonContinuer;
	public static Media mediaSonSelection;
	public static MediaPlayer mediaPlayerSonSelection;
	public static final String CHEMIN_SON_ENGAGEMENT = "file:/" + Main.CHEMIN_SON + "/engagement.wav";
	public static Media mediaSonEngagement;
	public static MediaPlayer mediaPlayerSonEngagement;
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static String nomToucheAccelerer;
	public static String nomToucheFreiner;
	public static String nomToucheTournerGauche;
	public static String nomToucheTournerDroite;
	public static String nomToucheRetour;
	public static ArrayList<String> listeTouchesDeJeu;
	private static final String texteInformationsJeu_1 = 
			"Le principe d'une course en jeu est celui du contre-la-montre. \r\n" + 
			"\r\n" + 
			"Dans la partie sup�rieure gauche de l'�cran il y a un chronom�tre. Il est lanc� d�s l'instant o� vous allez utiliser les commandes de jeu configur�es.\r\n" + 
			"Il s'arr�te automatiquement apr�s avoir fait le nombre de tours de terrain choisi.\r\n" + 
			"\r\n" + 
			"Les joueurs peuvent r�aliser leurs tours de terrain en m�me temps en faisant la course ou peuvent le faire quand bon leur semble. Le chronom�tre juge des performances de chacun.\r\n" + 
			"\r\n" + 
			"Vous pouvez quitter la course gr�ce � l'ic�ne dans la partie inf�rieure droite de l'�cran.\r\n" + 
			"\r\n" + 
			"La vitesse de votre voiture est affich�e dans la partie inf�rieure gauche de l'�cran.\r\n" + 
			"\r\n" + 
			"Dans l'onglet \"Joueurs connect�s\" vous pouvez voir la liste des joueurs connect�s. Vous pouvez aussi choisir ou non que les pseudonymes des joueurs soient affich�s ainsi que leur couleur, ou m�me que les adversaires soient affich�s ou non.";
	private static final String texteInformationsJeu_2 = 
			"Difficult� n�1 impos�e aux joueurs : une seule touche de jeu est utilisable � la fois ! Il est donc impossible d'acc�l�rer et de tourner en m�me temps par exemple !\r\n" + 
			"\r\n" + 
			"Sur le terrain le chemin � suivre est divis� en plusieurs portions qui sont mat�rialis�es par de la neige plus fonc�e, en gris fonc�. Il n'est pas n�cessaire de suivre scrupuleusement ce chemin ! Il est l� � titre indicatif ! L'unique but : atteindre le t�l�porteur suivant ! Il est cependant conseill� de ne pas quitter une portion de route pour aller sur une autre...\r\n" + 
			"\r\n" + 
			"Difficult� n�2 impos�e aux joueurs : La t�l�portation\r\n" + 
			"Au bout de chaque portion de chemin se trouve une zone mat�rialis�e par des pierres marron. Ce sont des t�l�porteurs qui vous t�l�portent sur une autre portion du terrain.\r\n" + 
			"\r\n" + 
			"M�me si vous ne suivez pas le chemin d�fini par les diff�rentes portions de terrain, elles-m�mes d�finies par les t�l�porteurs, il convient de passer tous les t�l�porteurs dans l'ordre (si vous voulez tous les passer le plus rapidement que possible..., car suivre les portions de terrain permet de faire un tour au plus vite que possible...) car cela permet de valider votre tour de terrain ! CONCRETEMENT : Vous pouvez passer les t�l�porteurs dans l'ordre que vous voulez. Pour valider votre tour de terrain il faut passer par tous les t�l�porteurs. Si par malheur vous quittez le terrain et sortez de l'�cran, vous pouvez appuyer sur votre touche de retour � la derni�re t�l�portation pour que le dernier t�l�porteur pass� vous t�l�porte de nouveau.";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Charg�");
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
		nomToucheRetour = "";
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
				|| Controleur_PageChoixTouches.nomToucheRetour.isEmpty()
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
				|| Controleur_PageChoixTouches.nomToucheRetour.isEmpty()
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
				|| Controleur_PageChoixTouches.nomToucheRetour.isEmpty()
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
				|| Controleur_PageChoixTouches.nomToucheRetour.isEmpty()
				);
	}

	@FXML
	void keyPressedChampRetour(KeyEvent event) {
		//System.out.println("Retour" + "/" + event.getCode() + "/" + event.getText());
		champToucheRetour.setText(event.getCode().getName());
		Controleur_PageChoixTouches.nomToucheRetour = event.getCode().getName();
		//Activation ou non du bouton pour poursuivre
		boutonPoursuivre.setDisable(
				Controleur_PageChoixTouches.nomToucheAccelerer.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheFreiner.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerGauche.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheTournerDroite.isEmpty()
				|| Controleur_PageChoixTouches.nomToucheRetour.isEmpty()
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
			mediaMusiqueFond = new Media(Controleur_PageJeu.CHEMIN_MUSIQUE_FOND);
			mediaPlayerMusiqueFond = new MediaPlayer(mediaMusiqueFond);
			mediaPlayerMusiqueFond.setCycleCount(Timeline.INDEFINITE); //Lecture de la musique en boucle
			mediaPlayerMusiqueFond.play();
		}
		//On enregistre les touches de jeu
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheAccelerer);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheFreiner);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheTournerGauche);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheTournerDroite);
		Controleur_PageChoixTouches.listeTouchesDeJeu.add(Controleur_PageChoixTouches.nomToucheRetour);
		//AFFICHAGE DES INFORMATIONS DE JEU VIA UNE BOITE DE DIALOGUE
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("D�roulement d'une partie (1)");
		alert.setHeaderText("3..., 2..., 1..., GO !!!");
		alert.setContentText(Controleur_PageChoixTouches.texteInformationsJeu_1);
		alert.showAndWait();
		//- - -
		Alert alert2 = new Alert(AlertType.INFORMATION);
		alert2.setTitle("D�roulement d'une partie (2)");
		alert2.setHeaderText("3..., 2..., 1..., GO !!!");
		alert2.setContentText(Controleur_PageChoixTouches.texteInformationsJeu_2);
		alert2.showAndWait();
		//On joue le son d'engagement de partie, on arr�te la musique de fond actuelle et on lance la nouvelle musique de fond
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



































