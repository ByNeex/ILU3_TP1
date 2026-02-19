package cartes;

public class JeuDeCartes {
	
	private Configuration[] typesDeCartes;
	
	
	public Carte[] donnerCartes() {
		
		// calcul de la taille totale du paquet de carte - Normalement 106
		int tailleTotale = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			tailleTotale += typesDeCartes[i].getNbExemplaires();
		}
		 
		Configuration[] typeC = typesDeCartes;
		Carte[] toutesCartes = new Carte[tailleTotale];
		
		
		// boucle de remplissage
		int posTabFinal = 0;
		for (int i = 0; i < typeC.length; i++) {
			for (int j = 0; j < typeC[i].getNbExemplaires(); j++) {
				toutesCartes[posTabFinal] = typeC[i].getCarte();
				posTabFinal ++;
			}
		}
		return toutesCartes;
		
	}
	
	
	public JeuDeCartes() {
		typesDeCartes = new Configuration[19]; // 19 cartes différentes
		
		// Initialisation du tableau
		
		// Borne
		typesDeCartes[0] = new Configuration(new Borne(25), 10);
		typesDeCartes[1] = new Configuration(new Borne(50), 10);
		typesDeCartes[2] = new Configuration(new Borne(75), 10);
		typesDeCartes[3] = new Configuration(new Borne(100), 12);
		typesDeCartes[4] = new Configuration(new Borne(200), 4);
		
		
		// Parade
		typesDeCartes[5] = new Configuration(new Parade(Type.FEU), 14);
		typesDeCartes[6] = new Configuration(new FinLimite(), 6);
		typesDeCartes[7] = new Configuration(new Parade(Type.ESSENCE), 6);
		typesDeCartes[8] = new Configuration(new Parade(Type.CREVAISON), 6);
		typesDeCartes[9] = new Configuration(new Parade(Type.ACCIDENT), 6);
		
		// Attaque
		typesDeCartes[10] = new Configuration(new Attaque(Type.FEU), 5);
		typesDeCartes[11] = new Configuration(new DebutLimite(), 4);
		typesDeCartes[12] = new Configuration(new Attaque(Type.ESSENCE), 3);
		typesDeCartes[13] = new Configuration(new Attaque(Type.CREVAISON), 3);
		typesDeCartes[14] = new Configuration(new Attaque(Type.ACCIDENT), 3);
		
		// Botte
		typesDeCartes[15] = new Configuration(new Botte(Type.FEU), 1);
		typesDeCartes[16] = new Configuration(new Botte(Type.ESSENCE), 1);
		typesDeCartes[17] = new Configuration(new Botte(Type.CREVAISON), 1);
		typesDeCartes[18] = new Configuration(new Botte(Type.ACCIDENT), 1);
		
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder affichageJeu = new StringBuilder();
		
		for (int i = 0; i < typesDeCartes.length; i++) {
			affichageJeu.append(typesDeCartes[i].getNbExemplaires() + " ");
			Carte recupCarte = typesDeCartes[i].getCarte(); // évite le "point point"
			affichageJeu.append(recupCarte.toString() + "\n");
			
		}
		return affichageJeu.toString();
	}

	// Classe interne
	private class Configuration{
		private int nbExemplaires;
		private Carte carte;

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
	}
	
	
	// Test avec main
	/*public static void main(String[] args) {
	    JeuDeCartes monJeu = new JeuDeCartes();
	    System.out.println(monJeu.affichageJeuDeCartes());
	}*/
	

}
