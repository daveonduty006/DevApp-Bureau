
public class Professeur {
	
	public static int nbProfesseurs= 0;
	
	private String nom;
	private int tacheAutomne;
	private int tacheHiver;
	private int position;
	
	Professeur(){}
	
	Professeur(String nom, int tacheAutomne, int tacheHiver, int position) {
		this.nom = nom;
		setTacheAutomne(tacheAutomne);
		setTacheHiver(tacheHiver);
		this.position = position;
		nbProfesseurs++;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getTacheAutomne() {
		return this.tacheAutomne;
	}
	
	public int getTacheHiver() {
		return this.tacheHiver;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void setNom(String unNom) {
		this.nom = unNom;
	}
	
	public void setTacheAutomne(int uneTache) {
		if(uneTache >= 0 && uneTache <= 50) {
			this.tacheAutomne = uneTache;
		}else {
			this.tacheAutomne = 0;
		}
	}
	
	public void setTacheHiver(int uneTache) {
		if(uneTache >= 0 && uneTache <= 50) {
			this.tacheHiver = uneTache;
		}else {
			this.tacheHiver = 0;
		}
	}
	
	public void setPosition(int unePosition) {
		this.position = unePosition;
	}
	
	public int calculerTache() {
		return this.tacheAutomne + this.tacheHiver;
	}
	
	public String message() {
		int tacheAnnuelle = this.tacheAutomne + this.tacheHiver;
		if(tacheAnnuelle >= 88) {
			return "plein";
		}else {
			return "partiel";
		}
	}
	
	public String toString() {
		return "nom: "+this.nom+", tacheAutomne: "+this.tacheAutomne+", tacheHiver: "+this.tacheHiver+", position: "+this.position;
	}
	
	

}
