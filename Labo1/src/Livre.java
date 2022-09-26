public class Livre extends Ouvrage {
	
	private String auteur, titre, editeur;
	//
	Livre(int num, Date dateEmprunt, String titre, String auteur, String editeur) {
		super(num, dateEmprunt);
		this.auteur= auteur;
		this.titre= titre;
		this.editeur= editeur;
	}
	//
	public String getAuteur() {
		return this.auteur;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public String getEditeur() {
		return this.editeur;
	}
	//
	public void setAuteur(String unAuteur) {
		this.auteur= unAuteur;
	}
	
	public void setTitre(String unTitre) {
		this.auteur= unTitre;
	}
	
	public void setEditeur(String unEditeur) {
		this.auteur= unEditeur;
	}
	//
	@Override
	public String toString() {
		String rep= super.toString();
		rep= rep+this.auteur+" "+this.titre+" "+this.editeur+" ";
		return rep;
	}
	
}
