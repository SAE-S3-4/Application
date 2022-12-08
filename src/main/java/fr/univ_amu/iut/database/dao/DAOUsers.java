package fr.univ_amu.iut.database.dao;

import fr.univ_amu.iut.database.User;

import java.sql.SQLException;
import java.util.List;

public interface DAOUsers extends DAO<User, String> {
    List<User> findAll() throws SQLException;

    User initialiseDatabaseLoginByNickname(String Nickname) throws SQLException;

    List<User> getLeaderBoard() throws SQLException;

    void updateUserScore(User user) throws SQLException;
}
