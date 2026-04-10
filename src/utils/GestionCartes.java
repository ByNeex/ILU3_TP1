package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	// a)
	
	// Version 1
	public static <T> T extraireV1(List<T> lst) {
		Random random = new Random();
		int indiceAlea = random.nextInt(lst.size());
		
		// on recup l'elt grâce au remove car en remove il renvoie l'elt
		T elt = lst.remove(indiceAlea);
		return elt;
		
	}
	
	// Version 2
	public static <T> T extraireV2(List<T> lst) {
		Random random = new Random();
		int indiceAlea = random.nextInt(lst.size());
		ListIterator<T> iterateur = lst.listIterator();
		T elt = null;
		
		// on fait avancer l'iterateur jusqu'a l'indice qu'on a choisi
		for (int i = 0; i <= indiceAlea; i++) {
            elt = iterateur.next();
        }
		
		iterateur.remove();
		
		return elt;
	}
	

	// b) 
	// lst doit etre vide (normalement)
	public static <T> List<T> melanger(List<T> lst) {
		List<T> lstMelange = new ArrayList<>();
		while(!lst.isEmpty()) {
			lstMelange.add(extraireV1(lst));
		}
		return lstMelange;
	}
	
	
	// c) 
	public static <T> boolean verifierMelange(List<T> lst1, List<T> lst2) {
		ListIterator<T> iterateur = lst1.listIterator();
		
		while (iterateur.hasNext()) {
			T elt = iterateur.next();
			int colecL1 = Collections.frequency(lst1, elt);
			int colecL2 = Collections.frequency(lst2, elt);
			if(colecL1 != colecL2) {
				return false;
			}
		}
		return true;
	}
	
	
	
	// d) 
	//public static <T> List<T> rassembler(List<T> lst){
		//List<T> lstConsec = new ArrayList<>();
		//for (ListIterator<T> iterateur = lstConsec.listIterator(); iterateur.hasNext();) {
			//T elt = iterateur.next();
			
			//if (!lstConsec.contains(elt)) {
				//for (int i = 0; i < Collections.frequency(lst, elt); i++) {
					//lstConsec.add(elt);
				//}
			//}
			
		//}
		//return lstConsec;
	//}
	
	public static <T> List<T> rassembler(List<T> lst) {
		List<T> lstConsec = new ArrayList<>();
		for (T elt : lst) {
			if (!lstConsec.contains(elt)) {
				int nbOccurrences = Collections.frequency(lst, elt);
				for (int i = 0; i < nbOccurrences; i++) {
					lstConsec.add(elt);
				}
			}
		}
		return lstConsec;
	}
	
	// e)
	
	public static <T> boolean verifierRassemblement(List<T> lst) {
		
		// ajout du cas de base 
		if (lst.isEmpty()) {
            return true;
        }
		
		ListIterator<T> it1 = lst.listIterator();
		
		T prevElt = it1.next();
		
		while(it1.hasNext()) {
			
			
			T eltCourant = it1.next();
			
			// vérification rupture
			if (!eltCourant.equals(prevElt)) {
				
				// si tel est le cas alors on lance le 2ème itérateur à partir de la position de it1
				ListIterator<T> it2 = lst.listIterator(it1.nextIndex());
				
				// on boucle le reste de la meme façon mais avec it2
				while(it2.hasNext()) {
					T eltRestants = it2.next();
					if (eltRestants.equals(prevElt)) {
                        return false; 
					}
				}
			}
			
			prevElt = eltCourant;
		}
		return true;	
	}
	
	
	
	
	
}
