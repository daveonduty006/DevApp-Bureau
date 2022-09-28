import java.io.*;
import java.util.*;

public class GestionBibliotheque {

	static final String FICHIER_TEXTE= "src/data/ouvrages.txt";
	static List<ArrayList<String>> listeAttributs;
    static BufferedReader clavier;
	
	public static void main(String[] args) throws Exception {
		listeAttributs= (ArrayList<ArrayList<String>>) Utilitaires.chargerFichierTexte(FICHIER_TEXTE, ";");
		BiblioTab biblioTab= new BiblioTab(listeAttributs);
		biblioTab.ajouterOuvrage();
		System.out.println(biblioTab.toString());
		biblioTab.supprimerOuvrage();
		System.out.println(biblioTab.toString());
	}

}
