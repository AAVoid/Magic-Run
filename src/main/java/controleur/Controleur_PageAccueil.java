package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import main.Main;



/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
*/



public class Controleur_PageAccueil implements Initializable {
	private static final int NB_CHAR_MAX_PSEUDO = 50; //Champ pseudo peut avoir 50 caractères max
	public static final String CHEMIN_FXML_PAGE_CREDITS = "/vue/pageCredits.fxml";
	@FXML
    private JFXTextField champPseudo;
	@FXML
    private JFXButton boutonSeConnecter;
	@FXML
    private JFXButton boutonCredits;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//System.out.println("Chargé");
		
		//ECOUTEUR SUR LE BOUTON POUR LE DESACTIVER SI CHAMP PSEUDO VIDE
		champPseudo.textProperty().addListener((observable, oldValue, newValue) -> {
			boutonSeConnecter.setDisable(newValue.trim().isEmpty());
			
			//LIMITE LE NOMBRE DE CARACTERES DU TEXT FIELD PSEUDO
			if(newValue.length() >= Controleur_PageAccueil.NB_CHAR_MAX_PSEUDO + 1)
				champPseudo.setText(oldValue); //On efface le caractère
		});
	}

    @FXML
    void seConnecter(ActionEvent event) {
    	//System.out.println("CONNECTE !");
    	String NOM_FENETRE = "Serveur de jeu";
    	String TEXTE_EXPLICATIONS = "Bonjour " + champPseudo.getText() + 
    			" !\nVeuillez saisir l'adresse du serveur de jeu.\n\n\nExemple : \"" + "lol" + "\"";
    	String TEXTE_LABEL_INPUT = "Adresse serveur";
    	
    	TextInputDialog dialog = new TextInputDialog("");
    	dialog.setTitle(NOM_FENETRE);
    	dialog.setHeaderText(TEXTE_EXPLICATIONS);
    	dialog.setContentText(TEXTE_LABEL_INPUT);

    	Optional<String> adresseServeur = dialog.showAndWait();
    	if (adresseServeur.isPresent()){
    	    //System.out.println("Adresse : " + adresseServeur.get());
    		//TEST DE CREATION DE NOUVEAU JOUEUR
    	}
    }
    
    @FXML
    void afficherCredits(ActionEvent event) {
    	//System.out.println("Afficher crédits !");
    	try {
			Parent root = FXMLLoader.load(getClass().getResource(Controleur_PageAccueil.CHEMIN_FXML_PAGE_CREDITS));
			Scene scene = new Scene(root, Main.LONGUEUR_FENETRE, Main.HAUTEUR_FENETRE);
	        scene.getStylesheets().add(Main.CHEMIN_FICHIER_CSS);
	        Main.windowStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}


















