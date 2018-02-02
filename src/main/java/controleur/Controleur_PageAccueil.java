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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import service.UtiliserWS;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */



public class Controleur_PageAccueil implements Initializable {
	private static final int NB_CHAR_MAX_PSEUDO = 60; //Champ pseudo peut avoir 50 caractères max
	private static final int NB_CHAR_MAX_ADRESSE_IP = 80;
	public static final String CHEMIN_FXML_PAGE_CREDITS = "/vue/pageCredits.fxml";
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
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Mario_Kart_Stadium_Mario_Kart_8_OST.mp3";
	public static Media mediaSonBouton;
	public static MediaPlayer mediaPlayerSonBouton;
	public static final String CHEMIN_SON_BOUTON = "file:/" + Main.CHEMIN_SON + "/valider.wav";
	public static Media mediaSonMessage;
	public static MediaPlayer mediaPlayerSonMessage;
	public static final String CHEMIN_SON_MESSAGE = "file:/" + Main.CHEMIN_SON + "/message.wav";
	
	public static String PSEUDONYME = "";
	public static String ADRESSE_IP_SERVEUR = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		
		//CHARGEMENT DES CHAMPS SI ON ETAIT DEJA CONNECTE
		champPseudo.setText(Controleur_PageAccueil.PSEUDONYME);
		champAdresseServeur.setText(Controleur_PageAccueil.ADRESSE_IP_SERVEUR);

		//ECOUTEUR SUR LE BOUTON POUR LE DESACTIVER SI CHAMP PSEUDO VIDE
		champPseudo.textProperty().addListener((observable, oldValue, newValue) -> {
			boutonSeConnecter.setDisable(newValue.trim().isEmpty() || champAdresseServeur.getText().trim().isEmpty());

			//LIMITE LE NOMBRE DE CARACTERES DU TEXT FIELD PSEUDO
			if(newValue.length() >= Controleur_PageAccueil.NB_CHAR_MAX_PSEUDO + 1)
				champPseudo.setText(oldValue); //On efface le caractère
		});
		champAdresseServeur.textProperty().addListener((observable, oldValue, newValue) -> {
			boutonSeConnecter.setDisable(newValue.trim().isEmpty() || champPseudo.getText().trim().isEmpty());

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
		Controleur_PageAccueil.ADRESSE_IP_SERVEUR = champAdresseServeur.getText();
		String who = null;
		try {
			who = UtiliserWS.service_Who();
		} catch (Exception e) { //L'adresse du serveur n'est pas correcte
			//e.printStackTrace();
		}
		//- - -
		//ON TEST SI ON A EU UNE REPONSE
		if(who != null) { //REPONSE => PASSAGE A L'ECRAN SUIVANT
			/*Parent root = FXMLLoader.load(getClass().getResource(Controleur_PageAccueil.CHEMIN_FXML_PAGE_CREDITS));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
			scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
			Main.windowStage.setScene(scene);*/
		}
		else { //PAS DE REPONSE
			//AFFICHAGE MESSAGE ADRESSE SERVEUR INCORRECTE
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
			e.printStackTrace();
		}
	}
}


















