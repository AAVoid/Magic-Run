package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import main.Main;
import modele.StatistiquesVoiture;
import modele.Voiture;
import service.UtiliserWS;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Controleur_PageJeu implements Initializable {
	@FXML
	private AnchorPane anchorLayout;
	@FXML
    private Button boutonEcouteurKey;
	@FXML
    private Label vitesseAffichee;
	public Timeline timerJeu;

	public static final String CHEMIN_FXML_PAGE_JEU = "/vue/pageJeu.fxml";
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Dark_Souls_III_Soundtrack_OST_-_Iudex_Gundyr.mp3";
	public static final int NOMBRE_FPS_JEU = 30;
	public static final int FACTEUR_VITESSE = 2;
	public static ArrayList<String> listeTouchesPressees;
	public static String jsonListeJoueurs;
	
	private Voiture voitureJoueur;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		Controleur_PageJeu.listeTouchesPressees = new ArrayList<String>();
		//Affichage de la voiture du joueur
		Image imageVoitureJoueur = new Image(Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.get(Controleur_PageChoixVoiture.nomVoitureSelectionneeValide));
		ImageView imageVoiture = new ImageView(imageVoitureJoueur);
		voitureJoueur = new Voiture(imageVoiture, StatistiquesVoiture.copier(Controleur_PageChoixVoiture.statistiqueVoiture));
		voitureJoueur.getImage().setLayoutX((Main.LONGUEUR_FENETRE / 2) - (voitureJoueur.getImage().getFitWidth() / 2));
		voitureJoueur.getImage().setLayoutY((Main.HAUTEUR_FENETRE / 2) - (voitureJoueur.getImage().getFitHeight() / 2));
		anchorLayout.getChildren().add(voitureJoueur.getImage());
		this.timerJeu = new Timeline(new KeyFrame(Duration.millis(1000 / Controleur_PageJeu.NOMBRE_FPS_JEU), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        traiterTouches();
		        actualiser();
		        //System.out.println(Controleur_PageJeu.jsonListeJoueurs);
		    }
		}));
		this.timerJeu.setCycleCount(Timeline.INDEFINITE);
		this.timerJeu.play();
	}
	
	@FXML
    void getKeyJeu(KeyEvent event) {
		if(!Controleur_PageJeu.listeTouchesPressees.contains(event.getCode().getName()))
			Controleur_PageJeu.listeTouchesPressees.add(event.getCode().getName());
    }
	
	private void actualiser() {
		try {
			Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Who();
			JSONArray tab = null;
			tab = new JSONArray(Controleur_PageJeu.jsonListeJoueurs);
			String pseudo;
			int x;
			int y;
			int angle;
			int vitesse;
			for(int i = 0 ; i < tab.length() ; i++) {
				try {
					pseudo = tab.getJSONObject(i).getString("pseudonyme");
					x = tab.getJSONObject(i).getJSONObject("voiture").getInt("x");
					y = tab.getJSONObject(i).getJSONObject("voiture").getInt("y");
					angle = tab.getJSONObject(i).getJSONObject("voiture").getInt("angle");
					vitesse = tab.getJSONObject(i).getJSONObject("voiture").getInt("vitesse");
					if(pseudo.equals(Controleur_PageAccueil.PSEUDONYME)) {
						//Actualise la position et la vitesse affichée
						this.vitesseAffichee.setText("" + (vitesse * Controleur_PageJeu.FACTEUR_VITESSE) + " km/h");
						this.voitureJoueur.getImage().setRotate(angle + 90); 
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	private void traiterTouches() {
		if(Controleur_PageJeu.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheAccelerer)) {
			//System.out.println("Avancer");
			try {
				Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "avancer", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			Controleur_PageJeu.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheAccelerer);
		}
		else if(Controleur_PageJeu.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheFreiner)) {
			try {
				Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "reculer", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			//System.out.println("Freiner");
			Controleur_PageJeu.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheFreiner);
		}
		else if(Controleur_PageJeu.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheTournerGauche)) {
			//System.out.println("Gauche");
			try {
				Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "gauche");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			Controleur_PageJeu.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheTournerGauche);
		}
		else if(Controleur_PageJeu.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheTournerDroite)) {
			//System.out.println("Droite");
			try {
				Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "droite");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			Controleur_PageJeu.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheTournerDroite);
		}
		else { //Si on appuie sur rien
			try {
				Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
}






















