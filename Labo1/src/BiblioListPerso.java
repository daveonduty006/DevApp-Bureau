import java.util.*;

public class BiblioListPerso {
	
	
	
	//
	
	private int tailleMax;
	private int compteur; 
	private ListeChainee biblio;
	
	//
	
	BiblioListPerso(List<ArrayList<String>> listeAttributs, int taille) {
		this.biblio= new ListeChainee();
		this.compteur= 0;
		this.tailleMax= taille;
		chargerListe(listeAttributs);
	}
	
	//
	
	public ListeChainee getBiblio() {
		return this.biblio;
	}
	
	public int getTailleMax() {
		return this.tailleMax;
	}
	
	//
	
	
	
	//
	
	public String toString() {
		String rep= "Nombre d'ouvrages: "+this.compteur+"\n\n";
        Noeud pointeurTete= this.biblio.getTete(); 
        while(pointeurTete != null) {
            rep+= pointeurTete.getData().toString()+"\n";
            pointeurTete= pointeurTete.getSuiv();
        }
        return rep;
	}
	
	//
	
	private void chargerListe(List<ArrayList<String>> listeAttributs) {
		listeAttributs.forEach((donneesOuvrage) -> {
			char type= donneesOuvrage.get(0).charAt(0);
			int num= Integer.parseInt(donneesOuvrage.get(1));
			Date dateEmprunt= null;
			if(!"null".equals(donneesOuvrage.get(2))) {
				String elemsDate[]= new String[3];
			    elemsDate= donneesOuvrage.get(2).split(" ");
			    dateEmprunt= new Date(
			    			 Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
			                 Integer.parseInt(elemsDate[2]));			    
			}
			String titre= donneesOuvrage.get(3);
			if(type == 'L') {
				String auteur= donneesOuvrage.get(4);
				String editeur= donneesOuvrage.get(5);
				Livre unLivre= new Livre(num,dateEmprunt,titre,auteur,editeur);
				this.biblio.ajouter(unLivre);
			}else if(type == 'P') {
				int numSerie= Integer.parseInt(donneesOuvrage.get(4));
				int periodicite= Integer.parseInt(donneesOuvrage.get(5));
				Periodique unPeriodique= new Periodique(num,dateEmprunt,titre,numSerie,periodicite);
				this.biblio.ajouter(unPeriodique);
			}else if(type == 'C') {
				String auteur= donneesOuvrage.get(4);
				CD unCD= new CD(num,dateEmprunt,titre,auteur);
				this.biblio.ajouter(unCD);
			}
			this.compteur++;
		});
	}
	
	
	

}
