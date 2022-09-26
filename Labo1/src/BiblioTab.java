import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class BiblioTab extends Bibliotheque {
	
	static final int NB_OUVRAGES= 20;
	static final String FICHIER_TEXTE= "src/data/ouvrages.txt";
	static int compteur;
    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    static BufferedReader clavier;
	static JTextArea sortie;
	static Ouvrage biblio[];
	
	BiblioTab() {
		biblio= new Ouvrage[NB_OUVRAGES];
        clavier= new BufferedReader(new InputStreamReader(System.in));
        if(!fichierBinaire.exists()) {
            creerFichierBinaire();
        }
	}
	//
	private void creerFichierBinaire() {
		ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_TEXTE, ";");
		listeAttributs.forEach((donneesOuvrage) -> {
			char type= donneesOuvrage.get(0).charAt(0);
			if(type=='L') {
				int num= Integer.parseInt(donneesOuvrage.get(1));
				String titre= donneesOuvrage.get(3);
				String auteur= donneesOuvrage.get(4);
				String editeur= donneesOuvrage.get(5);
				if(donneesOuvrage.get(2) != null) {
					String elemsDate[]= new String[3];
				    elemsDate= donneesOuvrage.get(2).split(" ");
				    Date dateEmprunt= new Date(
				    			      Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
				    			      Integer.parseInt(elemsDate[2]));
				}
				biblio
			}
		});
        String elems[]= new String[4];
        int num, duree;
        String titre="", categ="";
        try{
            tmpReadTexte= new BufferedReader(new FileReader("src/films.txt"));
            tmpWriteBin= new RandomAccessFile("src/films.bin", "rw");
            String ligne= tmpReadTexte.readLine();
            while(ligne != null ) {
                elems= ligne.split(";");
                num= Integer.parseInt(elems[0]);
                // Ceci fait la même chose que notre fonction formaterString()
                // String.format("%-20.20s",elems[1]);//20.20 min et max
                // String.format("%-12.12s",elems[2]);
                titre= formaterString(elems[1],20);
                categ= formaterString(elems[2],12);
                duree= Integer.parseInt(elems[3]);
                //System.out.println(tmpWriteBin.getFilePointer());
                tmpWriteBin.writeInt(num);
                tmpWriteBin.writeUTF(titre);
                tmpWriteBin.writeUTF(categ);
                tmpWriteBin.writeInt(duree);
                ligne= tmpReadTexte.readLine();
            }
            tmpReadTexte.close();
            tmpWriteBin.close();
        }catch(Exception e) {
        	System.out.println("Gros probleme! "+e.getMessage());
        }
	}

}
