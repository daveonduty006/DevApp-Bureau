import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener{
	
	static final String FICHIER_BIN = "src/data/livres.bin";
	static final String[] colonnes= {"Numero du Livre", "Titre", "Numero de l'Auteur", "Annee de Publication", 
                                     "Nombre de Pages", "Categorie du Livre"};
	static JTable table;
	static DefaultTableModel model;
	static JScrollPane sp;
	static BufferedReader tmpReadTxt;
    static RandomAccessFile tmpWriteBin;
    static String cheminFichierTxt;
    static Map<Integer,Object[]> tableIndex;
    static Set<Integer> tabNumLivres;
    static Set<Integer> tabAuteurs;
    static Set<String> tabCategs;
    static int compteurEnregEffaces;
    static JComboBox<String> comboBoxCategs;
    static JComboBox<Integer> comboBoxAuteurs;
    static JComboBox<Integer> comboBoxLivres;
    static JButton btnRecharger;
    static JButton btnLister;
    static JButton btnModifier;
    static JButton btnSupprimer;
    static JButton btnAjouter;
    static int numLivreChoisi;
  
    Interface() throws Exception {

    	this.setTitle("Laboratoire 1");
    	this.getContentPane().setBackground(Color.GRAY);
    	this.getContentPane().setForeground(Color.white);
    	this.setBounds(100,100,857,710);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    	this.getContentPane().setLayout(null);
    	this.setLocationRelativeTo(null);
    	
    	btnRecharger= new JButton("RECHARGER LIVRES.TXT");
    	btnRecharger.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnRecharger.setFocusable(false);
    	btnRecharger.addActionListener(this);
    	btnLister= new JButton("LISTER TOUS LES LIVRES");
    	btnLister.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnLister.setFocusable(false);
    	btnLister.addActionListener(this);
    	btnAjouter= new JButton("AJOUTER UN LIVRE");
    	btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnAjouter.setFocusable(false);
    	btnAjouter.addActionListener(this);
    	
    	JToolBar toolBar = new JToolBar();
    	toolBar.setFloatable(false);
    	toolBar.setBounds(10, 11, 821, 31);
    	toolBar.addSeparator(new Dimension(15,10));
    	toolBar.add(btnRecharger);
    	toolBar.addSeparator(new Dimension(65,10));
    	toolBar.add(btnLister);
    	toolBar.addSeparator(new Dimension(65,10));
    	toolBar.add(btnAjouter);
    	toolBar.addSeparator(new Dimension(65,10));
    	
    	JLabel lblLaboratoire = new JLabel("LABORATOIRE 1");
    	lblLaboratoire.setFont(new Font("Tahoma", Font.BOLD, 15));
    	toolBar.add(lblLaboratoire);
    	toolBar.setRollover(true);
    	this.getContentPane().add(toolBar);    	
    	
    	model= new DefaultTableModel();
    	model.setColumnIdentifiers(colonnes);
    	table= new JTable(model);
    	table.setRowHeight(30);
    	table.setAutoCreateRowSorter(true);
    	table.getRowSorter().toggleSortOrder(0);
    	
    	sp= new JScrollPane(table);
    	sp.setBounds(10, 53, 821, 475);
    	this.getContentPane().add(sp);
    	
    	comboBoxCategs = new JComboBox<>();
    	comboBoxCategs.setBounds(668, 568, 163, 36);
    	comboBoxCategs.addActionListener(this);
    	this.getContentPane().add(comboBoxCategs);
    	
    	JLabel lbl3 = new JLabel("CATÉGORIES DES LIVRES");
    	lbl3.setFont(new Font("Tahoma", Font.BOLD, 13));
    	lbl3.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl3.setBounds(668, 539, 163, 26);
    	this.getContentPane().add(lbl3);
    	
    	comboBoxAuteurs = new JComboBox<>();
    	comboBoxAuteurs.setBounds(465, 568, 163, 36);
    	comboBoxAuteurs.addActionListener(this);
    	this.getContentPane().add(comboBoxAuteurs);
    	
    	JLabel lbl2 = new JLabel("AUTEURS DES LIVRES");
    	lbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
    	lbl2.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl2.setBounds(465, 539, 163, 26);
    	this.getContentPane().add(lbl2);
    	
    	comboBoxLivres = new JComboBox<>();
    	comboBoxLivres.setBounds(10, 568, 193, 36);
    	comboBoxLivres.addActionListener(this);
    	this.getContentPane().add(comboBoxLivres);
    	
    	btnModifier = new JButton("Modifier");
    	btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
    	btnModifier.setBounds(227, 552, 113, 26);
    	btnModifier.addActionListener(this);
    	this.getContentPane().add(btnModifier);
    	
    	btnSupprimer = new JButton("Supprimer");
    	btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
    	btnSupprimer.setBounds(227, 594, 113, 26);
    	btnSupprimer.addActionListener(this);
    	this.getContentPane().add(btnSupprimer);
    	
    	JLabel lbl1 = new JLabel("LIVRES DE LA BIBLIOTHÈQUE");
    	lbl1.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl1.setFont(new Font("Tahoma", Font.BOLD, 13));
    	lbl1.setBounds(10, 539, 193, 26);
    	this.getContentPane().add(lbl1);
    	
    	this.setVisible(true);
    	obtenirFichierBinaire(0);
    }
    
    public void ajouterLivre(Object[] nouveauLivre) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	int num= (Integer) nouveauLivre[0];
    	String titre= (String) nouveauLivre[1];
    	int numAuteur= (Integer) nouveauLivre[2];
    	int annee= (Integer) nouveauLivre[3];
    	int pages= (Integer) nouveauLivre[4];
    	String categorie= (String) nouveauLivre[5];
    	int tailleEnreg= 4+(titre.getBytes(StandardCharsets.UTF_8).length)+4+4+
    			         4+(categorie.getBytes(StandardCharsets.UTF_8).length);
    	long addresse= 0;
    	if(compteurEnregEffaces != 0) {
            for(Integer numLivre : tableIndex.keySet()) {
            	if( ( (Integer) tableIndex.get(numLivre)[1]) != 0) {
            		if( ( (Long) tableIndex.get(numLivre)[2]) <= tailleEnreg) {
                    	addresse = (long) tableIndex.get(numLivre)[0];
                    	tmpWriteBin.seek(addresse);
                    	tmpWriteBin.writeInt(num);
                    	long adrAncienAttribut= tmpWriteBin.getFilePointer();
                    	String ancienAttribut= tmpWriteBin.readUTF();
                    	int tailleAncienAttribut= ancienAttribut.getBytes(StandardCharsets.UTF_8).length;
                    	titre= formaterString(titre, tailleAncienAttribut); 
                    	tmpWriteBin.seek(adrAncienAttribut);
                    	tmpWriteBin.writeUTF(titre);
                    	tmpWriteBin.writeInt(numAuteur);
                    	tmpWriteBin.writeInt(annee);
                    	tmpWriteBin.writeInt(pages);
                    	adrAncienAttribut= tmpWriteBin.getFilePointer();
                    	ancienAttribut= tmpWriteBin.readUTF();
                    	tailleAncienAttribut= ancienAttribut.getBytes(StandardCharsets.UTF_8).length;
                    	categorie= formaterString(categorie, tailleAncienAttribut);
                    	tmpWriteBin.seek(adrAncienAttribut);
                    	tmpWriteBin.writeUTF(categorie);  
                    	compteurEnregEffaces--;
                    	break;
            		}
            	}
            }          
    	}else {
            addresse= tmpWriteBin.length();
            tmpWriteBin.seek(addresse);
        	tmpWriteBin.writeInt(num);
        	tmpWriteBin.writeUTF(titre);
        	tmpWriteBin.writeInt(numAuteur);
        	tmpWriteBin.writeInt(annee);
        	tmpWriteBin.writeInt(pages);
        	tmpWriteBin.writeUTF(categorie);
    	}
    	Object[] mapValeur= {addresse,1,tmpWriteBin.getFilePointer()-addresse};
    	tableIndex.put(num, mapValeur);
    	tabNumLivres.add(num);
    	chargerComboBoxLivres(tabNumLivres);
    	tabAuteurs.add(numAuteur);
    	chargerComboBoxAuteurs(tabAuteurs);
    	tabCategs.add(categorie);
    	chargerComboBoxCategs(tabCategs);
		JOptionPane.showMessageDialog(null, "Livre #"+num+" cree");
        tmpWriteBin.close();  
    }
    
    public void supprimerLivre(int livreChoisi) {
    	tableIndex.get(livreChoisi)[1]= 0;
    	comboBoxLivres.removeItem(livreChoisi);
    	tabNumLivres.remove(livreChoisi);
    	compteurEnregEffaces++;
    	JOptionPane.showMessageDialog(null, "Livre #"+livreChoisi+" supprime");  	
    }
    
    public void modifierTitre(int livreChoisi) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	if( ( (Integer) tableIndex.get(livreChoisi)[1]) != 0) {
        	long addresse= (long) tableIndex.get(livreChoisi)[0];
        	tmpWriteBin.seek(addresse);
        	int num= tmpWriteBin.readInt();
        	String ancienTitre= tmpWriteBin.readUTF();
        	int tailleAncienTitre= ancienTitre.getBytes(StandardCharsets.UTF_8).length;
        	int numAuteur= tmpWriteBin.readInt();
        	int annee= tmpWriteBin.readInt();
        	int pages= tmpWriteBin.readInt();
        	String categorie= tmpWriteBin.readUTF();
        	//
        	String nouveauTitre= JOptionPane.showInputDialog(null, "Entrez le nouveau titre");
        	nouveauTitre= formaterString(nouveauTitre, tailleAncienTitre); 
        	//
        	tmpWriteBin.seek(addresse);
            tmpWriteBin.writeInt(num);
            tmpWriteBin.writeUTF(nouveauTitre);
            tmpWriteBin.writeInt(numAuteur);
            tmpWriteBin.writeInt(annee);
            tmpWriteBin.writeInt(pages);
            tmpWriteBin.writeUTF(categorie);
        	JOptionPane.showMessageDialog(null, "Titre du Livre #"+livreChoisi+" modifie");  
            tmpWriteBin.close();  
    	}	
    }
    
    public void listerAuteurChoisi(int auteurChoisi) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	int num, numAuteur, annee, pages;
    	String titre, categorie;
        for(Integer numLivre : tableIndex.keySet()) {
        	if( ( (Integer) tableIndex.get(numLivre)[1]) != 0) {
            	long addresse = (long) tableIndex.get(numLivre)[0];
            	tmpWriteBin.seek(addresse);
        	    num= tmpWriteBin.readInt();
        	    titre= tmpWriteBin.readUTF();
        	    numAuteur= tmpWriteBin.readInt();
        	    annee= tmpWriteBin.readInt();
        	    pages= tmpWriteBin.readInt();
        	    categorie= tmpWriteBin.readUTF();
        	    if(numAuteur == auteurChoisi) {
        	        Object[] rangee= {String.valueOf(num), titre, String.valueOf(numAuteur), String.valueOf(annee), 
      		                          String.valueOf(pages), categorie};
        	        model.addRow(rangee);
        	    }
        	}
        }        
        tmpWriteBin.close(); 
    }
    
    public void listerCategChoisie(String categChoisie) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	int num, numAuteur, annee, pages;
    	String titre, categorie;
        for(Integer numLivre : tableIndex.keySet()) {
        	if( ( (Integer) tableIndex.get(numLivre)[1]) != 0) {
            	long addresse = (long) tableIndex.get(numLivre)[0];
            	tmpWriteBin.seek(addresse);
        	    num= tmpWriteBin.readInt();
        	    titre= tmpWriteBin.readUTF();
        	    numAuteur= tmpWriteBin.readInt();
        	    annee= tmpWriteBin.readInt();
        	    pages= tmpWriteBin.readInt();
        	    categorie= tmpWriteBin.readUTF();
        	    if(categChoisie.equals(categorie)) {
        	        Object[] rangee= {String.valueOf(num), titre, String.valueOf(numAuteur), String.valueOf(annee), 
      		                          String.valueOf(pages), categorie};
        	        model.addRow(rangee);
        	    }
            }     
        }        
        tmpWriteBin.close(); 
    }
    
    public void listerLivreChoisi(int livreChoisi) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	if( ( (Integer) tableIndex.get(livreChoisi)[1]) != 0) {
        	long addresse= (long) tableIndex.get(livreChoisi)[0];
        	tmpWriteBin.seek(addresse);
        	int num= tmpWriteBin.readInt();
        	String titre= tmpWriteBin.readUTF();
        	int numAuteur= tmpWriteBin.readInt();
        	int annee= tmpWriteBin.readInt();
        	int pages= tmpWriteBin.readInt();
        	String categorie= tmpWriteBin.readUTF();
        	Object[] rangee= {String.valueOf(num), titre, String.valueOf(numAuteur), String.valueOf(annee), 
    		                  String.valueOf(pages), categorie};
        	model.addRow(rangee);
            tmpWriteBin.close(); 
    	}
    }
    
	public void listerLivres() throws IOException {
    	int num, numAuteur, annee, pages;
    	String titre, categorie;
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
        for(Integer numLivre : tableIndex.keySet()) {
        	if( ( (Integer) tableIndex.get(numLivre)[1]) != 0) {
            	long addresse = (long) tableIndex.get(numLivre)[0];
            	tmpWriteBin.seek(addresse);
        	    num= tmpWriteBin.readInt();
        	    titre= tmpWriteBin.readUTF();
        	    numAuteur= tmpWriteBin.readInt();
        	    annee= tmpWriteBin.readInt();
        	    pages= tmpWriteBin.readInt();
        	    categorie= tmpWriteBin.readUTF();
        	    Object[] rangee= {String.valueOf(num), titre, String.valueOf(numAuteur), String.valueOf(annee), 
        	        			  String.valueOf(pages), categorie};
        	    model.addRow(rangee);
        	}
        }        
        tmpWriteBin.close();      
    }
	
    private static String formaterString(String nouveauString, int tailleAncienString) {
		if(nouveauString.length() < tailleAncienString) {
			nouveauString= String.format("%-"+tailleAncienString+"s", nouveauString);
		}else if(nouveauString.length() > tailleAncienString) {
			nouveauString= nouveauString.substring(0,tailleAncienString);
		}
		return nouveauString;
	}
	
	private void chargerComboBoxLivres(Set<Integer> tabNumLivres) {
		comboBoxLivres.removeAllItems();
		for(Integer numLivre : tabNumLivres) {
			comboBoxLivres.addItem(numLivre);
		}
		comboBoxLivres.setSelectedIndex(-1);
	}
	
	private void chargerComboBoxCategs(Set<String> tabCategs) {
		comboBoxCategs.removeAllItems();
		for(String categ : tabCategs) {
			comboBoxCategs.addItem(categ);
		}
		comboBoxCategs.setSelectedIndex(-1);
	}
	
	private void chargerComboBoxAuteurs(Set<Integer> tabAuteurs) {
		comboBoxAuteurs.removeAllItems();
		for(Integer numAuteur : tabAuteurs) {
			comboBoxAuteurs.addItem(numAuteur);
		}
		comboBoxAuteurs.setSelectedIndex(-1);
	}
	
	private void obtenirFichierBinaire(int btnRechargerPresse) throws Exception {
        File f= new File(FICHIER_BIN);
        if(!f.exists() || btnRechargerPresse==1) {
            JButton open= new JButton();
            JFileChooser fc= new JFileChooser();
            fc.setDialogTitle("Emplacement du fichier livres.txt");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            	cheminFichierTxt= fc.getSelectedFile().getAbsolutePath();
            	if("livres.txt".equals(cheminFichierTxt.substring(cheminFichierTxt.length()-10))) {
                	tmpReadTxt= new BufferedReader(new FileReader(cheminFichierTxt));
                    ecrireFichierBinaire();
            	}else {
            		JOptionPane.showMessageDialog(null, "Veuillez charger le fichier livres.txt svp");
            	}
            }
        }
	}
	
	private void ecrireFichierBinaire() throws Exception {
		tableIndex= new TreeMap<>();
		tabNumLivres= new TreeSet<>();
		tabAuteurs= new TreeSet<>();
		tabCategs= new TreeSet<>();
        String elems[]= new String[6];
        int num, numAuteur, annee, pages;
        String titre, categorie;
        try{
            tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
            String ligne= tmpReadTxt.readLine();
            while(ligne != null ) {
                elems= ligne.split(";");
                num= Integer.parseInt(elems[0]);
                titre= elems[1];
                numAuteur= Integer.parseInt(elems[2]);
                annee= Integer.parseInt(elems[3]);
                pages= Integer.parseInt(elems[4]);
                categorie= elems[5];
                long debut= tmpWriteBin.getFilePointer();
                tmpWriteBin.writeInt(num);
                tmpWriteBin.writeUTF(titre);
                tmpWriteBin.writeInt(numAuteur);
                tmpWriteBin.writeInt(annee);
                tmpWriteBin.writeInt(pages);
                tmpWriteBin.writeUTF(categorie);
                long fin= tmpWriteBin.getFilePointer();
                Object[] mapValeur= {debut, 1, fin-debut};
                tableIndex.put(num, mapValeur);
                tabNumLivres.add(num);
                tabCategs.add(categorie);
                tabAuteurs.add(numAuteur);
                ligne= tmpReadTxt.readLine();
            }
            tmpReadTxt.close();
            tmpWriteBin.close();
            chargerComboBoxLivres(tabNumLivres);
        	chargerComboBoxCategs(tabCategs);
        	chargerComboBoxAuteurs(tabAuteurs);
            //listerLivres();
        }catch(Exception e) {
        	System.out.println("Gros probleme! "+e.getMessage());
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRecharger) {
			try {
				obtenirFichierBinaire(1);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnLister) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			try {
				listerLivres();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnAjouter) {
			FormulaireNouveauLivre form= new FormulaireNouveauLivre(this, "Ajout d'un Livre", tabNumLivres);
			form.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			form.setVisible(true);
			if(form.getNumLivre() != 0) {
				Object[] nouveauLivre= {form.getNumLivre(), form.getTitre(), form.getNumAuteur(),
										form.getAnnee(), form.getPages(), form.getCategorie()};
				try {
					ajouterLivre(nouveauLivre);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == comboBoxLivres) {
			numLivreChoisi= (Integer) comboBoxLivres.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			try {
				listerLivreChoisi(numLivreChoisi);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == comboBoxCategs) {
			String categChoisi= (String) comboBoxCategs.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    try {
				listerCategChoisie(categChoisi);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == comboBoxAuteurs) {
			int auteurChoisi= (Integer) comboBoxAuteurs.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    try {
				listerAuteurChoisi(auteurChoisi);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnModifier) {
			try {
				modifierTitre(numLivreChoisi);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnSupprimer) {
			supprimerLivre(numLivreChoisi);
		}
	}
}
