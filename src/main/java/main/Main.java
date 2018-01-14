package main;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String NOM_APPLLICATION = "Magic Run - Aymerik ABOSO";
	public static final int LONGUEUR_FENETRE = 600;
	public static final int HAUTEUR_FENETRE = 600;
	public static final String CHEMIN_FXML_PAGE_ACCUEIL = "/vue/pageAccueil.fxml";
	public static final String CHEMIN_FICHIER_CSS = "vue/ApplicationTheme.css";
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(Main.CHEMIN_FXML_PAGE_ACCUEIL));
        primaryStage.setTitle(Main.NOM_APPLLICATION);
        Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
        scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
	}
}























