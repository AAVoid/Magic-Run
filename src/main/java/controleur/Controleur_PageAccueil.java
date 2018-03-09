package controleur;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import service.UtiliserWS;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */



public class Controleur_PageAccueil implements Initializable {
	private static final int NB_CHAR_MAX_PSEUDO = 40; //Champ pseudo peut avoir 40 caractères max
	private static final int NB_CHAR_MAX_ADRESSE_IP = 60;
	public static final String CHEMIN_FXML_PAGE_CREDITS = "/vue/pageCredits.fxml";
	public static final String CHEMIN_FXML_PAGE_SELECTION_VOITURE = "/vue/pageSelectionVoiture.fxml";
	@FXML
	private JFXTextField champPseudo;
	@FXML
	private JFXTextField champAdresseServeur;
	@FXML
	private JFXButton boutonSeConnecter;
	@FXML
	private JFXButton boutonCredits;

	//MUSIQUE ET SON
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Halo_4-117.mp3";
	public static Media mediaSonBouton;
	public static MediaPlayer mediaPlayerSonBouton;
	public static final String CHEMIN_SON_BOUTON = "file:/" + Main.CHEMIN_SON + "/valider.wav";
	public static Media mediaSonMessage;
	public static MediaPlayer mediaPlayerSonMessage;
	public static final String CHEMIN_SON_MESSAGE = "file:/" + Main.CHEMIN_SON + "/message.wav";
	private static final int COORDONNEES_X_APRES_CONNEXION = -9999999;
	private static final int COORDONNEES_Y_APRES_CONNEXION = -9999999;

	public static String PSEUDONYME = "";
	public static String ADRESSE_IP_SERVEUR = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");

		//ECOUTEUR SUR LE BOUTON POUR LE DESACTIVER SI CHAMP PSEUDO VIDE
		champPseudo.textProperty().addListener((observable, oldValue, newValue) -> {
			boutonSeConnecter.setDisable(newValue.replace(" ", "").isEmpty() || champAdresseServeur.getText().replace(" ", "").isEmpty());

			//LIMITE LE NOMBRE DE CARACTERES DU TEXT FIELD PSEUDO
			if(newValue.length() >= Controleur_PageAccueil.NB_CHAR_MAX_PSEUDO + 1)
				champPseudo.setText(oldValue); //On efface le caractère
		});
		champAdresseServeur.textProperty().addListener((observable, oldValue, newValue) -> {
			boutonSeConnecter.setDisable(newValue.replace(" ", "").isEmpty() || champPseudo.getText().replace(" ", "").isEmpty());

			//LIMITE LE NOMBRE DE CARACTERES DU TEXT FIELD PSEUDO
			if(newValue.length() >= Controleur_PageAccueil.NB_CHAR_MAX_ADRESSE_IP + 1)
				champAdresseServeur.setText(oldValue);
		});

		if(mediaMusiqueFond == null) { //ON LANCE LA MUSIQUE QUE SI ELLE N'EST PAS DEJA EN TRAIN D'ETRE JOUEE
			mediaMusiqueFond = new Media(Controleur_PageAccueil.CHEMIN_MUSIQUE_FOND);
			mediaPlayerMusiqueFond = new MediaPlayer(mediaMusiqueFond);
			mediaPlayerMusiqueFond.setCycleCount(Timeline.INDEFINITE); //Lecture de la musique en boucle
			mediaPlayerMusiqueFond.play();
		}

		//CHARGEMENT DES CHAMPS SI ON ETAIT DEJA CONNECTE
		champPseudo.setText(Controleur_PageAccueil.PSEUDONYME);
		champAdresseServeur.setText(Controleur_PageAccueil.ADRESSE_IP_SERVEUR);
	}

	@FXML
	void seConnecter(ActionEvent event) {
		//System.out.println("CONNECTE !");
		//On doit recharger le son à chaque fois car en le jouant une fois, il est effacé de la mémoire
		//à la fin de sa lecture
		mediaSonMessage = new Media(Controleur_PageAccueil.CHEMIN_SON_MESSAGE);
		mediaPlayerSonMessage = new MediaPlayer(mediaSonMessage);
		mediaPlayerSonMessage.setCycleCount(1);
		mediaPlayerSonMessage.play();
		//Adresse du serveur, par exemple : Adr. serv. : http://93.113.223.251/magicrun/
		Controleur_PageAccueil.ADRESSE_IP_SERVEUR = champAdresseServeur.getText().replace(" ", "");
		Controleur_PageAccueil.PSEUDONYME = champPseudo.getText().replace(" ", "");
		String reponse = null;
		try {
			reponse = UtiliserWS.service_Nouveau(Controleur_PageAccueil.PSEUDONYME);
		} catch (Exception e) { //L'adresse du serveur n'est pas correcte
			//e.printStackTrace();
		}
		//- - -
		//ON TEST SI ON A EU UNE REPONSE
		if(reponse != null) { //REPONSE => EST-CE QUE LE PSEUDO EST DEJA UTILISE ?
			try {
				String status = UtiliserWS.getStatusService_Nouveau(reponse);
				if(status.equals("OK")) {
					reponse = UtiliserWS.service_Teleporter(Controleur_PageAccueil.PSEUDONYME, 
							Controleur_PageAccueil.COORDONNEES_X_APRES_CONNEXION, 
							Controleur_PageAccueil.COORDONNEES_Y_APRES_CONNEXION, 0);
					//On affiche l'écran de sélection de la voiture
					Parent root = FXMLLoader.load(getClass().getResource(Controleur_PageAccueil.CHEMIN_FXML_PAGE_SELECTION_VOITURE));
					Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
					scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
					Main.windowStage.setScene(scene);
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERREUR");
					alert.setHeaderText("Pseudonyme déjà utilisé !");
					alert.setContentText("Le pseudonyme \"" + Controleur_PageAccueil.PSEUDONYME + "\" est déjà utilisé !");
					alert.showAndWait();
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}			
		}
		else { //PAS DE REPONSE : MAUVAISE ADRESSE DU SERVEUR
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERREUR");
			alert.setHeaderText("Serveur de jeu non trouvé !");
			alert.setContentText("Le serveur de jeu n'a pas été trouvé via l'adresse spécifiée !");
			alert.showAndWait();
		}
	}

	@FXML
	void afficherCredits(ActionEvent event) {
		//System.out.println("Afficher crédits !");
		try {
			mediaSonBouton = new Media(Controleur_PageAccueil.CHEMIN_SON_BOUTON);
			mediaPlayerSonBouton = new MediaPlayer(mediaSonBouton);
			mediaPlayerSonBouton.setCycleCount(1); //On joue le son qu'une fois à chaque lecture
			mediaPlayerSonBouton.play();
			Parent root = FXMLLoader.load(getClass().getResource(Controleur_PageAccueil.CHEMIN_FXML_PAGE_CREDITS));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
			scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
			Main.windowStage.setScene(scene);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}


















