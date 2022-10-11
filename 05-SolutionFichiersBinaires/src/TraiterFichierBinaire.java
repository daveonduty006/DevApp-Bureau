import java.io.*;

public class TraiterFichierBinaire {
	
    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    static BufferedReader clavier;
    static File fichierBin;

    // 4 octets pour numéro du film, 
    // 20 octets pour le titre,
    // 12 octets pour la catégorie,
    // 4 octets pour la durée. 
    // Le système ajoute au début de chaque string 2 octets '\\u'
    // pour indiquer l'encodage (UTF). Comme nous avons 2 strings
    // alors faudra ajouter à la taille de l'enregistrement 4 octets de plus.
    // Voilà donc le dernier 4 dans le calcul de la taille.
    static final int TAILLE_ENREG= 4+20+12+4+4;

    // Ajouter au string lu dans le fichier le nombre d'espaces nécessaire
    // pour compléter sa taille.
    
    public static void main(String args[]) throws IOException {        
        clavier= new BufferedReader(new InputStreamReader(System.in));
        fichierBin= new File("src/films.bin");
        if(!fichierBin.exists()) {
            ecrireDansFichierBinaire();
        }
	    boolean sortie= false;
		int choix= 0;
        do{       
        	do{
                try{
                	System.out.println();
                	System.out.println("MENU");
                    System.out.println("====");
                	System.out.println("1. Rechercher un film");
                    System.out.println("2. Lister tous les films");
                    System.out.println("3. Changer le titre d'un film"); 
                    System.out.println("4. Supprimer un film");   
                    System.out.println("5. Quitter");
                    choix= Integer.parseInt(clavier.readLine());
                }catch(NumberFormatException e)
                	{}
            }while(choix < 1 || choix > 5);
        	switch(choix) {
				case 1:
			        rechercherUnFilm();
					break;
				case 2:
			        listerFilms();
					break;
				case 3:
			        remplacerTitre(); 
					break; 
				case 4:
			        supprimerUnFilm();
					break; 
				case 5:
					sortie= true;
					break;
			}
		}while(!sortie);
        System.exit(0); 
    }
    
    public static String formaterString(String leString, int tailleMax) {
        //int taille = tailleMax-leString.length();
        //for(int i=0;i<taille;i++){
        //    leString+=" ";
        //}
        //return leString;
		if(leString.length() < tailleMax) {
			leString= String.format("%-"+tailleMax+"s", leString);
		}else if(leString.length() > tailleMax) {
			leString= leString.substring(0,tailleMax);
		}
		return leString;
	}
    
    public static void afficherInfosFilm(int cle) throws IOException {
        int num, duree;
        String titre, categ;
        long adr= obtenirAdresse(cle);
        //System.out.println(adr);
        try{
            tmpWriteBin= new RandomAccessFile("src/films.bin", "rw");
            tmpWriteBin.seek(adr);
            num= tmpWriteBin.readInt();
            titre= tmpWriteBin.readUTF();
            categ= tmpWriteBin.readUTF();
            duree= tmpWriteBin.readInt();
            System.out.println();
            System.out.println("NUMERO= " +num+"\nTITRE= "+titre);
            System.out.println("CATEGORIE= "+categ+"\nDUREE= "+duree);
            System.out.println("*****************************");
        }catch(Exception e) {
            System.out.println("Gros probleme! "+e.getMessage());
        }finally{
            tmpWriteBin.close();
        }
    }
    
    public static long obtenirAdresse(int cle) {
        long adr= (cle/100-1) * TAILLE_ENREG;
        return adr;
    }
    
    public static void ecrireDansFichierBinaire() {
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

    public static void rechercherUnFilm() throws IOException {
        char choix;
        int cle;
        do{
        	System.out.println();
            System.out.print("Entrez le numero du film: ");
            cle= Integer.parseInt(clavier.readLine());
            afficherInfosFilm(cle);
            System.out.print("Rechercher un autre film ? (O-oui, N-non): ");
            choix= clavier.readLine().trim().toUpperCase().charAt(0);           
        }while(choix == 'O');
    }
    
    public static void listerFilms() throws IOException {
        int num, duree;
        String titre, categ;
        tmpWriteBin= new RandomAccessFile("src/films.bin", "rw");
        try{
           // Les fichiers binaires n'ont pas de fin de fichier. Donc
           // lorsqu'il n'aura plus de données à lire il va déclencher 
           // une exception qui sera rattrapé dans le catch et on ferme 
           // le fichier.
           System.out.println("\n********** LISTE DES FILMS **********\n");
           while(true) { // Boucle infinie
        	   num= tmpWriteBin.readInt();
               titre= tmpWriteBin.readUTF();
               categ= tmpWriteBin.readUTF();
               duree= tmpWriteBin.readInt();
               // -1 indique que le film a précédemment été supprimé du fichier
               // les autres données sont également lu pour se repositionner jusqu'au 
               // prochain enregistrement.
               if(num != -1) {
            	   System.out.println("NUMERO= "+num+"\nTITRE= "+titre);
                   System.out.println("CATEGORIE= "+categ+"\nDUREE= "+duree);
                   System.out.println("*****************************");
               }
           }
        }catch(Exception e) 
        	{}
    	tmpWriteBin.close();
    }

    public static void remplacerTitre() throws IOException {
    	String titre;
    	int cle;
    	System.out.println();
    	System.out.print("Entrez le numero du film: ");
    	cle= Integer.parseInt(clavier.readLine());
    	System.out.print("Entrez le nouveau titre du film: ");
    	titre= formaterString(clavier.readLine(), 20);
    	long adr= obtenirAdresse(cle);
    	try{
    		tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
    		tmpWriteBin.seek(adr+4); // On passe par-dessus le numéro du film et tombe sur le titre
    		tmpWriteBin.writeUTF(titre);
        }catch(Exception e) {
            System.out.println("Gros probleme! "+e.getMessage());
        }finally{
            tmpWriteBin.close();
        }          
    }

    public static void supprimerUnFilm() throws IOException {
    	int cle;
    	System.out.println();
    	System.out.print("Entrez le numero du film: ");
    	cle= Integer.parseInt(clavier.readLine());     
    	long adr= obtenirAdresse(cle);
    	try{
    		tmpWriteBin= new RandomAccessFile("src/films.bin", "rw");
    		tmpWriteBin.seek(adr); 
    		tmpWriteBin.writeInt(-1);
    		tmpWriteBin.writeUTF("                    ");
    		tmpWriteBin.writeUTF("            ");
    		tmpWriteBin.writeInt(-1);
    	}catch(IOException e) {
    		System.out.println("Gros probleme! "+e.getMessage());
    	}finally{
    		tmpWriteBin.close();
    	}
    }
 
}
