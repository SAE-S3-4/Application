package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.Test;
import fr.univ_amu.iut.database.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUtilisateurJDBC implements DAOUtilisateur{

    private final Connection connection = Test.getDBConnection();
    private final PreparedStatement findAllStatement;

    /**
     * Constructeur | Initialise tout les prepareStatement
     *
     * @throws SQLException
     */
    public DAOUtilisateurJDBC() throws SQLException {
        //Requête
        findAllStatement = connection.prepareStatement("SELECT * FROM QUESTION");
    }

    /**
     * Renvoie tous les utilisateurs
     * @return List<Utilisateur>
     * @throws SQLException
     */
    @Override
    public List<Utilisateur> findAll() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        ResultSet resultSet = findAllStatement.executeQuery();

        while(resultSet.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setID(resultSet.getString(1));
            utilisateur.setMotDePasse(resultSet.getString(2));
            utilisateurs.add(utilisateur);
        }
        return utilisateurs;
    }

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     * @return Utilisateur
     * @param obj Utilisateur à insérer dans la base
     */
    @Override
    public Utilisateur insert(Utilisateur obj) {
        return null;
    }

    /**
     * Permet la suppression d'un tuple de la base
     * @return boolean
     * @param obj Utilisateur à supprimer dans la base
     */
    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    /**
     * Permet de mettre à jour les données d'un tuple dans la base à partir d'un objet passé en paramètre
     * @return boolean
     * @param obj Utilisateur à mettre à jour dans la base
     */
    @Override
    public boolean update(Utilisateur obj) {
        return false;
    }
}
