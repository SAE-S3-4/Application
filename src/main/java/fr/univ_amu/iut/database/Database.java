package fr.univ_amu.iut.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    /**
     * Chaine de connection, URL, LOGIN, PASSWORD
     */
    private static final String CONNECT_URL = "jdbc:postgresql://lucky.db.elephantsql.com/xpirrwid";
    private static final String LOGIN = "xpirrwid";
    private static final String PASSWORD = "LkhxflJA_GDQQI_nqpkJBIbFBc955fiL";
    private static Connection connection;

    /**
     * Retourne la connection direct de la base de données ElephantSQL
     *
     * @return Connection
     */
    public static Connection getDBConnection(){
        // Connexion a la base
        System.out.println("Connexion a " + CONNECT_URL);

        try {
            connection = DriverManager.getConnection(CONNECT_URL,LOGIN,PASSWORD);
            System.out.println("Lien effectue avec la base de données. Connecte\n");
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
            System.out.println("Lien non effectue avec la base de données. Deconnecte\n");
            return null;
        }

    }

    /**
     * Permet de fermer la connexion avec la base de données
     * @return boolean
     */
    public static boolean closeDBConnection() {
        try {
            connection.close();
            System.out.println("Connexion fermée");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
