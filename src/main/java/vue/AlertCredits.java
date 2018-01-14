package vue;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
*/



public class AlertCredits {
	private static final String TITRE = Main.NOM_APPLICATION + " - " + "CREDITS";
	
	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setResizable(false);
		window.setTitle(AlertCredits.TITRE);

		Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
        scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
		window.setScene(scene);
		window.showAndWait();
	}
}





















