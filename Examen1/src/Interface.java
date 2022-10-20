import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {
	
	static final String FICHIER_BIN = "src/data/radios.bin";
	static final String FICHIER_TXT = "src/data/radios.txt";
	static final String FICHIER_TABLE_INDEX= "src/data/tableIndex.obj";
    static File fichierBin;
    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    static Map<Integer,Object[]> tableIndex;
    static Set<Integer> tabNumRadios;
	static DefaultTableModel model;

	private JPanel contentPane;
	private JTextField txtFieldMarque;
	private JTextField txtFieldModele;
	private JTextField txtFieldOptions;
	private JTextField txtFieldPrix;
	private JTable table;
	private JButton btnLister;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnTotal;
	private JTextField txtFieldTotal;
	private JLabel lblTotal;

	public Interface() {
		setTitle("EXAMEN 1");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 815, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setLayout(null);
    	setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMarque.setBounds(52, 42, 97, 14);
		contentPane.add(lblMarque);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setHorizontalAlignment(SwingConstants.CENTER);
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModele.setBounds(52, 77, 97, 14);
		contentPane.add(lblModele);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOptions.setBounds(52, 114, 97, 14);
		contentPane.add(lblOptions);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrix.setBounds(52, 152, 97, 14);
		contentPane.add(lblPrix);
		
		txtFieldMarque = new JTextField();
		txtFieldMarque.setBounds(159, 40, 123, 20);
		contentPane.add(txtFieldMarque);
		txtFieldMarque.setColumns(10);
		
		txtFieldModele = new JTextField();
		txtFieldModele.setColumns(10);
		txtFieldModele.setBounds(159, 75, 123, 20);
		contentPane.add(txtFieldModele);
		
		txtFieldOptions = new JTextField();
		txtFieldOptions.setColumns(10);
		txtFieldOptions.setBounds(159, 112, 123, 20);
		contentPane.add(txtFieldOptions);
		
		txtFieldPrix = new JTextField();
		txtFieldPrix.setColumns(10);
		txtFieldPrix.setBounds(159, 150, 123, 20);
		contentPane.add(txtFieldPrix);
		
		btnLister = new JButton("Lister");
		btnLister.setFocusable(false);
		btnLister.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLister.setBounds(37, 288, 112, 23);
		btnLister.addActionListener(this);
		contentPane.add(btnLister);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjouter.setBounds(182, 288, 112, 23);
		btnAjouter.addActionListener(this);
		contentPane.add(btnAjouter);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBounds(37, 334, 112, 23);
		btnSupprimer.addActionListener(this);
		contentPane.add(btnSupprimer);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setBounds(182, 335, 112, 23);
		btnModifier.addActionListener(this);
		contentPane.add(btnModifier);
		
    	Object[] colonnes= {"Numero", "Marque", "Modele", "Options", "Prix"};
    	model= new DefaultTableModel() {
			@Override
    	    public Class<?> getColumnClass(int c) {
                switch (c) {
                	case 0:
                		return Integer.class;
                	default:
                		return String.class;
                }
    	}};
    	model.setColumnIdentifiers(colonnes);
    	
		table = new JTable(model);
	   	table.setRowHeight(30);
    	table.setAutoCreateRowSorter(true);
    	
    	DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
    	leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
    	table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		
    	JScrollPane sp= new JScrollPane(table);
		sp.setBounds(378, 50, 400, 335);
		contentPane.add(sp);
		
		lblTotal = new JLabel("Total = ");
		lblTotal.setVisible(false);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotal.setBounds(378, 410, 58, 14);
		contentPane.add(lblTotal);
		
		txtFieldTotal = new JTextField();
		txtFieldTotal.setVisible(false);
		txtFieldTotal.setEditable(false);
		txtFieldTotal.setBounds(434, 408, 86, 20);
		contentPane.add(txtFieldTotal);
		txtFieldTotal.setColumns(10);
		
		btnTotal = new JButton("Afficher Total");
		btnTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTotal.setBounds(102, 388, 130, 23);
		btnTotal.addActionListener(this);
		contentPane.add(btnTotal);
		
		JLabel lblNewLabel = new JLabel("EXAMEN 1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(490, 11, 175, 14);
		contentPane.add(lblNewLabel);
		
    	addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	        if (JOptionPane.showConfirmDialog(null, 
    	            "Voulez-vous mettre fin a votre session?", "Fermeture du programme", 
    	            JOptionPane.YES_NO_OPTION,
    	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
    	        	sauvegarderTableIndex();
    	            System.exit(0);
    	        }
    	    }
    	});
    	
		setVisible(true);
        try {
			chargerFichierBinaire();
		} catch (Exception e1) {
		}
	}
    
	private static void chargerFichierBinaire() throws Exception {
        File f= new File(FICHIER_BIN);
        if(!f.exists()) {
            ecrireDansFichierBinaire();
        }else {
    		chargerTableIndex();
    		tabNumRadios= new TreeSet<>();
            int num;
            tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
            for(Integer numRadio : tableIndex.keySet()) {
            	if( ( (Integer) tableIndex.get(numRadio)[1]) != 0) {
                	long addresse = (long) tableIndex.get(numRadio)[0];
                	tmpWriteBin.seek(addresse);
                	num= tmpWriteBin.readInt();
                    tabNumRadios.add(num);
            	}     
            }
            tmpWriteBin.close();
        }
	}
	
    private static void ecrireDansFichierBinaire() {
		tableIndex= new TreeMap<>();
		tabNumRadios= new TreeSet<>();
        String elems[]= new String[5];
        int num;
        Double prix;
        String marque, modele, options;
        try{
            tmpReadTexte= new BufferedReader(new FileReader(FICHIER_TXT));
            tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
            String ligne= tmpReadTexte.readLine();
            while(ligne != null ) {
                elems= ligne.split(";");
                num= Integer.parseInt(elems[0]);
                marque= elems[1];
                modele= elems[2];
                options= elems[3];
                prix= Double.parseDouble(elems[4]);
                long adresse= tmpWriteBin.getFilePointer();
                tmpWriteBin.writeInt(num);
                tmpWriteBin.writeUTF(marque);
                tmpWriteBin.writeUTF(modele);
                tmpWriteBin.writeUTF(options);
                tmpWriteBin.writeDouble(prix);
                Object[] mapValeur= {adresse, 1};
                tableIndex.put(num, mapValeur);
                tabNumRadios.add(num);
                ligne= tmpReadTexte.readLine();
            }
            tmpReadTexte.close();
            tmpWriteBin.close();
        }catch(Exception e) {
        	System.out.println("Gros probleme! "+e.getMessage());
        }
    }
    
	@SuppressWarnings("unchecked")
	private static void chargerTableIndex() throws ClassNotFoundException {
    	tableIndex= new TreeMap<>();
    	try {
        	File f= new File(FICHIER_TABLE_INDEX);
			FileInputStream fileInputStream= new FileInputStream(f);
			ObjectInputStream objInputStream= new ObjectInputStream(fileInputStream);
			tableIndex= (TreeMap<Integer, Object[]>) objInputStream.readObject();
			objInputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
		}	
    }
	
    private void sauvegarderTableIndex() {
    	try {
        	File f= new File(FICHIER_TABLE_INDEX);
			FileOutputStream fileOutputStream= new FileOutputStream(f);
			ObjectOutputStream objOutputStream= new ObjectOutputStream(fileOutputStream);
			objOutputStream.writeObject(tableIndex);
			objOutputStream.close();
		} catch (IOException e) {
		}
    }
    
	public void listerRadios() throws IOException {
		DecimalFormat df = new DecimalFormat("$###,###.##");
		Double prixAccumules= 0.00;
    	int num;
    	Double prix;
    	String marque, modele, options;
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
        for(Integer numRadio : tableIndex.keySet()) {
        	if( ( (Integer) tableIndex.get(numRadio)[1]) != 0) {
            	long addresse = (long) tableIndex.get(numRadio)[0];
            	tmpWriteBin.seek(addresse);
        	    num= tmpWriteBin.readInt();
        	    marque= tmpWriteBin.readUTF();
        	    modele= tmpWriteBin.readUTF();
        	    options= tmpWriteBin.readUTF();
        	    prix= tmpWriteBin.readDouble();
        	    Object[] rangee= {String.valueOf(num), marque, modele, options, String.valueOf(prix)};
        	    model.addRow(rangee);
        	    prixAccumules += prix;
        	}
        }     
	    txtFieldTotal.setText(df.format(prixAccumules));
        tmpWriteBin.close();      
    }
	
	@SuppressWarnings("rawtypes")
	public void ajouterRadio() throws IOException {
		if(txtFieldMarque.getText().equals("") || txtFieldModele.getText().equals("") || txtFieldOptions.getText().equals("") ||
		   txtFieldPrix.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Remplissez tous les champs svp");
		}else {
	        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
        	tmpWriteBin.seek(tmpWriteBin.length());
            long adresse= tmpWriteBin.getFilePointer();
			int num= (Integer) ((TreeSet) tabNumRadios).last()+1000;
			String marque= txtFieldMarque.getText();
			String modele= txtFieldModele.getText();
			String options= txtFieldOptions.getText();
			Double prix= Double.parseDouble(txtFieldPrix.getText());
			tmpWriteBin.writeInt(num);
			tmpWriteBin.writeUTF(marque);
			tmpWriteBin.writeUTF(modele);
			tmpWriteBin.writeUTF(options);
			tmpWriteBin.writeDouble(prix);
            Object[] mapValeur= {adresse, 1};
            tableIndex.put(num, mapValeur);
            tabNumRadios.add(num);
            Object[] rangee= {num, marque, modele, options, prix};
            model.addRow(rangee);
            tmpWriteBin.close();
		}
	}
	
	public void supprimerRadio() throws IOException {
		int num= Integer.parseInt(JOptionPane.showInputDialog("Entrez le numero de la radio: "));
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
        tmpWriteBin.seek( (long) tableIndex.get(num)[0] );
        tmpWriteBin.readInt();
        String marque= tmpWriteBin.readUTF();
        String modele= tmpWriteBin.readUTF();
        String options= tmpWriteBin.readUTF();
        Double prix= tmpWriteBin.readDouble();
        String radioStr= num+"  "+marque+"  "+modele+"  "+options+"  "+prix;
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		UIManager.put("OptionPane.noButtonText", "Non");
		UIManager.put("OptionPane.yesButtonText", "Oui");
		int choix= JOptionPane.showConfirmDialog(null, "( "+radioStr+" ) Voulez-vous vraiment supprimer cette radio?");
		switch(choix) {
			case JOptionPane.YES_OPTION:
				tableIndex.get(num)[1]= 0;
				break;
			default:
				break;
		}
		tmpWriteBin.close();
	}
	
	public void modifierOptions() throws IOException {
		int num= Integer.parseInt(JOptionPane.showInputDialog("Entrez le numero de la radio: "));
        tmpWriteBin= new RandomAccessFile(FICHIER_BIN, "rw");
        tmpWriteBin.seek( (long) tableIndex.get(num)[0] );
        tmpWriteBin.readInt();
        tmpWriteBin.readUTF();
        tmpWriteBin.readUTF();
        long adresseOptions= tmpWriteBin.getFilePointer();
        String options= tmpWriteBin.readUTF();
        String nouvelleOptions= JOptionPane.showInputDialog(null, "( Options actuelles: "+options+" ) Entrez le code des nouvelles options: ");
        tmpWriteBin.seek(adresseOptions);
        tmpWriteBin.writeUTF(nouvelleOptions.substring(0,3));
        tmpWriteBin.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLister) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			try {
				listerRadios();
			} catch (IOException e1) {
			}
		}
		if(e.getSource() == btnAjouter) {
			try {
				ajouterRadio();
			} catch (IOException e1) {
			}
		}
		if(e.getSource() == btnSupprimer) {
			try {
				supprimerRadio();
			} catch (IOException e1) {
			}
		}
		if(e.getSource() == btnModifier) {
			try {
				modifierOptions();
			} catch (IOException e1) {
			}
		}
		if(e.getSource() == btnTotal) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			try {
				listerRadios();
			} catch (IOException e1) {
			}
			lblTotal.setVisible(true);
			txtFieldTotal.setVisible(true);
		}
	}
}
