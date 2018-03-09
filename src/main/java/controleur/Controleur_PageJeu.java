package controleur;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToggleButton;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.Main;
import modele.StatistiquesVoiture;
import modele.Teleporteur;
import modele.Voiture;
import service.UtiliserWS;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Controleur_PageJeu implements Initializable {
	@FXML
	private AnchorPane anchorLayoutFenetre;
	@FXML
	private AnchorPane anchorLayout;
	@FXML
	private Label vitesseAffichee;
	@FXML
	private JFXButton boutonQuitterPartie;
	@FXML
	private Label chronoAffiche;
	@FXML
	private Label compteurTours;
	@FXML
	private JFXTabPane groupeOnglets;
	@FXML
	private Label nombreJoueursConnectes;
	@FXML
	private JFXListView<String> listeViewJoueursConnectes;
	@FXML
	private JFXToggleButton toggleAfficherPseudoJoueurs;
	@FXML
	private JFXColorPicker couleurPickerPseudo;
	@FXML
	private JFXToggleButton toggleAfficherAdversaires;

	public static final String CHEMIN_FXML_PAGE_JEU = "/vue/pageJeu.fxml";
	public static Media mediaMusiqueFond;
	public static MediaPlayer mediaPlayerMusiqueFond;
	public static final String CHEMIN_MUSIQUE_FOND = "file:/" + Main.CHEMIN_MUSIQUE + "/Epic_Soul_Factory-The_Glorious_Ones.mp3";
	public static final int NOMBRE_FPS_JEU = 30;
	public static final int FACTEUR_VITESSE = 6;
	public static Timeline timerJeu;
	public static Timeline timerChrono;
	private static final int TAUX_AMORTISSEMENT_DECELERATION = 10;
	public static Media mediaSonTeleportation;
	public static MediaPlayer mediaPlayerSonTeleportation;
	public static final String CHEMIN_SON_TELEPORTATION = "file:/" + Main.CHEMIN_SON + "/teleportation.wav";
	public static Media mediaSonTourValide;
	public static MediaPlayer mediaPlayerSonTourValide;
	public static Media mediaSonQuitterPartie;
	public static MediaPlayer mediaPlayerSonQuitterPartie;

	private ArrayList<String> listeTouchesPressees;
	private String jsonListeJoueurs;
	private HashMap<String, Voiture> hashMapJoueur;
	public int valeurChrono;
	private boolean partieDemarree;
	private int amortissementDeceleration;
	private int numeroTourActuel;
	private HashMap<Integer, Teleporteur> hashMapNumeroTeleporteurTeleporteur;
	private int numeroDernierTeleporteur;
	private boolean partieTerminee;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		this.listeTouchesPressees = new ArrayList<String>();
		this.hashMapJoueur = new HashMap<String, Voiture>();
		this.jsonListeJoueurs = "";
		this.valeurChrono = 0;
		this.chronoAffiche.setText("" + this.valeurChrono + "s");
		this.partieDemarree = false;
		this.amortissementDeceleration = 0;
		this.couleurPickerPseudo.setValue(Color.rgb(0, 0, 0));
		this.numeroTourActuel = 0;
		this.partieTerminee = false;
		this.numeroDernierTeleporteur = 7; //Dernier téléporteur du terrain pour être téléporter au début
		//ECOUTEUR SUR LA SELECTION D'ONGLET
		this.groupeOnglets.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			//System.out.println("" + oldTab.getText() + "/" + newTab.getText());
			if(newTab.getText().equals("Joueurs connectés")) { //Si on veut voir les joueurs connectés
				actualiserListeJoueursConnectes();
			}
		});
		//CREATION DES RECTANGLE POUR TELEPORTEURS
		this.hashMapNumeroTeleporteurTeleporteur = new HashMap<Integer, Teleporteur>();
		//Téléporteurs rajouté dans l'ordre de passage sur la carte
		this.hashMapNumeroTeleporteurTeleporteur.put(1, new Teleporteur(new Rectangle(570, 9, 50, 55),
				45, 29, 90, 1, false));
		this.hashMapNumeroTeleporteurTeleporteur.put(2, new Teleporteur(new Rectangle(0, 219, 30, 55),
				30, 454, 0, 2, false));
		this.hashMapNumeroTeleporteurTeleporteur.put(3, new Teleporteur(new Rectangle(0, 309, 30, 55),
				290, 394, 0, 3, false));
		this.hashMapNumeroTeleporteurTeleporteur.put(4, new Teleporteur(new Rectangle(516, 524, 59, 50),
				165, 539, -90, 4, false));
		this.hashMapNumeroTeleporteurTeleporteur.put(5, new Teleporteur(new Rectangle(425, 489, 50, 55),
				195, 399, -90, 5, false));
		this.hashMapNumeroTeleporteurTeleporteur.put(6, new Teleporteur(new Rectangle(370, 100, 45, 54),
				375, 204, 90, 6, false));
		this.hashMapNumeroTeleporteurTeleporteur.put(7, new Teleporteur(new Rectangle(570, 129, 50, 55),
				30, 10, 0, 7, false));
		//BOUCLE DE JEU
		Controleur_PageJeu.timerJeu = new Timeline(new KeyFrame(Duration.millis(1000 / Controleur_PageJeu.NOMBRE_FPS_JEU), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				actualiserPositionCreerJoueur();
				actualiserNombreToursTerrain();
				traiterTouches();
				teleporterJoueur();
				/*
				RECUPERER LA POSITION DE LA SOURIS A L'ECRAN : UTILISE POUR CREER LES TELEPORTEURS - y - 61 à cause des onglets
				com.sun.glass.ui.Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
		        int y = robot.getMouseY();
		        System.out.println("y point = " + y);
		        int x = robot.getMouseX();
		        System.out.println("x point= " + x);
				 */
			}
		}));
		Controleur_PageJeu.timerJeu.setCycleCount(Timeline.INDEFINITE);
		Controleur_PageJeu.timerJeu.play();
		//CHRONO
		Controleur_PageJeu.timerChrono = new Timeline(new KeyFrame(Duration.seconds(1.0), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				incrementerChrono();
			}
		}));
		Controleur_PageJeu.timerChrono.setCycleCount(Timeline.INDEFINITE);
	}

	@FXML
	void getKeyJeu(KeyEvent event) {
		if(!this.listeTouchesPressees.contains(event.getCode().getName())
				&& Controleur_PageChoixTouches.listeTouchesDeJeu.contains(event.getCode().getName())
				&& this.groupeOnglets.getSelectionModel().getSelectedIndex() == 0) {
			this.listeTouchesPressees.add(event.getCode().getName());
		}
	}

	private void teleporterJoueur() {
		for(Integer i : this.hashMapNumeroTeleporteurTeleporteur.keySet()) {
			Teleporteur t = this.hashMapNumeroTeleporteurTeleporteur.get(i);
			if(hashMapJoueur.get(Controleur_PageAccueil.PSEUDONYME).getImage().intersects(t.getRectangle().x, t.getRectangle().y, t.getRectangle().width, t.getRectangle().height)) {
				//System.out.println("COLLISION TELEPORTEUR");
				try {
					UtiliserWS.service_Teleporter(Controleur_PageAccueil.PSEUDONYME, t.getNewX(), t.getNewY(), t.getNewAngle());
					Controleur_PageJeu.mediaSonTeleportation = new Media(Controleur_PageJeu.CHEMIN_SON_TELEPORTATION);
					Controleur_PageJeu.mediaPlayerSonTeleportation = new MediaPlayer(mediaSonTeleportation);
					Controleur_PageJeu.mediaPlayerSonTeleportation.setCycleCount(1);
					Controleur_PageJeu.mediaPlayerSonTeleportation.play();
					this.numeroDernierTeleporteur = t.getNumero();
					t.setJoueurPasseParLa(true);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		}
	}

	private void actualiserNombreToursTerrain() {
		if(!this.partieTerminee) {
			if(this.numeroTourActuel != Controleur_PageChoixCircuit.NOMBRE_TOURS_A_FAIRE)
				this.compteurTours.setText("Tour " + (this.numeroTourActuel + 1) + " / " + Controleur_PageChoixCircuit.NOMBRE_TOURS_A_FAIRE);
			long nbTeleporteurs = this.hashMapNumeroTeleporteurTeleporteur.keySet().size();
			long nbTeleporteursPasses = this.hashMapNumeroTeleporteurTeleporteur.keySet().stream()
			.map(k -> hashMapNumeroTeleporteurTeleporteur.get(k))
			.filter(t -> t.isJoueurPasseParLa()).count();
			//System.out.println(nbTeleporteurs + "/" + nbTeleporteursPasses);
			//SI ON A PASSE TOUS LES TELEPORTEURS ON VALIDE LE TOUR
			if(nbTeleporteurs == nbTeleporteursPasses) {
				this.numeroTourActuel++;
				resetTeleporteurPasse();
				Controleur_PageJeu.mediaSonTourValide = new Media(Controleur_PageAccueil.CHEMIN_SON_BOUTON);
				Controleur_PageJeu.mediaPlayerSonTourValide = new MediaPlayer(mediaSonTourValide);
				Controleur_PageJeu.mediaPlayerSonTourValide.setCycleCount(1);
				Controleur_PageJeu.mediaPlayerSonTourValide.play();
			}
			//CAS OU TOUS LES TOURS SONT TERMINES
			if(this.numeroTourActuel == Controleur_PageChoixCircuit.NOMBRE_TOURS_A_FAIRE) {
				//Arrêt des timer etc.
				Controleur_PageJeu.timerChrono.stop();
				this.partieTerminee = true;
			}
		}
	}
	
	private void resetTeleporteurPasse() {
		for(Integer i : this.hashMapNumeroTeleporteurTeleporteur.keySet()) {
			Teleporteur t = this.hashMapNumeroTeleporteurTeleporteur.get(i);
			t.setJoueurPasseParLa(false);
		}
	}

	private void incrementerChrono() {
		this.valeurChrono++;
		this.chronoAffiche.setText("" + this.valeurChrono + "s");
	}

	private void actualiserListeJoueursConnectes() {
		try {
			this.listeViewJoueursConnectes.getItems().clear(); //On vide la liste
			ArrayList<String> listePseudoJoueurs = new ArrayList<String>();
			String jsonWho = UtiliserWS.service_Who();
			JSONArray tab = null;
			tab = new JSONArray(jsonWho);
			String pseudo;
			int nbJoueurs = tab.length();
			this.nombreJoueursConnectes.setText("" + nbJoueurs);
			for(int i = 0 ; i < nbJoueurs ; i++) {
				try {
					pseudo = tab.getJSONObject(i).getString("pseudonyme");
					listePseudoJoueurs.add(pseudo);
				} catch (JSONException e) {
					//e.printStackTrace();
				}
			}
			//Ajout des éléments à la liste
			Collections.sort(listePseudoJoueurs);
			this.listeViewJoueursConnectes.getItems().addAll(listePseudoJoueurs);
			this.listeViewJoueursConnectes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
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
											), StatistiquesVoiture.copier(Controleur_PageChoixVoiture.statistiqueVoiture),
											new Label(pseudo)));
							Voiture v = hashMapJoueur.get(pseudo);
							v.getImage().setX((double)x);
							v.getImage().setY((double)y);
							v.getImage().setRotate(angle + 90);
							v.getLabelPseudo().setLayoutX(x + 50);
							v.getLabelPseudo().setLayoutY(y);
							this.anchorLayout.getChildren().add(v.getImage());
							this.anchorLayout.getChildren().add(v.getLabelPseudo());
						}
						else { //SI C'EST UN ADVERSAIRE
							hashMapJoueur.put(pseudo, 
									new Voiture(new ImageView(
											new Image(Controleur_PageChoixVoiture.CHEMIN_IMAGE_VOITURE_ADVERSAIRE)
											), new StatistiquesVoiture(x, y, angle, 0, 0, 0, 0, 0, 0), new Label(pseudo)));
							Voiture v = hashMapJoueur.get(pseudo);
							v.getImage().setX((double)x);
							v.getImage().setY((double)y);
							v.getImage().setRotate(angle + 90);
							v.getLabelPseudo().setLayoutX(x + 50);
							v.getLabelPseudo().setLayoutY(y);
							this.anchorLayout.getChildren().add(v.getImage());
							this.anchorLayout.getChildren().add(v.getLabelPseudo());
						}
					}
					else { //SI LE JOUEUR A DEJA REJOINT LA PARTIE, ON LE DEPLACE UNIQUEMENT
						Voiture v = hashMapJoueur.get(pseudo);
						v.getImage().setX((double)x);
						v.getImage().setY((double)y);
						v.getImage().setRotate(angle + 90);
						v.getLabelPseudo().setLayoutX(x + 50);
						v.getLabelPseudo().setLayoutY(y);
						v.getLabelPseudo().setTextFill(this.couleurPickerPseudo.getValue());
						if(pseudo.equals(Controleur_PageAccueil.PSEUDONYME)) { //SI C'EST LE JOUEUR
							this.vitesseAffichee.setText("" + (vitesse * Controleur_PageJeu.FACTEUR_VITESSE) + " km/h");
							v.getLabelPseudo().setVisible(this.toggleAfficherPseudoJoueurs.isSelected());
						}
						else { //SI C'EST UN ENNEMI
							v.getLabelPseudo().setVisible(this.toggleAfficherAdversaires.isSelected() 
									&& this.toggleAfficherPseudoJoueurs.isSelected());
							v.getImage().setVisible(this.toggleAfficherAdversaires.isSelected());
						}
					}
				} catch (JSONException e) {
					//e.printStackTrace();
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private void traiterTouches() {
		//ACCELERER
		if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheAccelerer)) {
			//System.out.println("Avancer");
			if(!this.partieDemarree) { //Si c'est la première fois qu'on bouge on lance le chrono
				this.partieDemarree = true;
				Controleur_PageJeu.timerChrono.play();
			}
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "avancer", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheAccelerer);
		}
		//FREINER
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheFreiner)) {
			//System.out.println("Freiner");
			if(!this.partieDemarree) { //Si c'est la première fois qu'on bouge on lance le chrono
				this.partieDemarree = true;
				Controleur_PageJeu.timerChrono.play();
			}
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "reculer", "rien");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheFreiner);
		}
		//GAUCHE
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheTournerGauche)) {
			//System.out.println("Gauche");
			if(!this.partieDemarree) { //Si c'est la première fois qu'on bouge on lance le chrono
				this.partieDemarree = true;
				Controleur_PageJeu.timerChrono.play();
			}
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "gauche");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheTournerGauche);
		}
		//DROITE
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheTournerDroite)) {
			//System.out.println("Droite");
			if(!this.partieDemarree) { //Si c'est la première fois qu'on bouge on lance le chrono
				this.partieDemarree = true;
				Controleur_PageJeu.timerChrono.play();
			}
			try {
				UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "droite");
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheTournerDroite);
		}
		//RETOUR
		else if(this.listeTouchesPressees.contains(Controleur_PageChoixTouches.nomToucheRetour)) {
			//System.out.println("Droite");
			if(!this.partieDemarree) { //Si c'est la première fois qu'on bouge on lance le chrono
				this.partieDemarree = true;
				Controleur_PageJeu.timerChrono.play();
			}
			Teleporteur t = this.hashMapNumeroTeleporteurTeleporteur.get(this.numeroDernierTeleporteur);
			try {
				UtiliserWS.service_Teleporter(Controleur_PageAccueil.PSEUDONYME, t.getNewX(), t.getNewY(), t.getNewAngle());
				Controleur_PageJeu.mediaSonTeleportation = new Media(Controleur_PageJeu.CHEMIN_SON_TELEPORTATION);
				Controleur_PageJeu.mediaPlayerSonTeleportation = new MediaPlayer(mediaSonTeleportation);
				Controleur_PageJeu.mediaPlayerSonTeleportation.setCycleCount(1);
				Controleur_PageJeu.mediaPlayerSonTeleportation.play();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			this.listeTouchesPressees.remove(Controleur_PageChoixTouches.nomToucheRetour);
		}
		else { //SI ON APPUIE SUR RIEN : ROUE LIBRE
			this.amortissementDeceleration++;
			this.amortissementDeceleration %= 
					(Controleur_PageJeu.TAUX_AMORTISSEMENT_DECELERATION - Controleur_PageChoixVoiture.statistiqueVoiture.getDeceleration());
			if(this.amortissementDeceleration == 0) {
				try {
					UtiliserWS.service_Jouer(Controleur_PageAccueil.PSEUDONYME, "libre", "rien");
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		}
	}

	@FXML
	void quitterPartie(ActionEvent event) {
		Controleur_PageJeu.timerJeu.stop(); //Arrêt de la boucle de jeu et du chrono
		Controleur_PageJeu.timerChrono.stop();
		//ON FAIT LE JOUEUR QUITTER LE TERRAIN POUR MONTRER QU'IL A QUITTE LA PARTIE
		try {
			UtiliserWS.service_Teleporter(Controleur_PageAccueil.PSEUDONYME, Controleur_PageAccueil.COORDONNEES_X_APRES_CONNEXION, 
					Controleur_PageAccueil.COORDONNEES_Y_APRES_CONNEXION, 0);
		} catch (Exception e1) {
			//e1.printStackTrace();
		}
		Controleur_PageJeu.mediaSonQuitterPartie = new Media(Controleur_PageAccueil.CHEMIN_SON_BOUTON);
		Controleur_PageJeu.mediaPlayerSonQuitterPartie = new MediaPlayer(mediaSonQuitterPartie);
		Controleur_PageJeu.mediaPlayerSonQuitterPartie.setCycleCount(1);
		Controleur_PageJeu.mediaPlayerSonQuitterPartie.play();
		//RETOUR A L'ECRAN PRECEDENT
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
}























