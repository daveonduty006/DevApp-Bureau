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
            if(Dao_Instance == null) {
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
		if(Dao_Instance == null) {
			getControleurLivre();
		}
        String message;       
        message = Dao_Instance.MdlL_Enregistrer(livre);
		Dao_Instance = null;
        return message;
    }

	@Override
	public List<Livre> CtrL_GetAll() {
		if(Dao_Instance == null) {
			getControleurLivre();
		}
		List<Livre> listeLivres = new ArrayList<>();
		listeLivres = Dao_Instance.MdlL_GetAll();
		Dao_Instance = null;
		return listeLivres;
	}

	@Override
	public Livre CtrL_GetByIdf(int idf) {
		if(Dao_Instance == null) {
			getControleurLivre();
		}
		Livre livre = Dao_Instance.MdlL_GetByIdf(idf);
		Dao_Instance = null;
		return livre;
	}

	@Override
	public List<Livre> CtrL_GetByCateg(String categ) {
		if(Dao_Instance == null) {
			getControleurLivre();
		}
		List<Livre> listeLivres = new ArrayList<>();
		listeLivres = Dao_Instance.MdlL_GetByCateg(categ);
		Dao_Instance = null;
		return listeLivres;
	}

	@Override
	public List<Livre> CtrL_GetByNumAuteur(int numAuteur) {
		if(Dao_Instance == null) {
			getControleurLivre();
		}
		List<Livre> listeLivres = new ArrayList<>();
		listeLivres = Dao_Instance.MdlL_GetByNumAuteur(numAuteur);
		Dao_Instance = null;
		return listeLivres;
	}

	@Override
	public String CtrL_ModifierTitre(Livre livre) {
		if(Dao_Instance == null) {
			getControleurLivre();
		}
		String message;
		message = Dao_Instance.MdlL_ModifierTitre(livre);
		Dao_Instance = null;
		return message;
	}

	@Override
	public String CtrL_Supprimer(int idf) {
		if(Dao_Instance == null) {
			getControleurLivre();
		}
		String message;
		message = Dao_Instance.MdlL_Supprimer(idf);
		Dao_Instance = null;
		return message;
	}

}
