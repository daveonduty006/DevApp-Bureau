package dao_controleurs;

import java.util.ArrayList;
import java.util.List;
import dao_modeles.Livre;
import dao_modeles.LivreDao;

public class ControleurLivre implements ILivreRequetes{
	
    private static ControleurLivre CtrL_Instance = null;
    private static LivreDao Dao_Instance = null;

    private ControleurLivre(){}

    public static synchronized ControleurLivre getControleurLivre() {
        try {
            if(CtrL_Instance == null) {
                CtrL_Instance = new ControleurLivre();
                Dao_Instance = LivreDao.getLivreDao();
            }
            return CtrL_Instance;
        }catch(Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
	@Override
	public String CtrL_Enregistrer(Livre livre) {
        String message;
        message = Dao_Instance.MdlL_Enregistrer(livre);
        return message;
    }

	@Override
	public List<Livre> CtrL_GetAll() {
		List<Livre> listeLivres = new ArrayList<>();
		listeLivres = Dao_Instance.MdlL_GetAll();
		return listeLivres;
	}

	@Override
	public Livre CtrL_GetByIdf(int idf) {
		Livre livre = Dao_Instance.MdlL_GetByIdf(idf);
		return livre;
	}

	@Override
	public List<Livre> CtrL_GetByCateg(String categ) {
		List<Livre> listeLivres = new ArrayList<>();
		listeLivres = Dao_Instance.MdlL_GetByCateg(categ);
		return listeLivres;
	}

	@Override
	public List<Livre> CtrL_GetByNumAuteur(int numAuteur) {
		List<Livre> listeLivres = new ArrayList<>();
		listeLivres = Dao_Instance.MdlL_GetByNumAuteur(numAuteur);
		return listeLivres;
	}

	@Override
	public String CtrF_ModifierTitre(Livre livre) {
		String message;
		message = Dao_Instance.MdlF_ModifierTitre(livre);
		return message;
	}

	@Override
	public String CtrF_Supprimer(int idf) {
		String message;
		message = Dao_Instance.MdlF_Supprimer(idf);
		return message;
	}

}
