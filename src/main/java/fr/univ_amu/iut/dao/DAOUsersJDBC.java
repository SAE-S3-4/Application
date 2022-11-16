package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.Test;
import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUsersJDBC implements DAOUsers {
    private final Connection connection = Database.getConnetion();
    private final PreparedStatement findAllUsers;

    public static DAOUsersJDBC daoUsersJDBC;

    public DAOUsersJDBC() throws SQLException {
        //Requête
        findAllUsers = connection.prepareStatement("SELECT * FROM USERS");
    }

    public static void initDAOUsersJDBC() throws SQLException {
        daoUsersJDBC = new DAOUsersJDBC();
    }

    public static DAOUsersJDBC getDAOUsersJDBC(){return daoUsersJDBC;}

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = findAllUsers.executeQuery();

        while(resultSet.next()) {
            User user = new User();
            user.setId_user(resultSet.getInt(1));
            user.setNickname(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setScore(resultSet.getInt(4));
            user.setPassword(resultSet.getString(5));
            users.add(user);
        }
        return users;
    }

    @Override
    public User insert(User obj) throws SQLException {
        return null;
    }

    @Override
    public void delete(User obj) throws SQLException {

    }

    @Override
    public void update(User obj) throws SQLException {

    }
}
