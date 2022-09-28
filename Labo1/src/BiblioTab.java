import java.io.*;
import java.util.*;
import javax.swing.*;

public class BiblioTab extends Bibliotheque {
	
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
		int num= compteur+1;
		Date dateEmprunt= null;
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		String titre= JOptionPane.showInputDialog(
                      null, "Entrez le titre du nouvel ouvrage: ", "Ajout d'un nouvel ouvrage",
                      JOptionPane.PLAIN_MESSAGE);
		titre= formatterStringMajuscules(titre);
		UIManager.put("OptionPane.cancelButtonText", "CD");
		UIManager.put("OptionPane.noButtonText", "Periodique");
		UIManager.put("OptionPane.yesButtonText", "Livre");
		int rep= JOptionPane.showConfirmDialog(
				 null, "Choisissez le type du nouvel ouvrage", "Ajout d'un nouvel ouvrage", 
				 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		switch(rep) {
			case JOptionPane.YES_OPTION:
				UIManager.put("OptionPane.cancelButtonText", "Annuler");
				String auteur= JOptionPane.showInputDialog(
	                           null, "Entrez le nom de l'auteur du livre: ", 
	                           "Ajout d'un nouvel ouvrage", JOptionPane.PLAIN_MESSAGE);
				auteur= formatterStringMajuscules(auteur);
				String editeur= JOptionPane.showInputDialog(
                                null, "Entrez la maison d'edition du livre: ", 
                                "Ajout d'un nouvel ouvrage", JOptionPane.PLAIN_MESSAGE);
				editeur= formatterStringMajuscules(editeur);
				Livre unLivre= new Livre(num,dateEmprunt,titre,auteur,editeur);
				biblio[compteur]= unLivre;
				compteur++;
				
		}
		
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
	
	private String formatterStringMajuscules(String str) {
		String strFormatte= "";
		for(String mot : str.split(" ")) {
			String motSansMajuscules= "";
			for(char c : mot.toCharArray()) {
				if(Character.isLetter(c)) {
					motSansMajuscules+= Character.toLowerCase(c);
				}else {
					motSansMajuscules+= c;
				}
			}
			if(Character.isLetter(motSansMajuscules.charAt(0))){
				mot= motSansMajuscules.substring(0,1).toUpperCase() + motSansMajuscules.substring(1);
			}
			strFormatte+= mot+" ";
		}		
		return strFormatte;
	}
	
	/*
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}
	*/

}
