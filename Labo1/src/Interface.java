import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener{
	
	static final String FICHIER_BIN = "src/data/livres.bin";
	static final String[] colonnes= {"Numero du Livre", "Titre", "Numero de l'Auteur", "Annee de Publication", 
                                     "Nombre de Pages", "Categorie du Livre"};
	static DefaultTableModel model;
	static JScrollPane sp;
    static JButton btnLister;
	static BufferedReader tmpReadTxt;
    static RandomAccessFile tmpWriteBin;
    static String cheminFichierTxt;
    static Map<Integer,Object[]> tableIndex;
    static int compteurEnregEffaces;
  
    Interface() throws FileNotFoundException {

    	this.getContentPane().setBackground(Color.GRAY);
    	this.getContentPane().setForeground(Color.white);
    	this.setBounds(100,100,857,710);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    	this.getContentPane().setLayout(null);
    	this.setLocationRelativeTo(null);	
    	
    	JToolBar toolBar = new JToolBar();
    	toolBar.setBackground(Color.lightGray);
    	toolBar.setFloatable(false);
    	toolBar.setBounds(0, 0, 857, 42);
    	
    	btnLister = new JButton("Lister Tous les Livres");
    	btnLister.setFocusable(false);
    	btnLister.addActionListener(this);
    	toolBar.add(btnLister);
    	
    	model= new DefaultTableModel();
    	model.setColumnIdentifiers(colonnes);
    	JTable table= new JTable(model);
    	table.setRowHeight(30);
    	table.setAutoCreateRowSorter(true);
    	table.getRowSorter().toggleSortOrder(0);
    	
    	sp= new JScrollPane(table);
    	sp.setBounds(10, 53, 821, 475);
    	
    	this.getContentPane().add(toolBar); 	
    	this.getContentPane().add(sp);
    	this.setVisible(true);
    	obtenirFichierBinaire();
    }
    
	public void listerLivres() throws IOException {
    	int num, numAuteur, annee, pages;
    	String titre, categorie;
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
        try { 
        	while(true) {
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
        }catch(Exception e) {
        	//
        }        
        tmpWriteBin.close();      
    }
	
	private void obtenirFichierBinaire() throws FileNotFoundException {
        File f= new File(FICHIER_BIN);
        if(!f.exists()) {
            JButton open= new JButton();
            JFileChooser fc= new JFileChooser();
            fc.setDialogTitle("Emplacement du fichier livres.txt");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            	cheminFichierTxt= fc.getSelectedFile().getAbsolutePath();  
            	tmpReadTxt= new BufferedReader(new FileReader(cheminFichierTxt));
                ecrireFichierBinaire();
            }
        }
	}
	
	private void ecrireFichierBinaire() throws FileNotFoundException {
		tableIndex= new TreeMap<>();
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
                ligne= tmpReadTxt.readLine();
            }
            tmpReadTxt.close();
            tmpWriteBin.close();
        }catch(Exception e) {
        	System.out.println("Gros probleme! "+e.getMessage());
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLister) {
			try {
				listerLivres();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
