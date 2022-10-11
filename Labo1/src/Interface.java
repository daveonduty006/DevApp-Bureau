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
    static int compteurEnregEffaces;
    static JComboBox<String> comboBoxCategs;
    static JComboBox<Integer> comboBoxAuteurs;
    static JComboBox<Integer> comboBoxLivres;
    static JButton btnRecharger;
    static JButton btnLister;
    static JButton btnModifier;
    static JButton btnSupprimer;
    static int numLivreChoisi;
  
    Interface() throws Exception {

    	this.getContentPane().setBackground(Color.GRAY);
    	this.getContentPane().setForeground(Color.white);
    	this.setBounds(100,100,857,710);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    	this.getContentPane().setLayout(null);
    	this.setLocationRelativeTo(null);
    	
    	btnRecharger= new JButton("RECHARGER LES LIVRES");
    	btnRecharger.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnRecharger.setFocusable(false);
    	btnRecharger.addActionListener(this);
    	btnLister= new JButton("LISTER TOUS LES LIVRES");
    	btnLister.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnLister.setFocusable(false);
    	btnLister.addActionListener(this);
    	
    	JToolBar toolBar = new JToolBar();
    	toolBar.setFloatable(false);
    	toolBar.setBounds(10, 11, 821, 31);
    	toolBar.add(btnRecharger);
    	toolBar.addSeparator(new Dimension(100,10));
    	toolBar.add(btnLister);
    	toolBar.addSeparator(new Dimension(100,10));
    	JLabel lblLaboratoire = new JLabel("                                    LABORATOIRE 1");
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
    
    public void supprimerLivre(int livreChoisi) {
    	tableIndex.get(livreChoisi)[1]= 0;
    	comboBoxLivres.removeItem(livreChoisi);
    	JOptionPane.showMessageDialog(null, "Livre #"+livreChoisi+" supprime");  	
    }
    
    public void modifierTitre(int livreChoisi) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	if( ( (Integer) tableIndex.get(livreChoisi)[1]) != 0) {
        	long addresse= (long) tableIndex.get(livreChoisi)[0];
        	tmpWriteBin.seek(addresse);
        	int num= tmpWriteBin.readInt();
        	String ancienTitre= tmpWriteBin.readUTF();
        	int tailleAncienTitre= ancienTitre.getBytes(StandardCharsets.UTF_8).length + 2;
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
		for(Integer numLivre : tabNumLivres) {
			comboBoxLivres.addItem(numLivre);
		}
	}
	
	private void chargerComboBoxCategs(Set<String> tabCategs) {
		for(String categ : tabCategs) {
			comboBoxCategs.addItem(categ);
		}
	}
	
	private void chargerComboBoxAuteurs(Set<Integer> tabAuteurs) {
		for(Integer numAuteur : tabAuteurs) {
			comboBoxAuteurs.addItem(numAuteur);
		}
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
		Set<Integer> tabNumLivres= new TreeSet<>();
		Set<String> tabCategs= new TreeSet<>();
		Set<Integer> tabAuteurs= new TreeSet<>();
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
