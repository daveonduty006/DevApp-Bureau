public class Periodique extends Ouvrage {
	
	private String titre;
	private int numSerie, periodicite;
	//
	Periodique(int num, Date dateEmprunt, String titre, int numSerie, int periodicite) {
		super(num, dateEmprunt);
		this.titre= titre;
		this.numSerie= numSerie;
		this.periodicite= periodicite;
	}
	//
	public String getTitre() {
		return this.titre;
	}
	
	public int getNumSerie() {
		return this.numSerie;
	}
	
	public int getPeriodicite() {
		return this.periodicite;
	}
	//
	public void setTitre(String unTitre) {
		this.titre= unTitre;
	}
	
	public void setNumSerie(int unNumSerie) {
		this.numSerie= unNumSerie;
	}
	
	public void setPeriodicite(int unePeriodicite) {
		this.periodicite= unePeriodicite;
	}
	//
	@Override
	public String toString() {
		String rep= super.toString();
		rep= rep+"Titre: "+this.titre+"\n"+"Numero: "+this.numSerie+"\n"+"Periodicite: "+this.periodicite+" semaine(s)\n";
		return rep;
	}

}
