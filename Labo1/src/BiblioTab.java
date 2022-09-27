import java.io.*;
import java.util.*;

public class BiblioTab {
	
	static final int TAILLE_MAX= 20;
	static int compteur;  
    static BufferedReader clavier;
	static Ouvrage biblio[];
	
	//
	
	BiblioTab(List<ArrayList<String>> listeAttributs) {
        chargerTableau(listeAttributs);
	}
	
	//
	
	public void ajouterOuvrage() {
		
	}
	
	//
	
	@Override
	public String toString() {
		String rep= "Nombre d'ouvrages: "+compteur+"\n\n";
		for(Ouvrage unOuvrage : biblio) {
			if(unOuvrage != null) {
				rep += unOuvrage.toString()+"\n";
			}
		}
		return rep;
	}
	
	//
	
	private void chargerTableau(List<ArrayList<String>> listeAttributs) {
		biblio= new Ouvrage[TAILLE_MAX];
		compteur= 0;
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
				biblio[compteur]= unLivre;
			}else if(type == 'P') {
				int numSerie= Integer.parseInt(donneesOuvrage.get(4));
				int periodicite= Integer.parseInt(donneesOuvrage.get(5));
				Periodique unPeriodique= new Periodique(num,dateEmprunt,titre,numSerie,periodicite);
				biblio[compteur]= unPeriodique;
			}else if(type == 'C') {
				String auteur= donneesOuvrage.get(4);
				CD unCD= new CD(num,dateEmprunt,titre,auteur);
				biblio[compteur]= unCD;
			}
			compteur++;
		});
	}

}
