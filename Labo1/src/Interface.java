import java.awt.event.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener{
	  
	static BufferedReader tmpReadTxt;
    static RandomAccessFile tmpWriteBin;
    static File fichierBin;
    static File fichierTxt;
    
    Interface() throws FileNotFoundException {
    	obtenirFichierBinaire();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    	this.setSize(920,520);
    	
    	
    	
    	this.setVisible(true);
    }
	
	private void obtenirFichierBinaire() throws FileNotFoundException {
        fichierBin= new File("src/data/livres.bin");
        if(!fichierBin.exists()) {
            JButton open= new JButton();
            JFileChooser fc= new JFileChooser();
            fc.setDialogTitle("Emplacement du fichier livres.txt");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            	fichierTxt= fc.getSelectedFile();  
            	tmpReadTxt= new BufferedReader(new FileReader(fichierTxt));
                ecrireFichierBinaire();
            }
        }      
	}
	
	private void ecrireFichierBinaire() throws FileNotFoundException {
        String elems[]= new String[6];
        int num, numAuteur, annee, pages;
        String titre, categorie;
        try{
            tmpWriteBin= new RandomAccessFile("src/data/films.bin", "rw");
            String ligne= tmpReadTxt.readLine();
            while(ligne != null ) {
                elems= ligne.split(";");
                num= Integer.parseInt(elems[0]);
                titre= elems[1];
                numAuteur= Integer.parseInt(elems[2]);
                annee= Integer.parseInt(elems[3]);
                pages= Integer.parseInt(elems[4]);
                categorie= elems[5];
                tmpWriteBin.writeInt(num);
                tmpWriteBin.writeUTF(titre);
                tmpWriteBin.writeInt(numAuteur);
                tmpWriteBin.writeInt(annee);
                tmpWriteBin.writeInt(pages);
                tmpWriteBin.writeUTF(categorie);
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
		// TODO Auto-generated method stub
		
	}

}
