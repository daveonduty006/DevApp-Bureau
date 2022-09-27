import java.util.*;
import java.io.*;

public class Utilitaires {

    static BufferedReader tmpReadTexte;
    static BufferedWriter tmpWriteTexte;
    static ObjectInputStream tmpReadObj;
    static ObjectOutputStream tmpWriteObj;
    static Object obj;


    public static String ajouterEspacesFin(int tailleColMax, String chaine) {
        int nbEspaces = tailleColMax - chaine.length();
        String espaces = "";
        for (int i = 0; i < nbEspaces; i++) {
            espaces += " ";
        }
        System.out.println(espaces.length() + "==>" + chaine.length() + "==>" + (espaces.length() + chaine.length()));
        return espaces;
    }

    public static String ajouterEspacesDebut(int tailleColMax, String chaine) {
        int nbEspaces = (tailleColMax - chaine.length()) / 2;
        String espaces = "";
        for (int i = 0; i < nbEspaces; i++) {
            espaces += " ";
        }
        return espaces;
    }

    public static String ajouterCaractereGauche(char car, int longueur, String ch) {
        String rep = "";
        int nbCar = longueur - ch.length();
        for (int i = 1; i <= nbCar; i++) {
            rep += car;
        }
        return rep + ch;
    }
    
    public static void sauvegarderFichierObjets(Object obj, String fichier) throws IOException {
        try {
            tmpWriteObj = new ObjectOutputStream(new FileOutputStream(fichier));
            tmpWriteObj.writeObject(obj);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. Verifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probleme est arrive lors de la manipulation du fichier. Verifiez vos donnees.");
        } catch (Exception e) {
            System.out.println("Un probleme est arrive lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Execute si erreur ou pas
            tmpWriteObj.close();
        }
    }

    public static Object chargerFichierObjets(String fichier) throws Exception {
        try {
            tmpReadObj = new ObjectInputStream(new FileInputStream(fichier));
            obj = tmpReadObj.readObject();
            tmpReadObj.close();
            return obj;
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. Verifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probleme est arrive lors de la manipulation du fichier. Verifiez vos donnees.");
        } catch (Exception e) {
            System.out.println("Un probleme est arrive lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Execute si erreur ou pas
            tmpReadObj.close();
        }
        return null;
    }

    public static ArrayList<ArrayList<String>> chargerFichierTexte(String fichier, String delimiteurs) throws Exception {
        String ligne;
        // String elems[] = new String[];
        ArrayList<ArrayList<String>> listeAttributs = new ArrayList<ArrayList<String>>();
        ArrayList<String> attributs;
        try {
            tmpReadTexte = new BufferedReader(new FileReader(fichier));
            StringTokenizer st;
            ligne = tmpReadTexte.readLine();// Lire la premiere ligne du fichier
            while (ligne != null) {// Si ligne == null alors ont a atteint la fin du fichier
                st= new StringTokenizer(ligne, delimiteurs);
                attributs = new ArrayList<String>();
                while(st.hasMoreTokens()){
                     attributs.add(st.nextToken());
                }
                listeAttributs.add(attributs);
                ligne = tmpReadTexte.readLine();
            } // fin while

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. Verifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probleme est arrive lors de la manipulation du fichier. Verifiez vos donnees.");
        } catch (Exception e) {
            System.out.println("Un probleme est arrive lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Execute si erreur ou pas
            tmpReadTexte.close();      
        }
        return listeAttributs;
    }

}