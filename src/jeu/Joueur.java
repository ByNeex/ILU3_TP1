package jeu;

import cartes.*;

public class Joueur {
	private ZoneDeJeu zoneJoueur = new ZoneDeJeu();
	private String nom;
	private MainJoueur main = new MainJoueur();
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	
	public String getNom() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		
		Carte fstCarte = sabot.piocher();
		this.donner(fstCarte);
		return fstCarte;
		
	}
	
	public int donnerKmParcourus() {
		return zoneJoueur.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zoneJoueur.deposer(c);
	}


	@Override
	public String toString() {
		return (" Le joueur s'appelle : " + nom);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur) {
			return nom.equals(joueur.getNom());
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneJoueur.estDepotAutorise(carte);
	}
	
	
}
