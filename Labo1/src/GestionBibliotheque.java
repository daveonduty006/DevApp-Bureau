import java.util.*;
import javax.swing.*;

public class GestionBibliotheque {

	static final int TAILLE_MAX= 20;
	static final String FICHIER_TEXTE= "src/data/ouvrages.txt";
	static List<ArrayList<String>> listeAttributs;
	
	public static void main(String[] args) throws Exception {
		listeAttributs= (ArrayList<ArrayList<String>>) Utilitaires.chargerFichierTexte(FICHIER_TEXTE, ";");
		//BiblioTab biblioTab= new BiblioTab(listeAttributs, TAILLE_MAX);
		//BiblioList biblioList= new BiblioList(listeAttributs, TAILLE_MAX);
		//BiblioListPerso biblioListPerso= new BiblioListPerso(listeAttributs, TAILLE_MAX);
		String choixStructure, choixTest;
		do {
			String msg= "1. Instancier la bibliotheque sous forme de BiblioTab\n"+
		                "2. Instancier la bibliotheque sous forme de BiblioList\n"+
					    "3. Instancier la bibliotheque sous forme de BiblioListPerso\n"+
		                "4. Quitter\n\n";
			choixStructure= JOptionPane.showInputDialog(null, msg, "MENU PRINCIPAL", JOptionPane.PLAIN_MESSAGE);
			switch(choixStructure) {
				case "1":
					BiblioTab biblioTab= new BiblioTab(listeAttributs, TAILLE_MAX);
					do {
						msg= "1. Afficher tous les ouvrages\n"+
					         "2. Afficher un ouvrage en particulier\n"+
						     "3. Ajouter un nouvel ouvrage\n"+
					         "4. Supprimer un ouvrage existant\n"+
						     "5. Quitter\n\n";
						choixTest= JOptionPane.showInputDialog(null, msg, "MENU DES TESTS", JOptionPane.PLAIN_MESSAGE);
						switch(choixTest) {
							case "1":
								System.out.println(biblioTab);
								break;
							case "2":
								int num= Integer.parseInt(JOptionPane.showInputDialog("Entrez le numero de l'ouvrage recherche: "));
								System.out.println(biblioTab.rechercherOuvrage(num));
								break;
							case "3":
								biblioTab.ajouterOuvrage();
								break;
							case "4":
								biblioTab.supprimerOuvrage();
								break;
							case "5":
								break;
						}
					}while(!"5".equals(choixTest));
					break;
				case "2":
					BiblioList biblioList= new BiblioList(listeAttributs, TAILLE_MAX);
					do {
						msg= "1. Afficher tous les ouvrages\n"+
					         "2. Afficher un ouvrage en particulier\n"+
						     "3. Ajouter un nouvel ouvrage\n"+
					         "4. Supprimer un ouvrage existant\n"+
						     "5. Quitter\n\n";
						choixTest= JOptionPane.showInputDialog(null, msg, "MENU DES TESTS", JOptionPane.PLAIN_MESSAGE);
						switch(choixTest) {
							case "1":
								System.out.println(biblioList);
								break;
							case "2":
								int num= Integer.parseInt(JOptionPane.showInputDialog("Entrez le numero de l'ouvrage recherche: "));
								System.out.println(biblioList.rechercherOuvrage(num));
								break;
							case "3":
								biblioList.ajouterOuvrage();
								break;
							case "4":
								biblioList.supprimerOuvrage();
								break;
							case "5":
								break;
						}
					}while(!"5".equals(choixTest));
					break;
				case "3":
					BiblioListPerso biblioListPerso= new BiblioListPerso(listeAttributs, TAILLE_MAX);
					do {
						msg= "1. Afficher tous les ouvrages\n"+
					         "2. Afficher un ouvrage en particulier\n"+
						     "3. Ajouter un nouvel ouvrage\n"+
					         "4. Supprimer un ouvrage existant\n"+
						     "5. Quitter\n\n";
						choixTest= JOptionPane.showInputDialog(null, msg, "MENU DES TESTS", JOptionPane.PLAIN_MESSAGE);
						switch(choixTest) {
							case "1":
								System.out.println(biblioListPerso);
								break;
							case "2":
								int num= Integer.parseInt(JOptionPane.showInputDialog("Entrez le numero de l'ouvrage recherche: "));
								System.out.println(biblioListPerso.rechercherOuvrage(num));
								break;
							case "3":
								biblioListPerso.ajouterOuvrage();
								break;
							case "4":
								biblioListPerso.supprimerOuvrage();
								break;
							case "5":
								break;
						}
					}while(!"5".equals(choixTest));
					break;
				case "4":
					break;
			}
		}while(!"4".equals(choixStructure));
		System.exit(0);
	}

}
