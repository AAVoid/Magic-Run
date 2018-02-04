package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import service.UtiliserWS;



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
	@FXML
	private JFXListView<String> listeVoiture;
	@FXML
    private Label nomVoitureSelectionnee;

	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static Media mediaSonContinuer;
	public static MediaPlayer mediaPlayerSonContinuer;
	public static Media mediaSonSelection;
	public static MediaPlayer mediaPlayerSonSelection;
	public static ArrayList<String> listeNomsVoitures;

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
		//PREPARATION DE LA LIST VIEW
		String nomVoiture_1 = "Neutrino";
		String nomVoiture_2 = "Propulso";
		String nomVoiture_3 = "Gliseo";
		String nomVoiture_4 = "Sheo";
		String nomVoiture_5 = "Melo";
		String nomVoiture_6 = "Fleo";
		String nomVoiture_7 = "Falco";
		String nomVoiture_8 = "Hero";
		Controleur_PageChoixVoiture.listeNomsVoitures = new ArrayList<>();
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_1);
		nomVoitureSelectionnee.setText(nomVoiture_1);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_2);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_3);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_4);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_5);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_6);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_7);
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_8);
		listeVoiture.getItems().addAll(listeNomsVoitures);
		listeVoiture.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //On peut sélectionner qu'une voiture
		listeVoiture.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			//EN CAS DE CHANGEMENT DE VOITURE SELECTIONNEE DANS LA LISTE
			//System.out.println(newValue);
			mediaSonSelection = new Media(Controleur_PageAccueil.CHEMIN_SON_BOUTON);
			mediaPlayerSonSelection = new MediaPlayer(mediaSonSelection);
			mediaPlayerSonSelection.setCycleCount(1);
			mediaPlayerSonSelection.play();
			//ON AFFICHE LA NOUVELLE IMAGE DE VOITURE ET SES STATISTIQUES
			nomVoitureSelectionnee.setText(newValue);
		});
	}

	@FXML
	void seDeconnecter(ActionEvent event) {
		//SON DE RETOUR
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
	void choisirTerrain(ActionEvent event) {
		mediaSonContinuer = new Media(Controleur_PageAccueil.CHEMIN_SON_MESSAGE);
		mediaPlayerSonContinuer = new MediaPlayer(mediaSonContinuer);
		mediaPlayerSonContinuer.setCycleCount(1);
		mediaPlayerSonContinuer.play();
	}
}





































