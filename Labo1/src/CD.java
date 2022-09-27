public class CD extends Ouvrage {
	
	private String titre, auteur;
	//
	CD(int num, Date dateEmprunt, String titre, String auteur) {
		super(num, dateEmprunt);
		this.titre= titre;
		this.auteur= auteur;
	}
	//
	public String getTitre() {
		return this.titre;
	}
	
	public String getAuteur() {
		return this.auteur;
	}
	//
	public void setTitre(String unTitre) {
		this.titre= unTitre;
	}
	
	public void setAuteur(String unAuteur) {
		this.auteur= unAuteur;
	}
	//
	@Override
	public String toString() {
		String rep= super.toString();
		rep= rep+"Titre: "+this.titre+"\n"+"Auteur: "+this.auteur+"\n";
		return rep;
	}

}
