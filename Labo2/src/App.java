
import java.util.ArrayList;
import java.util.List;

import dao_controleurs.ControleurLivre;
import dao_modeles.Livre;

public class App {

	public static void main(String[] args) {
		
		/*
        // Selon le choix de l'utilisateur faudra appeler la bonne méthode
        // du contrôleur.
         * 
         * 
        // CAS 1 : Enregistrer un livre (PASSED)
        Livre livre = new Livre();
        livre.setTitre("Conan");
        livre.setNumAuteur(1);
        livre.setAnnee(1959);
        livre.setPages(200);
        livre.setCateg("aventure");
        
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        String message = CtrL.CtrL_Enregistrer(livre);
        System.out.println(message);
        */
		
	    /*
        // CAS 2 : Lire tous les livres 
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetAll();
        int compteur = 0;
        for(Livre livre : listeLivres) {
        	compteur++;
        	System.out.println("************");
        	System.out.println("Livre n."+compteur);
        	System.out.println("idf= "+livre.getIdf());
        	System.out.println("titre= "+livre.getTitre());
        	System.out.println("numAuteur= "+livre.getNumAuteur());
        	System.out.println("annee= "+livre.getAnnee());
        	System.out.println("pages= "+livre.getPages());
        	System.out.println("categ= "+livre.getCateg());
        }
		*/
		
		/*
		// CAS 3 : Lire un livre
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        Livre livre = CtrL.CtrL_GetByIdf(999);
    	System.out.println("************");
    	System.out.println("idf= "+livre.getIdf());
    	System.out.println("titre= "+livre.getTitre());
    	System.out.println("numAuteur= "+livre.getNumAuteur());
    	System.out.println("annee= "+livre.getAnnee());
    	System.out.println("pages= "+livre.getPages());
    	System.out.println("categ= "+livre.getCateg());
		*/
		
		/*
		// CAS 4 : Lire tous les livres selon Categ
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetByCateg("suspense");
        int compteur = 0;
        for(Livre livre : listeLivres) {
        	compteur++;
        	System.out.println("************");
        	System.out.println("Livre n."+compteur);
        	System.out.println("idf= "+livre.getIdf());
        	System.out.println("titre= "+livre.getTitre());
        	System.out.println("numAuteur= "+livre.getNumAuteur());
        	System.out.println("annee= "+livre.getAnnee());
        	System.out.println("pages= "+livre.getPages());
        	System.out.println("categ= "+livre.getCateg());
        }
        */
		
		/*
		// CAS 5 : Lire tous les livres selon NumAuteur
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetByNumAuteur(7);
        int compteur = 0;
        for(Livre livre : listeLivres) {
        	compteur++;
        	System.out.println("************");
        	System.out.println("Livre n."+compteur);
        	System.out.println("idf= "+livre.getIdf());
        	System.out.println("titre= "+livre.getTitre());
        	System.out.println("numAuteur= "+livre.getNumAuteur());
        	System.out.println("annee= "+livre.getAnnee());
        	System.out.println("pages= "+livre.getPages());
        	System.out.println("categ= "+livre.getCateg());
        }
        */
		
		
		// CAS 6 : Modifier le titre d'un livre
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        Livre livre = CtrL.CtrL_GetByIdf(999);
        livre.setTitre("test");
        String message = CtrL.CtrL_ModifierTitre(livre);
        System.out.println(message);
        
	}

}
