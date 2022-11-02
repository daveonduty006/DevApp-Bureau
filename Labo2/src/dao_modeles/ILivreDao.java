package dao_modeles;

import java.util.List;

public interface ILivreDao {
	
    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlL_Enregistrer(Livre livre);
    
    // Read
    public List<Livre> MdlL_GetAll();

    public List<Livre> MdlL_GetByCateg(String categ);

    public Livre MdlL_GetByIdf(int idf);
    
    public List<Livre> MdlL_GetByNumAuteur(int numAuteur);
      
    // Update
    public int MdlF_ModifierTitre(Livre livre);

    // Delete
    public int MdlF_Supprimer(Livre livre);

}
