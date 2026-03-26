package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println("JEU:\n" + jeu.affichageJeuDeCartes());
		
		// Test methode checkCount()
		System.out.println("Début du test du paquet");
		 
		
		if(jeu.checkCount()) {
			System.out.println("Ok : Le paquet a bien toute les cartes et les quantités correspondantes");
		}
		
		else if(jeu.checkCount()) {
			System.out.println("Erreur : pb dans le paquet");
		}

	}
}