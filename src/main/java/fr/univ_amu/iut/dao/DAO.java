package fr.univ_amu.iut.dao;

import java.sql.SQLException;

public interface DAO<Entity, Key> {

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     * @return Entity
     * @param obj Objet à insérer dans la base
     */
    Entity insert(Entity obj) throws SQLException;

    /**
     * Permet la suppression d'un tuple de la base
     * @return boolean
     * @param obj Objet à supprimer dans la base
     */
    boolean delete(Entity obj) throws SQLException;

    /**
     * Permet de mettre à jour les données d'un tuple dans la base à partir d'un objet passé en paramètre
     * @return boolean
     * @param obj Objet à mettre à jour dans la base
     */
    boolean update(Entity obj) throws SQLException;

}
