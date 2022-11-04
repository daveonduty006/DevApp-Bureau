
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

import dao_controleurs.ControleurLivre;
import dao_modeles.Livre;


@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener{
	
	static JTable table;
	static DefaultTableModel model;
	static JScrollPane sp;
	static Set<Integer> tabNumLivres;
    static Set<Integer> tabAuteurs;
    static Set<String> tabCategs;
    static JComboBox<String> comboBoxCategs;
    static JComboBox<Integer> comboBoxAuteurs;
    static JComboBox<Integer> comboBoxLivres;
    static JButton btnLister;
    static JButton btnModifier;
    static JButton btnSupprimer;
    static JButton btnAjouter;
    //static int numLivreChoisi;
    static boolean ecoute;
  
    Interface() throws Exception {

    	this.setTitle("Laboratoire 1");
		ImageIcon logo= new ImageIcon("logo.png"); 
		this.setIconImage(logo.getImage());
    	this.getContentPane().setBackground(Color.GRAY);
    	this.getContentPane().setForeground(Color.white);
    	this.setBounds(100,100,857,710);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
    	this.getContentPane().setLayout(null);
    	this.setLocationRelativeTo(null);
    	
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
    	toolBar.add(btnLister);
    	toolBar.addSeparator(new Dimension(65,10));
    	toolBar.add(btnAjouter);
    	toolBar.addSeparator(new Dimension(65,10));
    	
    	JLabel lblLaboratoire = new JLabel("                                     LABORATOIRE 2");
    	lblLaboratoire.setFont(new Font("Tahoma", Font.BOLD, 15));
    	toolBar.add(lblLaboratoire);
    	toolBar.setRollover(true);
    	this.getContentPane().add(toolBar);    	
    	
    	model= new DefaultTableModel();
    	String[] colonnes= {"Numero du Livre", "Titre", "Numero de l'Auteur", "Annee de Publication", 
                "Nombre de Pages", "Categorie du Livre"};
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
    	
    	BufferedImage image= ImageIO.read(new File("image.jpg"));
    	
    	JLabel imgLabel = new JLabel(new ImageIcon(image));
    	imgLabel.setBounds(0, 631, 841, 40); 	
    	getContentPane().add(imgLabel);
    	
    	ecoute= true;
    	
    	this.addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	        if (JOptionPane.showConfirmDialog(null, 
    	            "Voulez-vous mettre fin a votre session?", "Fermeture du programme", 
    	            JOptionPane.YES_NO_OPTION,
    	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
    	            System.exit(0);
    	        }
    	    }
    	});
    	
        getRootPane().setBorder(BorderFactory.createBevelBorder(10, Color.BLACK, Color.GRAY));
        chargerInterface();
    	this.setVisible(true);
    }
    /*
	public void ajouterLivre(Object[] nouveauLivre) throws IOException {
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
    	int num= (Integer) nouveauLivre[0];
    	String titre= (String) nouveauLivre[1];
    	int numAuteur= (Integer) nouveauLivre[2];
    	int annee= (Integer) nouveauLivre[3];
    	int pages= (Integer) nouveauLivre[4];
    	String categorie= (String) nouveauLivre[5];
    	long addresse= 0;
        addresse= tmpWriteBin.length();
        tmpWriteBin.seek(addresse);
        tmpWriteBin.writeInt(num);
        tmpWriteBin.writeUTF(titre);
        tmpWriteBin.writeInt(numAuteur);
        tmpWriteBin.writeInt(annee);
        tmpWriteBin.writeInt(pages);
        tmpWriteBin.writeUTF(categorie);
        tmpWriteBin.seek(addresse);
        System.out.println(tmpWriteBin.readInt());
        System.out.println(tmpWriteBin.readUTF());
        System.out.println(tmpWriteBin.readInt());
        System.out.println(tmpWriteBin.readInt());
        System.out.println(tmpWriteBin.readInt());
        System.out.println(tmpWriteBin.readUTF());    	
    	Object[] mapValeur= {addresse,1,tmpWriteBin.getFilePointer()-addresse};
    	tableIndex.put(num, mapValeur);
    	ecoute= false;
    	tabNumLivres.add(num);
    	chargerComboBoxLivres();
    	tabAuteurs.add(numAuteur);
    	chargerComboBoxAuteurs();
    	tabCategs.add(categorie);
    	chargerComboBoxCategs();
    	ecoute= true;
		JOptionPane.showMessageDialog(null, "Livre #"+num+" cree");
        tmpWriteBin.close();  
    }
    */
    public void supprimerLivre(int numLivreChoisi) {
		ControleurLivre CtrL = ControleurLivre.getControleurLivre();
		String message = CtrL.CtrL_Supprimer(numLivreChoisi);
        JOptionPane.showMessageDialog(null, message);	
    }
    
    public void modifierTitre(int numLivreChoisi) {
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        Livre livre = CtrL.CtrL_GetByIdf(numLivreChoisi);
        String nouveauTitre= JOptionPane.showInputDialog(null, "Entrez le nouveau titre");
        livre.setTitre(nouveauTitre);
        String message = CtrL.CtrL_ModifierTitre(livre);
        JOptionPane.showMessageDialog(null, message);	
    }
    
    public void listerCategChoisi(String categChoisi) {
    	int idf, numAuteur, annee, pages;
    	String titre, categ;
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetByCateg(categChoisi);
        for(Livre livre : listeLivres) {
        	idf= livre.getIdf();
        	titre= livre.getTitre();
            numAuteur= livre.getNumAuteur();
            annee= livre.getAnnee();
            pages= livre.getPages();
            categ= livre.getCateg();
    	    Object[] rangee= {idf, titre, numAuteur, annee, pages, categ};
    	    model.addRow(rangee);
        }    
    }
    
    public void listerAuteurChoisi(int numAuteurChoisi) {
    	int idf, numAuteur, annee, pages;
    	String titre, categ;
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetByNumAuteur(numAuteurChoisi);
        for(Livre livre : listeLivres) {
        	idf= livre.getIdf();
        	titre= livre.getTitre();
            numAuteur= livre.getNumAuteur();
            annee= livre.getAnnee();
            pages= livre.getPages();
            categ= livre.getCateg();
    	    Object[] rangee= {idf, titre, numAuteur, annee, pages, categ};
    	    model.addRow(rangee);
        }    
    }
    
    public void listerLivreChoisi(int numLivreChoisi) {
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        Livre livre = CtrL.CtrL_GetByIdf(numLivreChoisi);
        int idf= livre.getIdf();
        String titre= livre.getTitre();
        int numAuteur= livre.getNumAuteur();
        int annee= livre.getAnnee();
        int pages= livre.getPages();
        String categ= livre.getCateg();
	    Object[] rangee= {idf, titre, numAuteur, annee, pages, categ};
	    model.addRow(rangee); 
    }
    
	public void listerLivres() {
    	int idf, numAuteur, annee, pages;
    	String titre, categ;
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetAll();
        for(Livre livre : listeLivres) {
        	idf= livre.getIdf();
        	titre= livre.getTitre();
            numAuteur= livre.getNumAuteur();
            annee= livre.getAnnee();
            pages= livre.getPages();
            categ= livre.getCateg();
    	    Object[] rangee= {idf, titre, numAuteur, annee, pages, categ};
    	    model.addRow(rangee);
        }    
    }
		
	private void chargerComboBoxLivres() {
		comboBoxLivres.removeAllItems();
		for(Integer numLivre : tabNumLivres) {
			comboBoxLivres.addItem(numLivre);
		}
	}
	
	private void chargerComboBoxCategs() {
		comboBoxCategs.removeAllItems();
		for(String categ : tabCategs) {
			comboBoxCategs.addItem(categ);
		}
	}
	
	private void chargerComboBoxAuteurs() {
		comboBoxAuteurs.removeAllItems();
		for(Integer numAuteur : tabAuteurs) {
			comboBoxAuteurs.addItem(numAuteur);
		}
	}
	
	private void chargerInterface() {
		tabNumLivres= new TreeSet<>();
    	tabAuteurs= new TreeSet<>();
    	tabCategs= new TreeSet<>();
    	int idf, numAuteur, annee, pages;
    	String titre, categ;
        ControleurLivre CtrL = ControleurLivre.getControleurLivre();
        List<Livre> listeLivres = new ArrayList<>();
        listeLivres = CtrL.CtrL_GetAll();
        for(Livre livre : listeLivres) {
        	idf= livre.getIdf();
        	titre= livre.getTitre();
            numAuteur= livre.getNumAuteur();
            annee= livre.getAnnee();
            pages= livre.getPages();
            categ= livre.getCateg();
            tabNumLivres.add(idf);
            tabAuteurs.add(numAuteur);
            tabCategs.add(categ);
    	    Object[] rangee= {idf, titre, numAuteur, annee, pages, categ};
    	    model.addRow(rangee);
        }
        chargerComboBoxLivres();
        chargerComboBoxCategs();
        chargerComboBoxAuteurs();
        listerLivres();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLister) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    listerLivres();
		}
		/*
		if(e.getSource() == btnAjouter) {
			FormNouveauLivre form= new FormNouveauLivre(this, "Ajout d'un Livre", tabNumLivres);
			form.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			form.setVisible(true);
			if(form.getNumLivre() != 0) {
				Object[] nouveauLivre= {form.getNumLivre(), form.getTitre(), form.getNumAuteur(),
										form.getAnnee(), form.getPages(), form.getCategorie()};
				try {
					ajouterLivre(nouveauLivre);
				} catch (IOException e1) {
				}
			}
		}
		*/
		if(e.getSource() == comboBoxLivres && ecoute) {
			int numLivreChoisi= (Integer) comboBoxLivres.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			listerLivreChoisi(numLivreChoisi);
		}
		if(e.getSource() == comboBoxAuteurs && ecoute) {
			int numAuteurChoisi= (Integer) comboBoxAuteurs.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			listerAuteurChoisi(numAuteurChoisi);
		}
		if(e.getSource() == comboBoxCategs && ecoute) {
			String categChoisi= (String) comboBoxCategs.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    listerCategChoisi(categChoisi);
		}
		if(e.getSource() == btnModifier) {
			int numLivreChoisi= (Integer) comboBoxLivres.getSelectedItem();
			modifierTitre(numLivreChoisi);
		}
		if(e.getSource() == btnSupprimer) {
			int numLivreChoisi= (Integer) comboBoxLivres.getSelectedItem();
			supprimerLivre(numLivreChoisi);
	    	ecoute= false;
	    	tabNumLivres.remove(numLivreChoisi);
	    	chargerComboBoxLivres();
	    	ecoute= true; 	
		}
	}
}