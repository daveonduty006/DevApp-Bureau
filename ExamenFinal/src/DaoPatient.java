import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoPatient {
    private static Connection conn = null;
    private static DaoPatient instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String URL_BD = "jdbc:mysql://localhost/bdhopital";
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM patients WHERE idp=?";
    private static final String GET_ALL = "SELECT * FROM patients ORDER BY idp";
    private static final String GET_BY_ID = "SELECT * FROM patients WHERE idp=?";
    private static final String GET_BY_VILLE = "SELECT * FROM patients WHERE adresse LIKE ?";
    private static final String GET_NONFUMEURS = "SELECT * FROM patients WHERE fumeur=0";
    private static final String ENREGISTRER = "INSERT INTO patients VALUES(0,?,?,?,?,?,?,?)";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoPatient(){};
    
    public static synchronized DaoPatient getPatientDao () {
        try {
            // Class.forName(PILOTE);
            instanceDao = new DaoPatient();
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            return instanceDao;
        } catch (Exception e) {
            System.out.println("================================================================================================ ERREUR, getPatientDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }
    
    // Create
    public String MdlP_Enregistrer(Patient patient) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());           
            stmt.setString(3, patient.getDaten());
            stmt.setString(4, patient.getSexe());
            stmt.setString(5, patient.getAdresse());
            stmt.setString(6, patient.getCp());
            stmt.setInt(7, patient.getFumeur());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                patient.setIdp(rs.getInt(1));
            }
            return "Patient bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
    }

    // Read
    public List<Patient> MdlP_GetAll() {
        PreparedStatement stmt = null;
        List<Patient> listePatients = new ArrayList<Patient>();
        try {
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setSexe(rs.getString("sexe"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setFumeur(rs.getInt("fumeur"));
                listePatients.add(patient);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
        return listePatients;
    }

    public Patient MdlP_GetByIdp(int idp) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idp);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setSexe(rs.getString("sexe"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setFumeur(rs.getInt("fumeur"));
                return patient;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
    }
    
    public List<Patient> MdlP_GetByVille(String uneVille) {
        PreparedStatement stmt = null;
        List<Patient> listePatients = new ArrayList<Patient>();
        try {
            stmt = conn.prepareStatement(GET_BY_VILLE);
            stmt.setString(1, "%"+uneVille+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setSexe(rs.getString("sexe"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setFumeur(rs.getInt("fumeur"));
                listePatients.add(patient);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
        return listePatients;
    }
    
    public List<Patient> MdlP_GetNonFumeurs() {
        PreparedStatement stmt = null;
        List<Patient> listePatients = new ArrayList<Patient>();
        try {
            stmt = conn.prepareStatement(GET_NONFUMEURS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setSexe(rs.getString("sexe"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setFumeur(rs.getInt("fumeur"));
                listePatients.add(patient);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
        return listePatients;
    }
    
    

    // Delete
    public String MdlP_Supprimer(int idp) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, idp);
            stmt.executeUpdate();
            return "Dossier du patient bien supprimé";
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
    }
   
    private static void MdlP_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlP_Fermer(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}