package com.ipiecoles.java.java210;

public class Main {

	private String message;
	private int compteur=0;
	
	public static void main(String[] args) {
		Main first = new Main();
		System.out.println(first.getMessage());
		first.setMessage("Hello New World!");
		System.out.println(first.getMessage() +" /Compteur: "+ first.getCompteur());
	}
	
	public Main() {
		setMessage("Hello World!");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.setCompteur(this.getCompteur()+1);
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
}
