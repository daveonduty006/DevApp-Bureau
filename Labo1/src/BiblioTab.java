import java.io.*;
import java.util.*;
import javax.swing.*;

public class BiblioTab extends Bibliotheque {
	
	static final int NB_OUVRAGES= 20;
	static final String FICHIER_TEXTE= "src/data/ouvrages.txt";
	static int compteur;  
    static BufferedReader clavier;
	static JTextArea sortie;
	static Ouvrage biblio[];
	
	BiblioTab() throws Exception {
		biblio= new Ouvrage[NB_OUVRAGES];
        clavier= new BufferedReader(new InputStreamReader(System.in));
        chargerOuvrages();
	}
	
	//
	
	private void chargerOuvrages() throws Exception {
		compteur= 0;
		ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_TEXTE, ";");
		listeAttributs.forEach((donneesOuvrage) -> {
			char type= donneesOuvrage.get(0).charAt(0);
			if(type == 'L') {
				int num= Integer.parseInt(donneesOuvrage.get(1));
				String titre= donneesOuvrage.get(3);
				String auteur= donneesOuvrage.get(4);
				String editeur= donneesOuvrage.get(5);
				Date dateEmprunt= null;
				if(donneesOuvrage.get(2) != null) {
					String elemsDate[]= new String[3];
				    elemsDate= donneesOuvrage.get(2).split(" ");
				    dateEmprunt= new Date(
				    			 Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
				                 Integer.parseInt(elemsDate[2]));			    
				}
				Livre unLivre= new Livre(num,dateEmprunt,titre,auteur,editeur);
				biblio[compteur]= unLivre;
			}else if(type == 'P') {
				
			}
			compteur++;
		});
	}

}
