package dao_modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.java.dao.models.modelFilm.Film;

public class LivreDao implements ILivreDao{
	
	//private static int port = 8081;
    private static Connection conn = null;
    private static LivreDao instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String URL_BD = "jdbc:mysql://localhost:3306/bdlivres";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // CRUD - Create Read Update Delete
    private static final String ENREGISTRER = "INSERT INTO films VALUES(0, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM films ORDER BY idf";
    private static final String GET_BY_IDF = "SELECT * FROM films WHERE idf=?";
    private static final String GET_BY_CATEG = "SELECT * FROM films WHERE categ=? ORDER BY idf";
    private static final String GET_BY_NUM_AUTEUR = "SELECT * FROM films WHERE numAuteur=? ORDER BY idf";
    private static final String MODIFIER_TITRE = "UPDATE films SET titre=? WHERE idf=?";
    private static final String SUPPRIMER = "DELETE FROM films WHERE idf=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zone critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private LivreDao(){}
    
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

    // CREATE
	@Override
    public String MdlL_Enregistrer(Livre livre) {
        PreparedStatement stmt = null;
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
            return "Livre bien enregistré";
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
    }

	// READ
	@Override
	public List<Livre> MdlL_GetAll() {
        PreparedStatement stmt = null;
        List<Livre> listeLivres = new ArrayList<Livre>();
        try {
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
	public List<Livre> MdlL_GetByCateg(String categ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Livre MdlL_GetByIdf(int idf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livre> MdlL_GetByNumAuteur(int numAuteur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int MdlF_ModifierTitre(Livre livre) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int MdlF_Supprimer(Livre livre) {
		// TODO Auto-generated method stub
		return 0;
	}
	
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
	
	

}
