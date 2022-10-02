public class Periodique extends Ouvrage {
	
	private String titre;
	private int publication, periodicite;
	//
	Periodique(int num, Date dateEmprunt, String titre, int numSerie, int periodicite) {
		super(num, dateEmprunt);
		this.titre= titre;
		this.publication= numSerie;
		this.periodicite= periodicite;
	}
	//
	public String getTitre() {
		return this.titre;
	}
	
	public int getNumSerie() {
		return this.publication;
	}
	
	public int getPeriodicite() {
		return this.periodicite;
	}
	//
	public void setTitre(String unTitre) {
		this.titre= unTitre;
	}
	
	public void setNumSerie(int unNumSerie) {
		this.publication= unNumSerie;
	}
	
	public void setPeriodicite(int unePeriodicite) {
		this.periodicite= unePeriodicite;
	}
	//
	@Override
	public String toString() {
		String rep= super.toString();
		rep= rep+"Titre: "+this.titre+"\n"+"Publication: numero "+this.publication+"\n"+"Periodicite: "+this.periodicite+" semaine(s)\n";
		return rep;
	}

}
