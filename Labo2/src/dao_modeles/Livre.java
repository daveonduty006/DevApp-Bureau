package dao_modeles;

public class Livre {
	
	private int idf;
	private String titre;
	private int numAuteur;
	private int annee;
	private int pages;
	private String categ;
	
	public int getIdf() {
		return idf;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public int getNumAuteur() {
		return numAuteur;
	}
	
	public int getAnnee() {
		return annee;
	}
	
	public int getPages() {
		return pages;
	}
	
	public String getCateg() {
		return categ;
	}
	
	public void setIdf(int unIdf) {
		this.idf= unIdf;
	}
	
	public void setTitre(String unTitre) {
		this.titre= unTitre;
	}
	
	public void setNumAuteur(int unNumAuteur) {
		this.numAuteur= unNumAuteur;
	}
	
	public void setAnnee(int uneAnnee) {
		this.annee= uneAnnee;
	}
	
	public void setPages(int unNbPages) {
		this.pages= unNbPages;
	}
	
	public void setCateg(String uneCateg) {
		this.categ= uneCateg;
	}

}
