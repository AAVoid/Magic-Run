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
	public static final int ACCELERATION_U = 5;
	private int rotation;
	public static final int ROTATION_D = 3;
	public static final int ROTATION_N = 4;
	public static final int ROTATION_U = 5;
	private int deceleration;
	public static final int DECELERATION_D = 3;
	public static final int DECELERATION_N = 4;
	public static final int DECELERATION_U = 5;
	private int frein;
	public static final int FREIN_D = 3;
	public static final int FREIN_N = 4;
	public static final int FREIN_U = 5;
	private int vitesseMax;
	public static final int VITESSE_MAX_D = 10;
	public static final int VITESSE_MAX_N = 13;
	public static final int VITESSE_MAX_U = 16;
	
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

	public static int getAccelerationD() {
		return ACCELERATION_D;
	}

	public static int getAccelerationN() {
		return ACCELERATION_N;
	}

	public static int getAccelerationU() {
		return ACCELERATION_U;
	}

	public static int getRotationD() {
		return ROTATION_D;
	}

	public static int getRotationN() {
		return ROTATION_N;
	}

	public static int getRotationU() {
		return ROTATION_U;
	}

	public static int getDecelerationD() {
		return DECELERATION_D;
	}

	public static int getDecelerationN() {
		return DECELERATION_N;
	}

	public static int getDecelerationU() {
		return DECELERATION_U;
	}

	public static int getFreinD() {
		return FREIN_D;
	}

	public static int getFreinN() {
		return FREIN_N;
	}

	public static int getFreinU() {
		return FREIN_U;
	}

	public static int getVitesseMaxD() {
		return VITESSE_MAX_D;
	}

	public static int getVitesseMaxN() {
		return VITESSE_MAX_N;
	}

	public static int getVitesseMaxU() {
		return VITESSE_MAX_U;
	}
}


























