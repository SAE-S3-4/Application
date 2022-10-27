package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.database.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface DAOUtilisateur extends DAO<Utilisateur, String>{

    /**
     * Renvoie tous les utilisateurs
     * @return List<Utilisateur>
     * @throws SQLException
     */
    List<Utilisateur> findAll() throws SQLException;
}
