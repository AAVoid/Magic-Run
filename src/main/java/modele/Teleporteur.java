package modele;

import java.awt.Rectangle;

/*
ATEUR : Aymerik ABOSO
ANNEE : 2018
*/



public class Teleporteur {
	private Rectangle rectangle;
	private int newX;
	private int newY;
	private int newAngle;
	
	public Teleporteur(Rectangle rectangle, int newX, int newY, int newAngle) {
		super();
		this.rectangle = rectangle;
		this.newX = newX;
		this.newY = newY;
		this.newAngle = newAngle;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public int getNewX() {
		return newX;
	}

	public void setNewX(int newX) {
		this.newX = newX;
	}

	public int getNewY() {
		return newY;
	}

	public void setNewY(int newY) {
		this.newY = newY;
	}

	public int getNewAngle() {
		return newAngle;
	}

	public void setNewAngle(int newAngle) {
		this.newAngle = newAngle;
	}
}
























