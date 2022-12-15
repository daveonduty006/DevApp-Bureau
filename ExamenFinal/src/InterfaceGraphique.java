
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;


@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame implements ActionListener{
	
	static JTable table;
	static DefaultTableModel model;
	static JScrollPane sp;
	static Set<Integer> tabIdp;
    static Set<String> tabVilles;
    static JComboBox<String> comboBoxVilles;
    static JComboBox<Integer> comboBoxPatients;
    static JButton btnLister;
    static JButton btnListerNonFumeurs;
    static JButton btnSupprimer;
    static JButton btnAjouter;
    static boolean ecoute;
  
    InterfaceGraphique() throws Exception {

    	this.setTitle("Examen Final");
		ImageIcon logo= new ImageIcon("logo.jpg"); 
		this.setIconImage(logo.getImage());
    	this.getContentPane().setBackground(Color.GRAY);
    	this.getContentPane().setForeground(Color.white);
    	this.setBounds(100,100,857,710);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
    	this.getContentPane().setLayout(null);
    	this.setLocationRelativeTo(null);
    	
    	btnLister= new JButton("LISTER TOUS LES PATIENTS");
    	btnLister.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnLister.setFocusable(false);
    	btnLister.addActionListener(this);
    	btnListerNonFumeurs= new JButton("LISTER TOUS LES PATIENTS NON-FUMEURS");
    	btnListerNonFumeurs.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnListerNonFumeurs.setFocusable(false);
    	btnListerNonFumeurs.addActionListener(this);
    	btnAjouter= new JButton("AJOUTER UN PATIENT");
    	btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 12));
    	btnAjouter.setFocusable(false);
    	btnAjouter.addActionListener(this);
    	
    	JToolBar toolBar = new JToolBar();
    	toolBar.setFloatable(false);
    	toolBar.setBounds(10, 11, 821, 31);
    	toolBar.addSeparator(new Dimension(15,10));
    	toolBar.add(btnLister);
    	toolBar.addSeparator(new Dimension(65,10));  	
    	toolBar.add(btnListerNonFumeurs);
    	toolBar.addSeparator(new Dimension(65,10)); 
    	toolBar.add(btnAjouter);
    	toolBar.setRollover(true);
    	this.getContentPane().add(toolBar);    	
    	
    	String[] colonnes= {"ID du Patient", "Nom", "Prenom", "Date de Naissance", "Sexe", "Adresse", 
                "Code Postal", "Fumeur"};
    	model= new DefaultTableModel() {
			@Override
    	    public Class<?> getColumnClass(int c) {
                switch(c) {
                	case 0:
                		return Integer.class;
                	default:
                		return String.class;
                }
			}
    	};
    	model.setColumnIdentifiers(colonnes);
    	table= new JTable(model);
    	table.setRowHeight(30);
    	table.setAutoCreateRowSorter(true);
    	table.getRowSorter().toggleSortOrder(0);
    	
    	DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
    	leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
    	table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
    	
    	sp= new JScrollPane(table);
    	sp.setBounds(10, 53, 821, 475);
    	this.getContentPane().add(sp);
    	
    	comboBoxPatients = new JComboBox<>();
    	comboBoxPatients.setBounds(10, 568, 193, 36);
    	comboBoxPatients.addActionListener(this);
    	this.getContentPane().add(comboBoxPatients);
    	
    	comboBoxVilles = new JComboBox<>();
    	comboBoxVilles.setBounds(668, 568, 163, 36);
    	comboBoxVilles.addActionListener(this);
    	this.getContentPane().add(comboBoxVilles);
    	
    	JLabel lbl2 = new JLabel("VILLES DE NOS PATIENTS");
    	lbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
    	lbl2.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl2.setBounds(668, 539, 163, 26);
    	this.getContentPane().add(lbl2);
    	
    	btnSupprimer = new JButton("Supprimer");
    	btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
    	btnSupprimer.setBounds(227, 573, 113, 26);
    	btnSupprimer.addActionListener(this);
    	this.getContentPane().add(btnSupprimer);
    	
    	JLabel lbl1 = new JLabel("NOS PATIENTS");
    	lbl1.setHorizontalAlignment(SwingConstants.CENTER);
    	lbl1.setFont(new Font("Tahoma", Font.BOLD, 13));
    	lbl1.setBounds(10, 539, 193, 26);
    	this.getContentPane().add(lbl1);
    	
    	BufferedImage image= ImageIO.read(new File("background.jpg"));
    	
    	JLabel imgLabel = new JLabel(new ImageIcon(image));
    	imgLabel.setBounds(0, 631, 841, 40); 	
    	getContentPane().add(imgLabel);
    	
    	this.addWindowListener(new WindowAdapter() {
    	    @Override
    	    public void windowClosing(WindowEvent e) {
    			UIManager.put("OptionPane.noButtonText", "Non");
    			UIManager.put("OptionPane.yesButtonText", "Oui");
    	        if (JOptionPane.showConfirmDialog(
    	        	null, "Voulez-vous mettre fin a votre session?", "Fermeture du programme", 
    	            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) 
    	        	== JOptionPane.YES_OPTION){
    	            System.exit(0);
    	        }
    	    }
    	});
    	
        getRootPane().setBorder(BorderFactory.createBevelBorder(10, Color.BLACK, Color.GRAY));
    	ecoute= true;
        chargerInterface();
    	this.setVisible(true);
    }
    
	public void ajouterPatient(Patient nouveauPatient) {
		ControleurPatient CtrP = ControleurPatient.getControleurPatient();
		String message = CtrP.CtrP_Enregistrer(nouveauPatient);
		JOptionPane.showMessageDialog(null, message); 
    }
    
    public void supprimerPatient(int idp) {
		ControleurPatient CtrP = ControleurPatient.getControleurPatient();
		String message = CtrP.CtrP_Supprimer(idp);
        JOptionPane.showMessageDialog(null, message);	
    }

    
    public void listerVilleChoisi(String villeChoisi) {
    	int idp, fumeur;
    	String nom, prenom, daten, sexe, adresse, cp;
        ControleurPatient CtrP = ControleurPatient.getControleurPatient();
        List<Patient> listePatients = new ArrayList<>();
        listePatients = CtrP.CtrP_GetByVille(villeChoisi);
        for(Patient patient : listePatients) {
        	idp= patient.getIdp();
        	nom= patient.getNom();
            prenom= patient.getPrenom();
            daten= patient.getDaten();
            sexe= patient.getSexe();
            adresse= patient.getAdresse();
            cp= patient.getCp();
            fumeur= patient.getFumeur();
    	    Object[] rangee= {idp,nom,prenom,daten,sexe,adresse,cp,fumeur};
    	    model.addRow(rangee);
        }    
    }
    
    public void listerNonFumeurs() {
    	int idp, fumeur;
    	String nom, prenom, daten, sexe, adresse, cp;
        ControleurPatient CtrP = ControleurPatient.getControleurPatient();
        List<Patient> listePatients = new ArrayList<>();
        listePatients = CtrP.CtrP_GetNonFumeurs();
        for(Patient patient : listePatients) {
        	idp= patient.getIdp();
        	nom= patient.getNom();
            prenom= patient.getPrenom();
            daten= patient.getDaten();
            sexe= patient.getSexe();
            adresse= patient.getAdresse();
            cp= patient.getCp();
            fumeur= patient.getFumeur();
    	    Object[] rangee= {idp,nom,prenom,daten,sexe,adresse,cp,fumeur};
    	    model.addRow(rangee);
        }    
    }
    
    public void listerPatientChoisi(int unIdp) {
    	int idp, fumeur;
    	String nom, prenom, daten, sexe, adresse, cp;
        ControleurPatient CtrP = ControleurPatient.getControleurPatient();
        Patient patient = CtrP.CtrP_GetByIdp(unIdp);
    	idp= patient.getIdp();
    	nom= patient.getNom();
        prenom= patient.getPrenom();
        daten= patient.getDaten();
        sexe= patient.getSexe();
        adresse= patient.getAdresse();
        cp= patient.getCp();
        fumeur= patient.getFumeur();
	    Object[] rangee= {idp,nom,prenom,daten,sexe,adresse,cp,fumeur};
	    model.addRow(rangee);
    }
    
	public void listerPatients() {
    	int idp, fumeur;
    	String nom, prenom, daten, sexe, adresse, cp;
        ControleurPatient CtrP = ControleurPatient.getControleurPatient();
        List<Patient> listePatients = new ArrayList<>();
        listePatients = CtrP.CtrP_GetAll();
        for(Patient patient : listePatients) {
        	idp= patient.getIdp();
        	nom= patient.getNom();
            prenom= patient.getPrenom();
            daten= patient.getDaten();
            sexe= patient.getSexe();
            adresse= patient.getAdresse();
            cp= patient.getCp();
            fumeur= patient.getFumeur();
    	    Object[] rangee= {idp,nom,prenom,daten,sexe,adresse,cp,fumeur};
    	    model.addRow(rangee);
        }    
    }
		
	private void chargerComboBoxPatients() {
		comboBoxPatients.removeAllItems();
		for(Integer idp : tabIdp) {
			comboBoxPatients.addItem(idp);
		}
	}
	
	private void chargerComboBoxVilles() {
		comboBoxVilles.removeAllItems();
		for(String ville : tabVilles) {
			comboBoxVilles.addItem(ville);
		}
	}
	
	private void chargerInterface() {
		tabIdp= new TreeSet<>();
    	tabVilles= new TreeSet<>();
    	int idp, fumeur;
    	String nom, prenom, daten, sexe, adresse, ville, cp;
        ControleurPatient CtrP = ControleurPatient.getControleurPatient();
        List<Patient> listePatients = new ArrayList<>();
        listePatients = CtrP.CtrP_GetAll();
        for(Patient patient : listePatients) {
        	idp= patient.getIdp();
        	nom= patient.getNom();
            prenom= patient.getPrenom();
            daten= patient.getDaten();
            sexe= patient.getSexe();
            adresse= patient.getAdresse();
            ville= adresse.split(", ")[1];
            cp= patient.getCp();
            fumeur= patient.getFumeur();
            tabIdp.add(idp);
            tabVilles.add(ville);
    	    Object[] rangee= {idp,nom,prenom,daten,sexe,adresse,cp,fumeur};
    	    model.addRow(rangee);
        }
        chargerComboBoxPatients();
        chargerComboBoxVilles();
        listerPatients();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLister) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    listerPatients();
		}
		if(e.getSource() == btnListerNonFumeurs) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    listerNonFumeurs();
		}
		if(e.getSource() == btnAjouter) {
			FormNouveauPatient form= new FormNouveauPatient(this, "Admission d'un Patient");
			form.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			form.setVisible(true);
			if(!form.patient.getNom().equals("")) {
				ajouterPatient(form.patient);
		    	ecoute= false;
		    	tabIdp.add(form.patient.getIdp());
		    	String ville = form.patient.getAdresse().split(", ")[1];
		        tabVilles.add(ville);
		    	chargerComboBoxPatients();
		    	chargerComboBoxVilles();
		    	ecoute= true;
			}
		}
		if(e.getSource() == comboBoxPatients && ecoute) {
			int idpChoisi= (Integer) comboBoxPatients.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
			listerPatientChoisi(idpChoisi);
		}
		if(e.getSource() == comboBoxVilles && ecoute) {
			String villeChoisi= (String) comboBoxVilles.getSelectedItem();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    dtm.setNumRows(0);
		    listerVilleChoisi(villeChoisi);
		}
		if(e.getSource() == btnSupprimer) {
			int idpChoisi= (Integer) comboBoxPatients.getSelectedItem();
			supprimerPatient(idpChoisi);
	    	ecoute= false;
	    	tabIdp.remove(idpChoisi);
	    	chargerComboBoxPatients();
	    	ecoute= true; 	
		}
	}
}