package dao_controleurs;

import java.util.List;
import dao_modeles.Livre;

public interface ILivreRequetes {
	
    // Pour le CRUD - Create Read Update Delete

    // Create
    public String CtrL_Enregistrer(Livre livre);
    
    // Read
    public List<Livre> CtrL_GetAll();
    
    public Livre CtrL_GetByIdf(int idf);

    public List<Livre> CtrL_GetByCateg(String categ);
    
    public List<Livre> CtrL_GetByNumAuteur(int numAuteur);
      
    // Update
    public String CtrF_ModifierTitre(Livre livre);

    // Delete
    public String CtrF_Supprimer(int idf);

}
