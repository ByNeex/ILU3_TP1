

package jeu;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<>();
	private List<Bataille> batailles = new ArrayList<>();
	private Collection<Borne> bornes = new ArrayList<>();
	
	
	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || limites.getLast() instanceof FinLimite) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int kmCount = 0;
		
		// on parcourt la collection de borne déposer dans la zone
		for(Borne borne : bornes) {
			kmCount = kmCount + borne.getKm();
		}
		return kmCount;
		
	}
	
	public void deposer(Carte c) {
		if(c instanceof Borne borne) {
			bornes.add(borne);
		} else if (c instanceof Limite limite ){
			limites.add(limite);		
		} else if (c instanceof FinLimite finLimite){
			limites.add(finLimite);
		} else if (c instanceof Bataille bataille) {
			batailles.add(bataille);	
		}
	}
	
	private boolean estDepotFeuVertAutorise() {
		if(batailles.isEmpty() || 
				(batailles.getLast().equals(Cartes.FEU_ROUGE)) || 
				(batailles.getLast() instanceof Parade && !batailles.getLast().equals(Cartes.FEU_VERT))){
			return true;
		}
		return false;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		if (!this.peutAvancer()) {
			return false;
		}
		
		if (borne.getKm() > donnerLimitationVitesse()) {
			return false;
		}
		
		
		if (donnerKmParcourus() + borne.getKm() > 1000) {
			return false;
		}
		
		return true;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(limite instanceof DebutLimite) {
			if(limites.isEmpty() || limites.getLast() instanceof FinLimite) {
				return true;
			}
		}
		
		if(limite instanceof FinLimite) {
			if(!limites.isEmpty() && limites.getLast() instanceof DebutLimite){
				return true;
			}
		}
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque && this.peutAvancer() ) {
			return true;
		}
		
		if (bataille instanceof Parade) {
			
			// cas a)
			if(bataille.equals(Cartes.FEU_VERT)) {
				return this.estDepotFeuVertAutorise();
			}
			
			// cas b)
			if(!batailles.isEmpty()) {
				if(batailles.getLast() instanceof Attaque && bataille.getType().equals(batailles.getLast().getType())) {
					return true;
				}
			}
		}
		
		return false;

	}
	
	
	// Les méthode d'autorisation publique
	public boolean peutAvancer() {
		if(!batailles.isEmpty() && batailles.getLast().equals(Cartes.FEU_VERT)) {
			return true;
		}
		
		return false;
	}
	

	public boolean estDepotAutorise(Carte carte) {
		
		if (carte instanceof Borne) {
			return estDepotBorneAutorise((Borne) carte);
			
		} else if (carte instanceof Limite) {
			return estDepotLimiteAutorise((Limite) carte);
			
		} else if (carte instanceof Bataille) {
			return estDepotBatailleAutorise((Bataille) carte);
			
		} else if (carte instanceof Botte) {
			return true; 
		}
		return false;
	}
	

}
