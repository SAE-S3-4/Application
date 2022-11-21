package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.database.User;

import java.sql.SQLException;
import java.util.List;

public interface DAOUsers extends DAO<User, String> {
    List<User> findAll() throws SQLException;

    List<User> getLeaderBoard() throws SQLException;
}
