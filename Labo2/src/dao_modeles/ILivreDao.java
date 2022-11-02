package dao_modeles;

import java.util.List;

public interface ILivreDao {
	
    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlL_Enregistrer(Livre livre);
    
    // Read
    public List<Livre> MdlL_GetAll();
    
    public Livre MdlL_GetByIdf(int idf);

    public List<Livre> MdlL_GetByCateg(String categ);
    
    public List<Livre> MdlL_GetByNumAuteur(int numAuteur);
      
    // Update
    public String MdlF_ModifierTitre(Livre livre);

    // Delete
    public String MdlF_Supprimer(int idf);

}
