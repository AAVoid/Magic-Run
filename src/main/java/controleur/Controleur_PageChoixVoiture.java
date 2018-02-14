package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;
import modele.StatistiquesVoiture;
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
	@FXML
	private ImageView imageVoiture;
	@FXML
    private Label valeurPriseVent;
    @FXML
    private Label valeurVitesseMax;
    @FXML
    private Label valeurTenueRoute;
    @FXML
    private Label valeurPuissance;
    @FXML
    private Label valeurFreinage;

	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static Media mediaSonContinuer;
	public static MediaPlayer mediaPlayerSonContinuer;
	public static Media mediaSonSelection;
	public static MediaPlayer mediaPlayerSonSelection;
	public static ArrayList<String> listeNomsVoitures;
	private static final String CHEMIN_IMAGE_VOITURE = "file:/" + Main.CHEMIN_IMAGE + "/F1_";
	private static HashMap<String, String> hashMapNomVoitureNomImage;
	private static HashMap<String, StatistiquesVoiture> hashMapNomVoitureStatistiques;
	private static StatistiquesVoiture statistiqueVoiture;

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
		Controleur_PageChoixVoiture.statistiqueVoiture = new StatistiquesVoiture();
		Controleur_PageChoixVoiture.listeNomsVoitures = new ArrayList<>();
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage = new HashMap<String, String>();
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques = new HashMap<String, StatistiquesVoiture>();
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_1);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_1, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_1 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_1, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_N, 
						StatistiquesVoiture.ROTATION_N, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_N, 
						StatistiquesVoiture.VITESSE_MAX_N));
		Controleur_PageChoixVoiture.statistiqueVoiture = Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.get(nomVoiture_1);
		this.valeurPuissance.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getAcceleration());
		this.valeurPriseVent.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getDeceleration());
		this.valeurFreinage.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getFrein());
		this.valeurTenueRoute.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getRotation());
		this.valeurVitesseMax.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getVitesse());
		nomVoitureSelectionnee.setText(nomVoiture_1);
		Controleur_PageChoixVoiture.statistiqueVoiture = Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.get(nomVoiture_1);
		imageVoiture.setImage(new Image(Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.get(nomVoiture_1)));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_2);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_2, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_2 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_2, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_U, 
						StatistiquesVoiture.ROTATION_N, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_N, 
						StatistiquesVoiture.VITESSE_MAX_D));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_3);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_3, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_3 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_3, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_N, 
						StatistiquesVoiture.ROTATION_U, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_N, 
						StatistiquesVoiture.VITESSE_MAX_D));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_4);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_4, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_4 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_4, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_N, 
						StatistiquesVoiture.ROTATION_N, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_U, 
						StatistiquesVoiture.VITESSE_MAX_D));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_5);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_5, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_5 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_5, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_D, 
						StatistiquesVoiture.ROTATION_N, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_N, 
						StatistiquesVoiture.VITESSE_MAX_U));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_6);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_6, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_6 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_6, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_N, 
						StatistiquesVoiture.ROTATION_D, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_N, 
						StatistiquesVoiture.VITESSE_MAX_U));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_7);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_7, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_7 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_7, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_N, 
						StatistiquesVoiture.ROTATION_N, 
						StatistiquesVoiture.DECELERATION_N, 
						StatistiquesVoiture.FREIN_D, 
						StatistiquesVoiture.VITESSE_MAX_U));
		Controleur_PageChoixVoiture.listeNomsVoitures.add(nomVoiture_8);
		Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.put(nomVoiture_8, Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE + nomVoiture_8 + ".png");
		Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.put(nomVoiture_8, 
				new StatistiquesVoiture(0, 0, 0, 0, 
						StatistiquesVoiture.ACCELERATION_U, 
						StatistiquesVoiture.ROTATION_U, 
						StatistiquesVoiture.DECELERATION_U, 
						StatistiquesVoiture.FREIN_U, 
						StatistiquesVoiture.VITESSE_MAX_U));
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
			Image nouvelleImage = new Image(Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.get(newValue));
			imageVoiture.setImage(nouvelleImage);
			Controleur_PageChoixVoiture.statistiqueVoiture = Controleur_PageChoixVoiture.hashMapNomVoitureStatistiques.get(newValue);
			this.valeurPuissance.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getAcceleration());
			this.valeurPriseVent.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getDeceleration());
			this.valeurFreinage.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getFrein());
			this.valeurTenueRoute.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getRotation());
			this.valeurVitesseMax.setText("" + Controleur_PageChoixVoiture.statistiqueVoiture.getVitesse());
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





































