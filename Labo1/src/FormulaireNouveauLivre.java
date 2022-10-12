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
	
	static String numLivre, titre, numAuteur, annee, pages, categorie;
	static Set<Integer> tabNumLivresExistants;
	private JTextField textFieldNumLivre;
	private JTextField textFieldTitre;
	private JTextField textFieldNumAuteur;
	private JTextField textFieldAnnee;
	private JTextField textFieldPages;
	JRadioButton rdbtnRoman;
	JRadioButton rdbtnSuspense;
	JRadioButton rdbtnBD;
	JRadioButton rdbtnNouvelle;
	JButton btnConfirmer;
	JButton btnAnnuler;
	
	FormulaireNouveauLivre(JFrame base, String titre, Set<Integer> tabNumLivres) {
		tabNumLivresExistants= (TreeSet<Integer>) tabNumLivres;
		
		setTitle(titre);
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
		getContentPane().add(textFieldTitre, "4, 4, 3, 1, fill, default");
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
		getContentPane().add(rdbtnRoman, "2, 14");
		
		rdbtnSuspense = new JRadioButton("Suspense");
		getContentPane().add(rdbtnSuspense, "4, 14");
		
		rdbtnBD = new JRadioButton("Bande-Dessinée");
		getContentPane().add(rdbtnBD, "2, 16");
		
		rdbtnNouvelle = new JRadioButton("Nouvelle");
		getContentPane().add(rdbtnNouvelle, "4, 16");
		
		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(btnConfirmer, "2, 18, right, default");
		btnConfirmer.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(btnAnnuler, "6, 18, left, default");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConfirmer) {
			Boolean check= false;
			while(!check) {
				numLivre= textFieldNumLivre.getText();
				
			}
		}
		
	}
	

}
