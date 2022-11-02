package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOQuestionJDBC;
import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.Question;
import fr.univ_amu.iut.database.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {
    private static Connection connection;   // On établie la connexion
    private static DAOUsersJDBC daoUtilisateurJDBC;

    public static void main(String[] args) throws SQLException {
        connection = Database.getDBConnection();
        daoUtilisateurJDBC = new DAOUsersJDBC();

        DAOUsersJDBC daoJDBC = Test.getDaoUtilisateurJDBC();
        List<User> listUsers;
        listUsers = daoJDBC.findAll();

        System.out.println(listUsers);
    }

    public static DAOUsersJDBC getDaoUtilisateurJDBC(){return daoUtilisateurJDBC;}

    public static Connection getDBConnection() {
        return connection;
    }
}
