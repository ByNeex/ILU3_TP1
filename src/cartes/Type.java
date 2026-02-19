package cartes;

public enum Type {
	FEU ("Feu rouge", "Feu vert", "Prioritaire"),
	ESSENCE ("Panne d'essence", "Bidon d'essence", "Citerne"),
	CREVAISON ("Crevaison", "Roue de secours", "Increvable"),
	ACCIDENT ("Accident", "Réparation", "As du volant");
	
	private String nomAttaque;
	private String nomParade;
	private String nomBotte;
	
	Type(String nomAttaque, String nomParade, String nomBotte) {
		this.nomAttaque = nomAttaque;
		this.nomParade = nomParade;
		this.nomBotte = nomBotte;
	}

	public String getNomAttaque() {
		return nomAttaque;
	}

	public String getNomParade() {
		return nomParade;
	}

	public String getNomBotte() {
		return nomBotte;
	}
	

}
