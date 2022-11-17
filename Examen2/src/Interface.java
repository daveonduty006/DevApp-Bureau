import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {

    static final String FICHIER_TXT = "src/data/profs.txt";
    static ArrayList<Professeur> listeProfs;
    static TreeSet<String> listeNoms;
    static Set<Integer> listePositions;
	static DefaultTableModel model;
    static BufferedReader tmpReadTexte;
    static JButton btnLister;
    static JButton btnModifier;
    static JButton btnAjouter;
    static JButton btnRetirer;
    static JButton btnStats;
    static int rangeeChoisie;
	static JTextField txtFieldNom;
	static JTextField txtFieldAutomne;
	static JTextField txtFieldHiver;
	static JTextField txtFieldPosition;
	private JPanel contentPane;
	private JTable table;
	static JTextField txtFieldMoyenne;
	static JTextField txtFieldPire;
	static JTextField txtFieldMeilleur;
	
	public Interface() {
		setTitle("EXAMEN 2");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 815, 499);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setLayout(null);
    	setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom: ");
		lblNom.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNom.setBounds(32, 21, 66, 27);
		contentPane.add(lblNom);
		
		JLabel lblAutomne = new JLabel("Automne: ");
		lblAutomne.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblAutomne.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAutomne.setBounds(32, 59, 101, 27);
		contentPane.add(lblAutomne);
		
		JLabel lblHiver = new JLabel("Hiver: ");
		lblHiver.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblHiver.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHiver.setBounds(32, 97, 101, 27);
		contentPane.add(lblHiver);
		
		JLabel lblPositionDanciennet = new JLabel("Position d'Anciennet\u00E9 ");
		lblPositionDanciennet.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblPositionDanciennet.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPositionDanciennet.setBounds(32, 135, 221, 27);
		contentPane.add(lblPositionDanciennet);
		
		JLabel lblResultats = new JLabel("R\u00E9sultats");
		lblResultats.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultats.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblResultats.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblResultats.setBounds(475, 11, 188, 35);
		contentPane.add(lblResultats);
		
		txtFieldNom = new JTextField();
		txtFieldNom.setBounds(144, 27, 112, 20);
		txtFieldNom.setColumns(10);
		contentPane.add(txtFieldNom);
		
		txtFieldAutomne = new JTextField();
		txtFieldAutomne.setColumns(10);
		txtFieldAutomne.setBounds(144, 67, 112, 20);
		contentPane.add(txtFieldAutomne);
		
		txtFieldHiver = new JTextField();
		txtFieldHiver.setColumns(10);
		txtFieldHiver.setBounds(144, 103, 112, 20);
		contentPane.add(txtFieldHiver);
		
		txtFieldPosition = new JTextField();
		txtFieldPosition.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldPosition.setBounds(95, 173, 94, 20);
		txtFieldPosition.setColumns(10);
		contentPane.add(txtFieldPosition);
		
		btnLister = new JButton("Lister");
		btnLister.setFocusable(false);
		btnLister.setBounds(277, 79, 89, 23);
		btnLister.addActionListener(this);
		contentPane.add(btnLister);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setFocusable(false);
		btnModifier.setBounds(277, 135, 89, 23);
		btnModifier.addActionListener(this);
		contentPane.add(btnModifier);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setFocusable(false);
		btnAjouter.setBounds(44, 232, 89, 23);
		btnAjouter.addActionListener(this);
		contentPane.add(btnAjouter);
		
		btnRetirer = new JButton("Retirer");
		btnRetirer.setFocusable(false);
		btnRetirer.setBounds(154, 232, 89, 23);
		btnRetirer.addActionListener(this);
		contentPane.add(btnRetirer);
		
		btnStats = new JButton("Voir Statistiques");
		btnStats.setFocusable(false);
		btnStats.setBounds(40, 284, 209, 23);
		btnStats.addActionListener(this);
		contentPane.add(btnStats);
		
    	Object[] colonnes= {"Nom", "Automne", "Hiver", "Annee", "Temps"};
    	model= new DefaultTableModel();
    	model.setColumnIdentifiers(colonnes);
		
		table = new JTable(model);
	   	table.setRowHeight(30);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				rangeeChoisie= table.getSelectedRow();
				String nom = model.getValueAt(rangeeChoisie, 0).toString();
				txtFieldNom.setText(model.getValueAt(rangeeChoisie, 0).toString());
				txtFieldAutomne.setText(model.getValueAt(rangeeChoisie, 1).toString());
				txtFieldHiver.setText(model.getValueAt(rangeeChoisie, 2).toString());
				for(Professeur unProf : listeProfs) {
					if(nom.equals(unProf.getNom())) {
						txtFieldPosition.setText(String.valueOf(unProf.getPosition()));
						break;
					}
				}
			}
		});
	    rangeeChoisie = table.getSelectedRow();
    	
    	JScrollPane sp= new JScrollPane(table);
		sp.setBounds(385, 54, 374, 382);
		contentPane.add(sp);
		
		JLabel lblMoyenne = new JLabel("Moyenne des T\u00E2ches");
		lblMoyenne.setForeground(UIManager.getColor("Button.background"));
		lblMoyenne.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoyenne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMoyenne.setBounds(46, 332, 209, 14);
		contentPane.add(lblMoyenne);
		
		JLabel lblPireTache = new JLabel("Pire T\u00E2che");
		lblPireTache.setForeground(UIManager.getColor("Button.background"));
		lblPireTache.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPireTache.setHorizontalAlignment(SwingConstants.CENTER);
		lblPireTache.setBounds(32, 394, 80, 14);
		contentPane.add(lblPireTache);
		
		JLabel lblMeilleurTache = new JLabel("Meilleur T\u00E2che");
		lblMeilleurTache.setForeground(UIManager.getColor("Button.background"));
		lblMeilleurTache.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeilleurTache.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMeilleurTache.setBounds(170, 394, 112, 14);
		contentPane.add(lblMeilleurTache);
		
		txtFieldMoyenne = new JTextField();
		txtFieldMoyenne.setEditable(false);
		txtFieldMoyenne.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldMoyenne.setBounds(108, 357, 86, 20);
		txtFieldMoyenne.setColumns(10);
		contentPane.add(txtFieldMoyenne);
		
		txtFieldPire = new JTextField();
		txtFieldPire.setEditable(false);
		txtFieldPire.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldPire.setBounds(30, 419, 86, 20);
		txtFieldPire.setColumns(10);
		contentPane.add(txtFieldPire);
		
		txtFieldMeilleur = new JTextField();
		txtFieldMeilleur.setEditable(false);
		txtFieldMeilleur.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldMeilleur.setColumns(10);
		txtFieldMeilleur.setBounds(184, 419, 86, 20);
		contentPane.add(txtFieldMeilleur);
		
    	addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    	        if (JOptionPane.showConfirmDialog(null, 
    	            "Voulez-vous mettre fin a votre session?", "Fermeture du programme", 
    	            JOptionPane.YES_NO_OPTION,
    	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
    	        	//sauvegarderTableIndex();
    	            System.exit(0);
    	        }
    	    }
    	});
    	
		setVisible(true);
		
    	chargerFichierTexte();			
	}
	
	public static void calculer() {
		int totalTaches= 0;
		TreeSet<Integer> listeTaches= new TreeSet<>();
		for(Professeur unProf : listeProfs) {
			totalTaches += unProf.calculerTache();
			listeTaches.add(unProf.calculerTache());
		}
		double moyenne= totalTaches/listeProfs.size();
		txtFieldMoyenne.setText(String.valueOf(moyenne));
		txtFieldPire.setText(String.valueOf(listeTaches.first()));
		txtFieldMeilleur.setText(String.valueOf(listeTaches.last()));	
	}
	
	public static void retirer() {
		int index= -1;
		String nom = model.getValueAt(rangeeChoisie, 0).toString();
		for(int i = 0; i < listeProfs.size(); i++) {
			if(nom.equals(listeProfs.get(i).getNom())) {
				index = i;
				break;
			}
		}
		listeProfs.remove(index);
		listeNoms.remove(nom);
		listePositions.remove(Integer.parseInt(txtFieldPosition.getText()));
		JOptionPane.showMessageDialog(null, "Retrait a la liste apporte");
	}
	
	public static void ajouter() {
		if( txtFieldNom.getText().equals("") || txtFieldAutomne.getText().equals("") || txtFieldHiver.getText().equals("") || txtFieldPosition.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs svp");
		}else {
			String nom= txtFieldNom.getText();
			int tacheAutomne= Integer.parseInt(txtFieldAutomne.getText());
			int tacheHiver= Integer.parseInt(txtFieldHiver.getText());
			int position = ((TreeSet<Integer>)listePositions).last() + 1;
		    Professeur unProf= new Professeur(nom, tacheAutomne, tacheHiver, position);
			listeProfs.add(unProf);
			listeNoms.add(nom);
			listePositions.add(position);
			JOptionPane.showMessageDialog(null, "Ajout a la liste apporte");
		}
	}
	
	public static void modifier() {
		int index= -1;
		String nom = model.getValueAt(rangeeChoisie, 0).toString();
		for(int i = 0; i < listeProfs.size(); i++) {
			if(nom.equals(listeProfs.get(i).getNom())) {
				index = i;
				break;
			}
		}
		int tacheAutomne= Integer.parseInt(txtFieldAutomne.getText());
		int tacheHiver= Integer.parseInt(txtFieldHiver.getText());
		int position= Integer.parseInt(txtFieldPosition.getText());
		listeProfs.get(index).setTacheAutomne(tacheAutomne);
		listeProfs.get(index).setTacheHiver(tacheHiver);
		listeProfs.get(index).setPosition(position);
		JOptionPane.showMessageDialog(null, "Modification a la liste apportee");
	}
	
	public static void listerProfs() {
    	String nom, message;
    	int tacheHiver, tacheAutomne, tacheAnnee;
    	for(String unNom : listeNoms) {
    		for(Professeur unProf : listeProfs) {
    			if(unProf.getNom().equalsIgnoreCase(unNom)) {
    	        	nom= unProf.getNom();
    	        	tacheAutomne= unProf.getTacheAutomne();
    	        	tacheHiver= unProf.getTacheHiver();
    	        	tacheAnnee= tacheAutomne + tacheHiver;
    	        	message= unProf.message();
    	        	Object[] rangee= {nom, String.valueOf(tacheAutomne), String.valueOf(tacheHiver), String.valueOf(tacheAnnee), message};
    	        	model.addRow(rangee);
    	        	break;
    			}
    		}
    	} 
	}
	
	private static void chargerFichierTexte() {
		listeProfs= new ArrayList<>();
		listeNoms= new TreeSet<>(); 
		listePositions= new TreeSet<>();
        String elems[]= new String[4];
        String nom;
        int tacheAutomne, tacheHiver, position;
        try{
            tmpReadTexte= new BufferedReader(new FileReader(FICHIER_TXT));
            String ligne= tmpReadTexte.readLine();
            while(ligne != null ) {
                elems= ligne.split(";");
                nom= elems[0];
                tacheAutomne= Integer.parseInt(elems[1]);
                tacheHiver= Integer.parseInt(elems[2]);
                position= Integer.parseInt(elems[3]);
                Professeur unProf= new Professeur(nom, tacheAutomne, tacheHiver, position);
                listeProfs.add(unProf);
                listeNoms.add(nom);
                listePositions.add(position);
                ligne= tmpReadTexte.readLine();
            }
            tmpReadTexte.close();
        }catch(Exception e) {
        	System.out.println("Gros probleme! "+e.getMessage());
        }
        listerProfs();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLister) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			listerProfs();
		}
		if(e.getSource() == btnModifier) {
			if(rangeeChoisie < 0) {
				JOptionPane.showMessageDialog(null, "Veuillez choisir une rangee svp");
			}else {
				modifier();
			}
		}
		if(e.getSource() == btnAjouter) {
			ajouter();
		}
		if(e.getSource() == btnRetirer) {
			if(rangeeChoisie < 0) {
				JOptionPane.showMessageDialog(null, "Veuillez choisir une rangee svp");
			}else {
				retirer();
			}
		}
		if(e.getSource() == btnStats) {
			calculer();
		}
	}
}
