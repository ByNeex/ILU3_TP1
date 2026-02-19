package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	
	private Carte[] cartesTab;
	private int nbCartes; // 106 cartes initialement dans le jeu
	private int nbOperation = 0; // pour l'itérateur
	
	
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartesTab.length) {
            throw new IndexOutOfBoundsException("Dépassement de capacité.");
        }
		
		cartesTab[nbCartes] = carte;
		nbCartes ++;
		nbOperation++;
	}
	
	public Carte piocher() {
		Iterator<Carte> iterateur = iterator();
		if (!iterateur.hasNext()) {
            throw new NoSuchElementException("Sabot vide.");
        }
		
		Carte carte = iterateur.next();
		iterateur.remove();
		
		return carte;
	}
	
	
	public Sabot(Carte[] cartesTab) {
		this.cartesTab = cartesTab;
		this.nbCartes = cartesTab.length;
	}
	
	
	// Implémentation itérateur

	@Override
	public Iterator<Carte> iterator() {
		return new IteratorSabot();
	}
	
	private class IteratorSabot implements Iterator<Carte>{
		private int indiceIterator = 0;
		private int nbOperationRef = nbOperation;
		private boolean suivant = false;
		
		
		@Override
		public boolean hasNext() {
			return indiceIterator < nbCartes;
		}
		
		@Override
		public Carte next() {
			verificationConcu();
			if(hasNext()) {
				Carte carte = cartesTab[indiceIterator];
				indiceIterator ++;
				suivant = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove(){
			verificationConcu();
			if(nbCartes < 1 || !suivant) {
				throw new IllegalStateException();
			}
			
			for (int i = indiceIterator -1; i < nbCartes -1; i++) {
				cartesTab[i] = cartesTab[i+1];
			}
			suivant =false;
			indiceIterator --;
			nbCartes --;
			
			nbOperation ++;
			nbOperationRef ++;
		}
		
		private void verificationConcu() {
			if(nbOperation != nbOperationRef) {
				throw new ConcurrentModificationException();
			}
		}
		
	}

}
