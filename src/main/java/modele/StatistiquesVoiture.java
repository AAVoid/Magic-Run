package modele;


/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
 */


public class StatistiquesVoiture {
	private int x;
	private int y;
	private int angle;
	private int vitesse;
	private int acceleration;
	public static final int ACCELERATION_D = 3;
	public static final int ACCELERATION_N = 4;
	public static final int ACCELERATION_U = 7;
	private int rotation;
	public static final int ROTATION_D = 18;
	public static final int ROTATION_N = 30;
	public static final int ROTATION_U = 45;
	private int deceleration;
	public static final int DECELERATION_D = 3;
	public static final int DECELERATION_N = 2;
	public static final int DECELERATION_U = 1;
	private int frein;
	public static final int FREIN_D = 1;
	public static final int FREIN_N = 3;
	public static final int FREIN_U = 6;
	private int vitesseMax;
	public static final int VITESSE_MAX_D = 10;
	public static final int VITESSE_MAX_N = 30;
	public static final int VITESSE_MAX_U = 40;
	
	public StatistiquesVoiture(int x, int y, int angle, int vitesse, int acceleration, int rotation, int deceleration,
			int frein, int vitesseMax) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.vitesse = vitesse;
		this.acceleration = acceleration;
		this.rotation = rotation;
		this.deceleration = deceleration;
		this.frein = frein;
		this.vitesseMax = vitesseMax;
	}
	
	public StatistiquesVoiture() {
		this(0, 0, 0, 0, 0, 0, 0, 0, 0);
	}
	
	public static StatistiquesVoiture copier(StatistiquesVoiture statistiques) {
		StatistiquesVoiture s = new StatistiquesVoiture();
		s.x = statistiques.x;
		s.y = statistiques.y;
		s.angle = statistiques.angle;
		s.vitesse = statistiques.vitesse;
		s.acceleration = statistiques.acceleration;
		s.rotation = statistiques.rotation;
		s.deceleration = statistiques.deceleration;
		s.frein = statistiques.frein;
		s.vitesseMax = statistiques.vitesseMax;
		return s;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public int getDeceleration() {
		return deceleration;
	}

	public void setDeceleration(int deceleration) {
		this.deceleration = deceleration;
	}

	public int getFrein() {
		return frein;
	}

	public void setFrein(int frein) {
		this.frein = frein;
	}

	public int getVitesseMax() {
		return vitesseMax;
	}

	public void setVitesseMax(int vitesseMax) {
		this.vitesseMax = vitesseMax;
	}
}


























