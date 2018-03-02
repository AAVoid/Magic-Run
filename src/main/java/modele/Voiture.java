package modele;

import javafx.scene.image.ImageView;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class Voiture {
	private ImageView image;
	private StatistiquesVoiture statistiques;
	
	public Voiture(ImageView image, StatistiquesVoiture statistiques) {
		super();
		this.image = image;
		this.statistiques = statistiques;
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
}




















