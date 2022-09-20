import java.io.*;

public class Exercice1 {
	
	static final String FICHIER_BINAIRE= "src/donnees/employes.bin";
	static final int TAILLE_ENREG= 36;
	static final int INTERVAL_CLE= 100;
	static int compteur;
	static RandomAccessFile donnee;
	static BufferedReader in;
	
	public static void main(String[] args) throws Exception {
	    in= new BufferedReader(new InputStreamReader(System.in));
	    chargerEnregs();
	    boolean sortie= false;
		int choix= 0;
        do{       
        	do{
                try{
                    System.out.println("Menu");
                    System.out.println("====\n");
                    System.out.println("1. Afficher les employes");
                    System.out.println("2. Ajouter un employe");                 
                    System.out.println("3. Modifier le salaire d'un employe");
                    System.out.println("4. Calculer le salaire moyen"); 
                    System.out.println("5. Rechercher un employe");  
                    System.out.println("6. Quitter");      
                    choix= Integer.parseInt(in.readLine());
                }catch(NumberFormatException e)
                    {}
            }while(choix < 1 || choix > 6);
		switch(choix) {
			case 1:
				afficherEnregs();
				break;
			case 2:
				ajouterEnreg();
				break;
			case 3:
				//modifierSal();
				break; 
			case 4:
				//calculerSalMoyen();
				break; 
			case 5:
				rechercherEnreg();
				break; 
			case 6:
				donnee.close();
				System.exit(0); 
			}
		}while(!sortie); 
        donnee.close();
        System.exit(0);  
	}
	
	private static long recupererAdresse(int cle) {
		long adresse= (cle/INTERVAL_CLE-1) * TAILLE_ENREG;
		return adresse;
	}
	
	private static String formatterString(String str) {
		if(str.length() < 10) {
			str= String.format("%-10s", str);
		}else if(str.length() > 10) {
			str= str.substring(0,10);
		}
		return str;
	}
	
	public static void chargerEnregs() throws IOException {
		File fic= new File(FICHIER_BINAIRE);
		if (fic.exists()) { 
			donnee= new RandomAccessFile(fic, "rw");
			compteur= (int) (donnee.length()/TAILLE_ENREG*INTERVAL_CLE);
		}else{
			donnee= new RandomAccessFile(fic, "rw");
			compteur= INTERVAL_CLE;
			String nom= formatterString("Tavares");
			String prenom= formatterString("Antonio");
			double sal= 5500;
		    donnee.writeInt(compteur);
		    donnee.writeUTF(nom);
		    donnee.writeUTF(prenom);
		    donnee.writeDouble(sal);
		}
	}
	
	public static void afficherEnregs() throws IOException {
		System.out.println();
		int num;
		String nom, prenom;
		double sal;
		donnee.seek(0);
		for (int i=0; i < donnee.length(); i++) {
            try{
				num= donnee.readInt();
				nom= donnee.readUTF();
				prenom= donnee.readUTF();
				sal= donnee.readDouble();
				System.out.println(num+" "+nom+" "+prenom+" "+sal);	
            }catch(EOFException e)
            	{}
		}
		System.out.println();
	}
	
	public static void ajouterEnreg() throws IOException {
        System.out.println();                          
        donnee.seek(donnee.length());
        System.out.println(donnee.getFilePointer());
        compteur+= INTERVAL_CLE;                  
        try{
        	donnee.writeInt(compteur);
            System.out.println("Entrez le nom du nouvel employe: ");
            donnee.writeUTF(formatterString(in.readLine()));
            System.out.println("Entrez le prénom du nouvel employe: ");            
            donnee.writeUTF(formatterString(in.readLine()));
            System.out.println("Entrez le salaire du nouvel employe: ");
            donnee.writeDouble(Double.parseDouble(in.readLine()));
        }catch(EOFException e)
            {}                     
        System.out.println(); 
	}
	
	public static void rechercherEnreg() throws IOException {
        System.out.println();  
        int num, numero;
        String nom, prenom;
        double sal;
        System.out.println("Entrez le numero de l'employe recherche: ");
        do{
        	num= Integer.parseInt(in.readLine());
        }while(num%INTERVAL_CLE != 0 || num == 0);
        donnee.seek(recupererAdresse(num));
        System.out.println(donnee.getFilePointer());
        try{
        	System.out.println();
			numero= donnee.readInt();
			nom= donnee.readUTF();
			prenom= donnee.readUTF();
			sal= donnee.readDouble();
			System.out.println(numero+" "+nom+" "+prenom+" "+sal);	
        }catch(EOFException e)
        	{}
        System.out.println();
	}
		
}
