package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOUtilisateurJDBC;
import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.Utilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {
    private static Connection connection;   // On établie la connexion
    private static DAOUtilisateurJDBC daoUtilisateurJDBC;

    public static void main(String[] args) throws SQLException {
        connection = Database.getDBConnection();
        daoUtilisateurJDBC = new DAOUtilisateurJDBC();

        DAOUtilisateurJDBC daoJDBC = Test.getDaoUtilisateurJDBC();
        List<Utilisateur> listUtilisateur;
        listUtilisateur = daoJDBC.findAll();    //Obtention du login
        System.out.println(listUtilisateur);
    }

    public static DAOUtilisateurJDBC getDaoUtilisateurJDBC(){return daoUtilisateurJDBC;}

    public static Connection getDBConnection() {
        return connection;
    }
}
