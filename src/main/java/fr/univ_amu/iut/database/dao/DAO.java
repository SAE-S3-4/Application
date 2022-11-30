package fr.univ_amu.iut.database.dao;

import java.sql.SQLException;

public interface DAO<Entity, Key> {

    Entity insert(Entity obj) throws SQLException;

    void delete(Entity obj) throws SQLException;

    void update(Entity obj) throws SQLException;

}
