package main;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String NOM_APPLICATION = "Magic Run";
	public static final String NOM_AUTEUR = "Aymerik ABOSO";
	public static final String NOM_APPLICATION_FENETRE = Main.NOM_APPLICATION + " - " + Main.NOM_AUTEUR;
	public static final String DATE_DEVELOPPEMENT = "2018";
	public static final String EMAIL_DEVELOPPEUR = "abosoaymerik@gmail.com";
	public static final String VERSION_DEVELOPPEMENT = "1.0";
	public static final int LONGUEUR_FENETRE = 600;
	public static final int HAUTEUR_FENETRE = 600;
	public static final String CHEMIN_FXML_PAGE_ACCUEIL = "/vue/pageAccueil.fxml";
	public static final String CHEMIN_FICHIER_CSS = "vue/ApplicationTheme.css";
	
	//RESSOURCES : A AJUSTER MANUELLEMENT DANS LES FXML
	public static final String CHEMIN_RESSOURCE = System.getProperty("user.dir").replace("\\", "/") + "/Ressource";
	//On travail à partir de la position absolue de l'application pour éviter des soucis...
	public static final String CHEMIN_ICONE = Main.CHEMIN_RESSOURCE + "/Icone";
	public static final String CHEMIN_IMAGE = Main.CHEMIN_RESSOURCE + "/Image";
	public static final String CHEMIN_MUSIQUE = Main.CHEMIN_RESSOURCE + "/Musique";
	public static final String CHEMIN_SON = Main.CHEMIN_RESSOURCE + "/Son";
	
	public static Stage windowStage; //Stage pour y avoir accès partout
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.windowStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource(Main.CHEMIN_FXML_PAGE_ACCUEIL));
		Main.windowStage.setTitle(Main.NOM_APPLICATION_FENETRE);
		Image iconeApplication = new Image("file:/" + Main.CHEMIN_IMAGE + "/Formule1.png");
		Main.windowStage.getIcons().add(iconeApplication);
        Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
        scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
        Main.windowStage.setScene(scene);
        Main.windowStage.setResizable(false);
        Main.windowStage.show();
	}
}























