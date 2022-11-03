package dao_modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivreDao implements ILivreDao{
	
	// DAO CLASS ATTRIBUTES
    private static Connection conn = null;
    private static LivreDao instanceDao = null;

    // MYSQL CONNECTION INPUTS
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String URL_BD = "jdbc:mysql://localhost:3306/bdlivres";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // CRUD - Create Read Update Delete
    private static final String ENREGISTRER = "INSERT INTO livres VALUES(0, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM livres ORDER BY idf";
    private static final String GET_BY_IDF = "SELECT * FROM livres WHERE idf=?";
    private static final String GET_BY_CATEG = "SELECT * FROM livres WHERE categ=? ORDER BY idf";
    private static final String GET_BY_NUM_AUTEUR = "SELECT * FROM livres WHERE numAuteur=? ORDER BY idf";
    private static final String MODIFIER_TITRE = "UPDATE livres SET titre=? WHERE idf=?";
    private static final String SUPPRIMER = "DELETE FROM livres WHERE idf=?";
    
    // PRIVATE CONSTRUCTOR  
    private LivreDao(){}

    // DATABASE CONNECTION METHOD 
    public static synchronized LivreDao getLivreDao () {
        try {
            //Class.forName(PILOTE);
            if(instanceDao == null) {
                instanceDao = new LivreDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            }
            return instanceDao;
        }catch(Exception e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
	// DATABASE DECONNECTION METHODS
    private static void MdlL_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            }catch(SQLException e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlL_Fermer(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }catch(SQLException e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    // CREATE
	@Override
    public String MdlL_Enregistrer(Livre livre) {
        PreparedStatement stmt = null;
        String message = "Livre non-enregistre";
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, livre.getTitre());
            stmt.setInt(2, livre.getNumAuteur());
            stmt.setInt(3, livre.getAnnee());
            stmt.setInt(4, livre.getPages());
            stmt.setString(5, livre.getCateg());    
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()) {
                livre.setIdf(rs.getInt(1));
            }
            message = "Livre bien enregistre";
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return message;
    }

	// READ
	@Override
	public List<Livre> MdlL_GetAll() {
        PreparedStatement stmt = null;
        List<Livre> listeLivres = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Livre livre = new Livre();
                livre.setIdf(rs.getInt("idf"));
                livre.setTitre(rs.getString("titre"));
                livre.setNumAuteur(rs.getInt("numAuteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                listeLivres.add(livre);
            }
        }catch(SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return listeLivres;
    }
	
	@Override
	public Livre MdlL_GetByIdf(int idf) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(GET_BY_IDF);
            stmt.setInt(1, idf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Livre livre = new Livre();
                livre.setIdf(rs.getInt("idf"));
                livre.setTitre(rs.getString("titre"));
                livre.setNumAuteur(rs.getInt("numAuteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                return livre;
            }else {
                return null;
            }
        }catch(SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
	}

	@Override
	public List<Livre> MdlL_GetByCateg(String categ) {
        PreparedStatement stmt = null;
        List<Livre> listeLivres = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(GET_BY_CATEG);
            stmt.setString(1, categ);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Livre livre = new Livre();
                livre.setIdf(rs.getInt("idf"));
                livre.setTitre(rs.getString("titre"));
                livre.setNumAuteur(rs.getInt("numAuteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                listeLivres.add(livre);
            }
        }catch(SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return listeLivres;
	}

	@Override
	public List<Livre> MdlL_GetByNumAuteur(int numAuteur) {
        PreparedStatement stmt = null;
        List<Livre> listeLivres = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(GET_BY_NUM_AUTEUR);
            stmt.setInt(1, numAuteur);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Livre livre = new Livre();
                livre.setIdf(rs.getInt("idf"));
                livre.setTitre(rs.getString("titre"));
                livre.setNumAuteur(rs.getInt("numAuteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                listeLivres.add(livre);
            }
        }catch(SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return listeLivres;
	}

    // UPDATE
	@Override
	public String MdlL_ModifierTitre(Livre livre) {
        PreparedStatement stmt = null;
        String message = "Titre du livre non-modifie";
        try {
            stmt = conn.prepareStatement(MODIFIER_TITRE);
            stmt.setString(1, livre.getTitre());
            stmt.setInt(2, livre.getIdf());
            stmt.executeUpdate();
            message = "Titre du livre bien modifie";
        }catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return message;
	}

	// DELETE
	@Override
	public String MdlL_Supprimer(int idf) {
        PreparedStatement stmt = null;
        String message = "Livre non-supprime";
        try {
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, idf);
            stmt.executeUpdate();
            message = "Livre bien supprime";
        }catch(SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return message;
	}	

}
