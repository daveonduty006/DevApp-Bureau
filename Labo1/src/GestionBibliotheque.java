import java.util.*;

public class GestionBibliotheque {

	static final int TAILLE_MAX= 20;
	static final String FICHIER_TEXTE= "src/data/ouvrages.txt";
	static List<ArrayList<String>> listeAttributs;
	
	public static void main(String[] args) throws Exception {
		listeAttributs= (ArrayList<ArrayList<String>>) Utilitaires.chargerFichierTexte(FICHIER_TEXTE, ";");
		//BiblioTab biblioTab= new BiblioTab(listeAttributs, TAILLE_MAX);
		//BiblioList biblioList= new BiblioList(listeAttributs, TAILLE_MAX);
		
	}

}
