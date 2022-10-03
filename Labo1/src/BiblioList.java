import java.util.*;
import javax.swing.*;

public class BiblioList extends Bibliotheque {
	
	
	
	//
	
	private int tailleMax;
	private int compteur; 
	private List<Ouvrage> biblio;
	private SortedSet<Integer> cles;
	
	//
	
	BiblioList(List<ArrayList<String>> listeAttributs, int taille) {
		this.biblio= new LinkedList<>();
		this.compteur= 0;
		this.tailleMax= taille;
		chargerListe(listeAttributs);
	}
	
	//
	
	public List<Ouvrage> getBiblio() {
		return this.biblio;
	}
	
	public int getTailleMax() {
		return this.tailleMax;
	}
	
	//
	
	public Ouvrage rechercherOuvrage(int num) {
		int i= 0;
		int posTrouve= -1;
		while(i < this.biblio.size() && posTrouve == -1) {
			if(this.biblio.get(i).num == num) {
				posTrouve= i;
			}else {
				i++;
			}
		}
		if(posTrouve != -1) {
			return this.biblio.get(posTrouve);
		}else {
			return null;
		}
	}
	
	public void supprimerOuvrage() {
		String msg;
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		int num= Integer.parseInt(
				 JOptionPane.showInputDialog(
	             null, "Entrez le numero de l'ouvrage a supprimer: ", "Suppression d'un ouvrage",
                 JOptionPane.PLAIN_MESSAGE));
		Ouvrage unOuvrage= rechercherOuvrage(num);
		if(unOuvrage != null) {
			this.biblio.remove(unOuvrage);
			this.compteur--;
			cles.remove(num);
			msg= "Ouvrage supprime";
		}else {
			msg= "Ouvrage inexistant";
		}
		JOptionPane.showMessageDialog(
	    null, msg, "Suppression d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void ajouterOuvrage() {
		if(this.biblio.size() < tailleMax) {
			while(cles.contains(this.compteur+1)) {
				this.compteur++;
			}
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
					this.biblio.add(unLivre);
					break;
				case JOptionPane.NO_OPTION:
					int numSerie= Integer.parseInt(
								  JOptionPane.showInputDialog(
								  null, "Entrez le numero de publication du periodique: ",
								  "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE));
					int periodicite= Integer.parseInt(
							  		 JOptionPane.showInputDialog(
							         null, "Entrez la periodicite du periodique: ",
							         "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE));
					Periodique unPeriodique= new Periodique(num,dateEmprunt,titre,numSerie,periodicite);
					this.biblio.add(unPeriodique);
					break;
				case JOptionPane.CANCEL_OPTION:
					auteur= JOptionPane.showInputDialog(
	                        null, "Entrez le nom de l'auteur du CD: ", 
	                        "Ajout d'un ouvrage", JOptionPane.PLAIN_MESSAGE);
					auteur= formatterStringMajuscules(auteur);
					CD unCD= new CD(num,dateEmprunt,titre,auteur);
					this.biblio.add(unCD);
					break;
			}
			this.compteur++;
			cles.add(num);
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
		String rep= "Nombre d'ouvrages: "+cles.size()+"\n\n";
		for(Ouvrage unOuvrage : this.biblio) {
			if(unOuvrage != null) {
				rep += unOuvrage.toString()+"\n";
			}
		}
		return rep;
	}
	
	//
	
	private void chargerListe(List<ArrayList<String>> listeAttributs) {
		cles= new TreeSet<>();
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
				this.biblio.add(unLivre);
			}else if(type == 'P') {
				int numSerie= Integer.parseInt(donneesOuvrage.get(4));
				int periodicite= Integer.parseInt(donneesOuvrage.get(5));
				Periodique unPeriodique= new Periodique(num,dateEmprunt,titre,numSerie,periodicite);
				this.biblio.add(unPeriodique);
			}else if(type == 'C') {
				String auteur= donneesOuvrage.get(4);
				CD unCD= new CD(num,dateEmprunt,titre,auteur);
				this.biblio.add(unCD);
			}
			this.compteur++;
			cles.add(num);
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
