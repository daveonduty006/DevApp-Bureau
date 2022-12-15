import java.util.List;

public class ControleurPatient {

    private static ControleurPatient CtrP_Instance = null;
    private static DaoPatient Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurPatient() est devenu une zone critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurPatient(){};

    public static synchronized ControleurPatient getControleurPatient() {
        try {
        	CtrP_Instance = new ControleurPatient();
            Dao_Instance = DaoPatient.getPatientDao();
            return CtrP_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurPatient(), e= " + e);
            throw new RuntimeException(e);
        }
    }
    
    public String CtrP_Enregistrer(Patient patient) {
        String message = null;
        message = Dao_Instance.MdlP_Enregistrer(patient);
        return message;
    }
    
    public List<Patient> CtrP_GetAll() {
        return Dao_Instance.MdlP_GetAll();
    }
    
    public Patient CtrP_GetByIdp(int idp) {
        return Dao_Instance.MdlP_GetByIdp(idp);
    }
    
    public List<Patient> CtrP_GetByVille(String uneVille) {
        return Dao_Instance.MdlP_GetByVille(uneVille);
    }
    
    public List<Patient> CtrP_GetNonFumeurs() {
        return Dao_Instance.MdlP_GetNonFumeurs();
    }
    
    public String CtrP_Supprimer(int idp) {
        String message = null;
        message = Dao_Instance.MdlP_Supprimer(idp);
        return message;
    }
    
}