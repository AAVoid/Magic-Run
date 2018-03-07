package modele;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Voiture {
	private ImageView image;
	private StatistiquesVoiture statistiques;
	private Label labelPseudo;
	
	public Voiture(ImageView image, StatistiquesVoiture statistiques, Label labelPseudo) {
		super();
		this.image = image;
		this.statistiques = statistiques;
		this.labelPseudo = labelPseudo;
	}

	public ImageView getImage() {
		return image;
	}

	public void setImage(ImageView image) {
		this.image = image;
	}

	public StatistiquesVoiture getStatistiques() {
		return statistiques;
	}

	public void setStatistiques(StatistiquesVoiture statistiques) {
		this.statistiques = statistiques;
	}

	public Label getLabelPseudo() {
		return labelPseudo;
	}

	public void setLabelPseudo(Label labelPseudo) {
		this.labelPseudo = labelPseudo;
	}
}




















