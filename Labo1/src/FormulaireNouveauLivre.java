import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

@SuppressWarnings("serial")
public class FormulaireNouveauLivre extends JDialog implements ActionListener {
	
	static Set<Integer> tabNumLivresExistants;
	private int numLivre, numAuteur, annee, pages;
	private String titre, categorie;
	JTextField textFieldNumLivre;
	JTextField textFieldTitre;
	JTextField textFieldNumAuteur;
	JTextField textFieldAnnee;
	JTextField textFieldPages;
	JRadioButton rdbtnRoman;
	JRadioButton rdbtnSuspense;
	JRadioButton rdbtnBD;
	JRadioButton rdbtnNouvelle;
	JButton btnConfirmer;
	JButton btnAnnuler;
	JLabel lblErrChamps;
	JLabel lblErrNumLivre;
	
	FormulaireNouveauLivre(JFrame base, String titre, Set<Integer> tabNumLivres) {
		tabNumLivresExistants= (TreeSet<Integer>) tabNumLivres;
		
		setTitle(titre);
		setMinimumSize(new Dimension(540,310));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(111dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Numéro du nouveau Livre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel, "2, 2");
		
		textFieldNumLivre = new JTextField();
		getContentPane().add(textFieldNumLivre, "4, 2, left, default");
		textFieldNumLivre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre du nouveau Livre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel_1, "2, 4");
		
		textFieldTitre = new JTextField();
		getContentPane().add(textFieldTitre, "4, 4, 3, 1, left, default");
		textFieldTitre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Numéro de l'Auteur");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel_2, "2, 6");
		
		textFieldNumAuteur = new JTextField();
		getContentPane().add(textFieldNumAuteur, "4, 6, left, default");
		textFieldNumAuteur.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Année de Publication");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel_3, "2, 8");
		
		textFieldAnnee = new JTextField();
		getContentPane().add(textFieldAnnee, "4, 8, left, default");
		textFieldAnnee.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre de Pages");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel_4, "2, 10");
		
		textFieldPages = new JTextField();
		getContentPane().add(textFieldPages, "4, 10, left, default");
		textFieldPages.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Catégorie du Livre");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().add(lblNewLabel_5, "2, 12");
		
		rdbtnRoman = new JRadioButton("Roman");
		rdbtnRoman.setFocusable(false);
		getContentPane().add(rdbtnRoman, "2, 14");
		
		rdbtnSuspense = new JRadioButton("Suspense");
		rdbtnSuspense.setFocusable(false);
		getContentPane().add(rdbtnSuspense, "4, 14");
		
		rdbtnBD = new JRadioButton("Bande-Dessinée");
		rdbtnBD.setFocusable(false);
		getContentPane().add(rdbtnBD, "2, 16");
		
		rdbtnNouvelle = new JRadioButton("Nouvelle");
		rdbtnNouvelle.setFocusable(false);
		getContentPane().add(rdbtnNouvelle, "4, 16");
		
		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(btnConfirmer, "2, 18, right, default");
		btnConfirmer.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(btnAnnuler, "6, 18, left, default");
		
		ButtonGroup group= new ButtonGroup();
		group.add(rdbtnRoman);
		group.add(rdbtnSuspense);
		group.add(rdbtnBD);
		group.add(rdbtnNouvelle);
		
		lblErrChamps = new JLabel("");
		lblErrChamps.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErrChamps.setForeground(Color.RED);
		getContentPane().add(lblErrChamps, "6, 6, left, default");
		
		lblErrNumLivre = new JLabel("");
		lblErrNumLivre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErrNumLivre.setForeground(Color.RED);
		getContentPane().add(lblErrNumLivre, "6, 2, left, default");
				
	}
	
	public int getNumLivre() {
		return this.numLivre;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getNumAuteur() {
		return this.numAuteur;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public String getCategorie() {
		return this.categorie;
	}
	
	private void setNumLivre(int unNum) {
		this.numLivre= unNum;
	}
	
	private void setTitre(String unTitre) {
		this.titre= unTitre;
	}
	
	private void setNumAuteur(int unNum) {
		this.numAuteur= unNum;
	}
	
	private void setAnnee(int uneAnnee) {
		this.annee= uneAnnee;
	}
	
	private void setPages(int unNumPages) {
		this.pages= unNumPages;
	}
	
	private void setCategorie(String uneCategorie) {
		this.categorie= uneCategorie;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConfirmer) {
			lblErrNumLivre.setText("");
			lblErrChamps.setText("");
			Boolean check= false;
			if(textFieldNumLivre.getText() != null  &&
			   textFieldTitre.getText() != null	    &&
			   textFieldNumAuteur.getText() != null &&
			   textFieldAnnee.getText() != null     &&
			   textFieldPages.getText() != null     &&
			   (rdbtnRoman.isSelected()    || 
			    rdbtnSuspense.isSelected() ||
			    rdbtnBD.isSelected()       ||
			    rdbtnNouvelle.isSelected()   )        ) {
				int numNouveauLivre= Integer.parseInt(textFieldNumLivre.getText());
				if(!tabNumLivresExistants.contains(numNouveauLivre)) {
					check= true;
				}else{
					lblErrNumLivre.setText("Numero de Livre deja attitre");
				}
			}else {
				lblErrChamps.setText("Remplir tous les champs svp");
			}
			if(check) {
				setNumLivre(Integer.parseInt(textFieldNumLivre.getText()));
				setTitre(textFieldTitre.getText());
				setNumAuteur(Integer.parseInt(textFieldNumAuteur.getText()));
				setAnnee(Integer.parseInt(textFieldAnnee.getText()));
				setPages(Integer.parseInt(textFieldPages.getText()));
				if(rdbtnRoman.isSelected()) {
					setCategorie("roman");
				}else if(rdbtnSuspense.isSelected()) {
					setCategorie("suspense");
				}else if(rdbtnBD.isSelected()) {
					setCategorie("bandes dessinees");
				}else if(rdbtnNouvelle.isSelected()) {
					setCategorie("nouvelle");
				}
				FormulaireNouveauLivre.this.setVisible(false);
			}
		}
		if(e.getSource() == btnAnnuler) {
			setNumLivre(0);
			setTitre("");
			setNumAuteur(0);
			setAnnee(0);
			setPages(0);
			setCategorie("");
			FormulaireNouveauLivre.this.setVisible(false);
		}		
	}
	
}
