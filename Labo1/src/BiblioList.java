import java.util.*;
import javax.swing.*;

public class BiblioList extends Bibliotheque {
	
	static int tailleMax;
	static int compteur; 
	static List<Ouvrage> biblio;
	
	//
	
	BiblioList(List<ArrayList<String>> listeAttributs, int taille) {
		biblio= new LinkedList<>();
		tailleMax= taille;
		chargerListe(listeAttributs);
	}
	
	//
	
	public int rechercherOuvrage(int num) {
		int i= 0;
		int posTrouve= -1;
		while(i < biblio.size() && posTrouve == -1) {
			if(biblio.get(i) == null) {
				i++;
			}else if(biblio.get(i).num == num) {
				posTrouve= i;
			}else {
				i++;
			}
		}
		return posTrouve;
	}
	
	public void supprimerOuvrage() {
		String msg;
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		int num= Integer.parseInt(
				 JOptionPane.showInputDialog(
	             null, "Entrez le numero de l'ouvrage a supprimer: ", "Suppression d'un ouvrage",
                 JOptionPane.PLAIN_MESSAGE));
		int posOuvrage= rechercherOuvrage(num);
		if(posOuvrage != -1) {
			biblio.remove(posOuvrage);
			compteur--;
			msg= "Ouvrage supprime";
		}else {
			msg= "Ouvrage inexistant";
		}
		JOptionPane.showMessageDialog(
	    null, msg, "Suppression d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void ajouterOuvrage() {
		if(biblio.size() < tailleMax) {
			int num= compteur+1;
			Date dateEmprunt= null;
			UIManager.put("OptionPane.cancelButtonText", "Annuler");
			String titre= JOptionPane.showInputDialog(
	                      null, "Entrez le titre du nouvel ouvrage: ", "Ajout d'un ouvrage",
	                      JOptionPane.PLAIN_MESSAGE);
			titre= formatterStringMajuscules(titre);
			UIManager.put("OptionPane.cancelButtonText", "CD");
			UIManager.put("OptionPane.noButtonText", "Periodique");
			UIManager.put("OptionPane.yesButtonText", "Livre");
			int rep= JOptionPane.showConfirmDialog(
					 null, "Choisissez le type du nouvel ouvrage", "Ajout d'un ouvrage", 
					 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			UIManager.put("OptionPane.cancelButtonText", "Annuler");
			String auteur;
			switch(rep) {
				case JOptionPane.YES_OPTION:
				    auteur= JOptionPane.showInputDialog(
		                    null, "Entrez le nom de l'auteur du livre: ", 
		                    "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
					auteur= formatterStringMajuscules(auteur);
					String editeur= JOptionPane.showInputDialog(
	                                null, "Entrez la maison d'edition du livre: ", 
	                                "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
					editeur= formatterStringMajuscules(editeur);
					Livre unLivre= new Livre(num,dateEmprunt,titre,auteur,editeur);
					biblio.add(unLivre);
					break;
				case JOptionPane.NO_OPTION:
					int numSerie= Integer.parseInt(
								  JOptionPane.showInputDialog(
								  null, "Entrez le numero de serie du périodique: ",
								  "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE));
					int periodicite= Integer.parseInt(
							  		 JOptionPane.showInputDialog(
							         null, "Entrez la periodicite du périodique: ",
							         "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE));
					Periodique unPeriodique= new Periodique(num,dateEmprunt,titre,numSerie,periodicite);
					biblio.add(unPeriodique);
					break;
				case JOptionPane.CANCEL_OPTION:
					auteur= JOptionPane.showInputDialog(
	                        null, "Entrez le nom de l'auteur du CD: ", 
	                        "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
					auteur= formatterStringMajuscules(auteur);
					CD unCD= new CD(num,dateEmprunt,titre,auteur);
					biblio.add(unCD);
					break;
			}
			compteur++;
			JOptionPane.showMessageDialog(
		    null, "Ouvrage cree", "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(
		    null, "Capacite maximale de la liste atteinte", "Ajout d'un ouvrage", JOptionPane.ERROR_MESSAGE);
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
	
	private void chargerListe(List<ArrayList<String>> listeAttributs) {
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
				biblio.add(unLivre);
			}else if(type == 'P') {
				int numSerie= Integer.parseInt(donneesOuvrage.get(4));
				int periodicite= Integer.parseInt(donneesOuvrage.get(5));
				Periodique unPeriodique= new Periodique(num,dateEmprunt,titre,numSerie,periodicite);
				biblio.add(unPeriodique);
			}else if(type == 'C') {
				String auteur= donneesOuvrage.get(4);
				CD unCD= new CD(num,dateEmprunt,titre,auteur);
				biblio.add(unCD);
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

}
