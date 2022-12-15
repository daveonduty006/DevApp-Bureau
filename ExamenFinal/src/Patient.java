
public class Patient {
	
	public static int nbPatients= 0;
	
	private int idp, fumeur;
	private String nom, prenom, daten, sexe, adresse, cp;
	
	Patient(){}
	
	Patient(int idp,String nom,String prenom,String daten,String sexe,String adresse,String cp,int fumeur) {
		this.idp = idp;
		this.nom = nom;
		this.prenom = prenom;
		this.daten = daten;
		this.sexe = sexe;
		this.adresse = adresse;
		this.cp = cp;
		this.fumeur = fumeur;
		nbPatients++;
	}

	public int getIdp() {
		return idp;
	}

	public void setIdp(int idp) {
		this.idp = idp;
	}

	public int getFumeur() {
		return fumeur;
	}

	public void setFumeur(int fumeur) {
		this.fumeur = fumeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDaten() {
		return daten;
	}

	public void setDaten(String daten) {
		this.daten = daten;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

}