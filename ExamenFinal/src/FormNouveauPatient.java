import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

@SuppressWarnings("serial")
public class FormNouveauPatient extends JDialog implements ActionListener {
	
	public Patient patient = new Patient();
	
	JTextField textFieldNom;
	JTextField textFieldPrenom;
	JTextField textFieldDaten;
	JRadioButton rdbtnHomme;
	JRadioButton rdbtnFemme;
	JRadioButton rdbtnAutre;
	JButton btnConfirmer;
	JButton btnAnnuler;
	JLabel lblErrChamps;
	private JLabel lblAdresse;
	private JTextField textFieldAdresse;
	private JLabel lblVille;
	private JLabel lblProvince;
	private JLabel lblCodePostal;
	private JLabel lblFumeur;
	private JRadioButton rdbtnOui;
	private JRadioButton rdbtnNon;
	private JTextField textFieldVille;
	private JTextField textFieldProvince;
	private JTextField textFieldCp;
	
	FormNouveauPatient(JFrame base, String titre) {		
		setTitle(titre);
		setMinimumSize(new Dimension(540,540));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(111dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:86dlu:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNom, "2, 4");
		
		textFieldNom = new JTextField();
		getContentPane().add(textFieldNom, "4, 4, 3, 1, left, default");
		textFieldNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblPrenom, "2, 6");
		
		textFieldPrenom = new JTextField();
		getContentPane().add(textFieldPrenom, "4, 6, left, default");
		textFieldPrenom.setColumns(10);
		
		JLabel lblDaten = new JLabel("Date de Naissance");
		lblDaten.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblDaten, "2, 8");
		
		textFieldDaten = new JTextField();
		getContentPane().add(textFieldDaten, "4, 8, left, default");
		textFieldDaten.setColumns(10);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblSexe, "2, 10");
		
		ButtonGroup groupSexe= new ButtonGroup();
		
		rdbtnHomme = new JRadioButton("Homme");
		rdbtnHomme.setFocusable(false);
		getContentPane().add(rdbtnHomme, "4, 10");
		groupSexe.add(rdbtnHomme);
		
		rdbtnFemme = new JRadioButton("Femme");
		rdbtnFemme.setFocusable(false);
		getContentPane().add(rdbtnFemme, "4, 12");
		groupSexe.add(rdbtnFemme);
		
		rdbtnAutre = new JRadioButton("Autre");
		rdbtnAutre.setFocusable(false);
		getContentPane().add(rdbtnAutre, "4, 14");
		groupSexe.add(rdbtnAutre);
		
		lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblAdresse, "2, 16, left, default");
		
		textFieldAdresse = new JTextField();
		textFieldAdresse.setColumns(10);
		getContentPane().add(textFieldAdresse, "4, 16, fill, default");
		
		lblVille = new JLabel("Ville");
		lblVille.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblVille, "2, 18, left, default");
		
		textFieldVille = new JTextField();
		textFieldVille.setColumns(10);
		getContentPane().add(textFieldVille, "4, 18, fill, default");
		
		lblProvince = new JLabel("Province");
		lblProvince.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblProvince, "2, 20, left, default");
		
		textFieldProvince = new JTextField();
		textFieldProvince.setColumns(10);
		getContentPane().add(textFieldProvince, "4, 20, fill, default");
		
		lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblCodePostal, "2, 22, left, default");
		
		textFieldCp = new JTextField();
		textFieldCp.setColumns(10);
		getContentPane().add(textFieldCp, "4, 22, fill, default");
		
		lblFumeur = new JLabel("Fumeur");
		lblFumeur.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblFumeur, "2, 24");
		
		ButtonGroup groupFumeur= new ButtonGroup();
		
		rdbtnOui = new JRadioButton("Oui");
		rdbtnOui.setFocusable(false);
		getContentPane().add(rdbtnOui, "4, 24");
		groupFumeur.add(rdbtnOui);
		
		rdbtnNon = new JRadioButton("Non");
		rdbtnNon.setFocusable(false);
		getContentPane().add(rdbtnNon, "4, 26");
		groupFumeur.add(rdbtnNon);
		
		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(btnConfirmer, "2, 30, right, default");
		btnConfirmer.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(btnAnnuler, "6, 30, left, default");
		btnAnnuler.addActionListener(this);
		
		lblErrChamps = new JLabel("");
		lblErrChamps.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErrChamps.setForeground(Color.RED);
		getContentPane().add(lblErrChamps, "6, 6, left, default");
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConfirmer) {
			lblErrChamps.setText("");
			Boolean check= false;
			//
			if(textFieldNom.getText()      != null	 &&
			   textFieldPrenom.getText()   != null   &&
			   textFieldDaten.getText()    != null   &&
			   (rdbtnHomme.isSelected() || 
			    rdbtnFemme.isSelected() ||
			    rdbtnAutre.isSelected())             &&
			   textFieldAdresse.getText()  != null   &&
		       textFieldVille.getText()    != null   &&
		       textFieldProvince.getText() != null   &&
		       textFieldCp.getText()       != null   &&
		       (rdbtnOui.isSelected() || 
		        rdbtnNon.isSelected())) {
				
		    //
				check= true;
			}else {
				lblErrChamps.setText("Remplir tous les champs svp");
			}
			if(check) {
				patient.setNom(textFieldNom.getText());
				patient.setPrenom(textFieldPrenom.getText());
				patient.setDaten(textFieldDaten.getText());
				if(rdbtnHomme.isSelected()) {
					patient.setSexe("M");
				}else if(rdbtnFemme.isSelected()) {
					patient.setSexe("F");
				}else if(rdbtnAutre.isSelected()) {
					patient.setSexe("A");
				}
				String adresseComplete = textFieldAdresse.getText()+", "+textFieldVille.getText()+", "+textFieldProvince.getText();
				patient.setAdresse(adresseComplete);
				patient.setCp(textFieldCp.getText());
				if(rdbtnOui.isSelected()) {
					patient.setFumeur(1);
				}else if(rdbtnNon.isSelected()) {
					patient.setFumeur(0);
				}
				FormNouveauPatient.this.setVisible(false);
			}
		}
		if(e.getSource() == btnAnnuler) {
			patient.setNom("");
			patient.setPrenom("");
			patient.setDaten("");
			patient.setSexe("");
			patient.setAdresse("");
			patient.setCp("");
			patient.setFumeur(0);
			textFieldNom.setText("");
			textFieldPrenom.setText("");
			textFieldDaten.setText("");
			rdbtnHomme.setSelected(false);
			rdbtnFemme.setSelected(false);
			rdbtnAutre.setSelected(false);
			textFieldAdresse.setText("");
			textFieldVille.setText("");
			textFieldProvince.setText("");
			textFieldCp.setText("");
			rdbtnOui.setSelected(false);
			rdbtnNon.setSelected(false);
			FormNouveauPatient.this.setVisible(false);
		}		
	}
	
}