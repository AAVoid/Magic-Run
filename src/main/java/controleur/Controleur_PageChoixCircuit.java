package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;

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
import service.UtiliserWS;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Controleur_PageChoixCircuit implements Initializable {
	public static final String CHEMIN_FXML_PAGE_CHOIX_CIRCUIT = "/vue/pageChoixTerrain.fxml";
	@FXML
	private JFXButton boutonSeDeconnecter;
	@FXML
	private Label nombreJoueurs;
	@FXML
	private Label nomCircuit;
	@FXML
	private Label pseudoJoueur;
	@FXML
	private JFXButton boutonPoursuivre;
	@FXML
	private JFXListView<String> listeCircuit;
	@FXML
	private JFXButton boutonPrecedent;
	@FXML
    private ImageView imageMinature;
	@FXML
    private JFXSlider sliderNombreToursTerrain;

	
	private static final String CHEMIN_IMAGE_CIRCUITS = "file:/" + Main.CHEMIN_IMAGE + "/";
	public static Media mediaSonRetour;
	public static MediaPlayer mediaPlayerSonRetour;
	public static Media mediaSonContinuer;
	public static MediaPlayer mediaPlayerSonContinuer;
	public static Media mediaSonSelection;
	public static MediaPlayer mediaPlayerSonSelection;
	public static String nomCircuitSelectionneeValide;
	public static int NOMBRE_TOURS_A_FAIRE = 0;
	private static final int COORDONNEES_X_INITIALES = 30;
	private static final int COORDONNEES_Y_INITIALES = 15;
	
	private ArrayList<String> listeNomsCircuits;
	private HashMap<String, String> hashMapNomCircuitsNomImage;

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
		String nomCircuit_1 = "Evolution";
		//String nomCircuit_2 = "Test";
		this.listeNomsCircuits = new ArrayList<>();
		this.hashMapNomCircuitsNomImage = new HashMap<String, String>();
		this.listeNomsCircuits.add(nomCircuit_1);
		this.hashMapNomCircuitsNomImage.put(nomCircuit_1, Controleur_PageChoixCircuit.CHEMIN_IMAGE_CIRCUITS + nomCircuit_1 + "_Map.png");
		nomCircuit.setText(nomCircuit_1);
		Image imageMiniat = new Image(this.hashMapNomCircuitsNomImage.get(nomCircuit_1));
		imageMinature.setImage(imageMiniat);

		//Controleur_PageChoixCircuit.listeNomsCircuits.add(nomCircuit_2);
		//Controleur_PageChoixCircuit.hashMapNomCircuitsNomImage.put(nomCircuit_2, Controleur_PageChoixCircuit.CHEMIN_IMAGE_CIRCUITS + nomCircuit_2 + ".png");

		listeCircuit.getItems().addAll(listeNomsCircuits);
		listeCircuit.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listeCircuit.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			//System.out.println(newValue);
			mediaSonSelection = new Media(Controleur_PageAccueil.CHEMIN_SON_BOUTON);
			mediaPlayerSonSelection = new MediaPlayer(mediaSonSelection);
			mediaPlayerSonSelection.setCycleCount(1);
			mediaPlayerSonSelection.play();
			//ON AFFICHE LA NOUVELLE IMAGE DU TERRAIN
			nomCircuit.setText(newValue);
			Image nouvelleImage = new Image(this.hashMapNomCircuitsNomImage.get(newValue));
			imageMinature.setImage(nouvelleImage);
		});
	}

	@FXML
	void afficherChoixVoiture(ActionEvent event) {
		mediaSonRetour = new Media(Controleur_PageCredits.CHEMIN_SON_RETOUR);
		mediaPlayerSonRetour = new MediaPlayer(mediaSonRetour);
		mediaPlayerSonRetour.setCycleCount(1);
		mediaPlayerSonRetour.play();
		//Changement de scene
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(Controleur_PageAccueil.CHEMIN_FXML_PAGE_SELECTION_VOITURE));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
			scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
			Main.windowStage.setScene(scene);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}

	@FXML
	void afficherCommandes(ActionEvent event) {
		mediaSonContinuer = new Media(Controleur_PageAccueil.CHEMIN_SON_MESSAGE);
		mediaPlayerSonContinuer = new MediaPlayer(mediaSonContinuer);
		mediaPlayerSonContinuer.setCycleCount(1);
		mediaPlayerSonContinuer.play();
		Controleur_PageChoixCircuit.nomCircuitSelectionneeValide = nomCircuit.getText();
		Controleur_PageChoixCircuit.NOMBRE_TOURS_A_FAIRE = (int)this.sliderNombreToursTerrain.getValue();
		//On place la voiture initialement sur le terrain
		Controleur_PageChoixVoiture.statistiqueVoiture.setX(Controleur_PageChoixCircuit.COORDONNEES_X_INITIALES);
		Controleur_PageChoixVoiture.statistiqueVoiture.setY(Controleur_PageChoixCircuit.COORDONNEES_Y_INITIALES);
		Controleur_PageChoixVoiture.statistiqueVoiture.setAngle(0);
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(Controleur_PageChoixTouches.CHEMIN_FXML_PAGE_CHOIX_TOUCHES));
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
}























