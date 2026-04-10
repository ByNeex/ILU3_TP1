package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	
	
	public int getKm() {
		return km;
	}



	@Override
	public String toString() {
		return km + "KM";
	}
	
	// Ajout du TP2
	@Override
	public boolean equals(Object obj) {
		// j'ai tester avec le cast au préalable dans une autre variable
		// mais ça causait un "NullPointer"
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		return super.equals(obj) && this.km == ((Borne) obj).km;
	}

}
