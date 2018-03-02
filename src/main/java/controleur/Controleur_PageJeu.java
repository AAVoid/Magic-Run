package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONException;

import com.jfoenix.controls.JFXButton;

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
	@FXML
    private JFXButton boutonQuitterPartie;

	public static final String CHEMIN_FXML_PAGE_JEU = "/vue/pageJeu.fxml";
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Dark_Souls_III_Soundtrack_OST_-_Iudex_Gundyr.mp3";
	public static final int NOMBRE_FPS_JEU = 30;
	public static final int FACTEUR_VITESSE = 2;
	public static Timeline timerJeu;
	
	private ArrayList<String> listeTouchesPressees;
	private String jsonListeJoueurs;
	private HashMap<String, Voiture> hashMapJoueur;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Charg�");
		this.listeTouchesPressees = new ArrayList<String>();
		this.hashMapJoueur = new HashMap<String, Voiture>();
		this.jsonListeJoueurs = "";
		Controleur_PageJeu.timerJeu = new Timeline(new KeyFrame(Duration.millis(1000 / Controleur_PageJeu.NOMBRE_FPS_JEU), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	actualiserPositionCreerJoueur();
		        traiterTouches();
		    }
		}));
		Controleur_PageJeu.timerJeu.setCycleCount(Timeline.INDEFINITE);
		Controleur_PageJeu.timerJeu.play();
	}
	
	@FXML
    void getKeyJeu(KeyEvent event) {
		if(!this.listeTouchesPressees.contains(event.getCode().getName()))
			this.listeTouchesPressees.add(event.getCode().getName());
    }
	
	private void actualiserPositionCreerJoueur() {
		try {
			this.jsonListeJoueurs = UtiliserWS.service_Who();
			JSONArray tab = null;
			tab = new JSONArray(this.jsonListeJoueurs);
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
					if(!hashMapJoueur.containsKey(pseudo)) { //SI LE JOUEUR N'A PAS ENCORE REJOINT LA PARTIE
						if(pseudo.equals(Controleur_PageAccueil.PSEUDONYME)) { //SI C'EST NOUS
							hashMapJoueur.put(Controleur_PageAccueil.PSEUDONYME, 
									new Voiture(new ImageView(
											new Image(Controleur_PageChoixVoiture.hashMapNomVoitureNomImage.get(Controleur_PageChoixVoiture.nomVoitureSelectionneeValide))
											), StatistiquesVoiture.copier(Controleur_PageChoixVoiture.statistiqueVoiture)));
							Voiture v = hashMapJoueur.get(pseudo);
							v.getImage().setLayoutX(x);
							v.getImage().setLayoutY(y);
							v.getImage().setRotate(angle + 90);
							this.anchorLayout.getChildren().add(v.getImage());
						}
						else { //SI C'EST UN ADVERSAIRE
							hashMapJoueur.put(pseudo, 
									new Voiture(new ImageView(
											new Image(Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE_ADVERSAIRE)
											), new StatistiquesVoiture(x, y, angle, 0, 0, 0, 0, 0, 0)));
							Voiture v = hashMapJoueur.get(pseudo);
							v.getImage().setLayoutX(x);
							v.getImage().setLayoutY(y);
							v.getImage().setRotate(angle + 90);
							this.anchorLayout.getChildren().add(v.getImage());
						}
					}
					else { //SI LE JOUEUR A DEJA REJOINT LA PARTIE, ON LE DEPLACE UNIQUEMENT
						Voiture v = hashMapJoueur.get(pseudo);
						v.getImage().setLayoutX(x);
						v.getImage().setLayoutY(y);
						v.getImage().setRotate(angle + 90);
						//Si c'est nous on actualise la vitesse affich�e
						if(pseudo.equals(Controleur_PageAccueil.PSEUDONYME))
							this.vitesseAffichee.setText("" + vitesse + " km/h");
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
		if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheAccelerer)) {
			System.out.println("Avancer");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "avancer", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheAccelerer);
		}
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheFreiner)) {
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "reculer", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			System.out.println("Freiner");
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheFreiner);
		}
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheTournerGauche)) {
			System.out.println("Gauche");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "gauche");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheTournerGauche);
		}
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheTournerDroite)) {
			System.out.println("Droite");
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "droite");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheTournerDroite);
		}
		/*else { //Si on appuie sur rien
			try {
				Controleur_PageJeu.jsonListeJoueurs = UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}*/
	}
	
	@FXML
    void quitterPartie(ActionEvent event) {
		System.out.println("QUITTER");
	}
}






















