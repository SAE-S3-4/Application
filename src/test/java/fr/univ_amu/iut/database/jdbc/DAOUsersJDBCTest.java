package fr.univ_amu.iut.database.jdbc;

import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class DAOUsersJDBCTest {

    private static DAOUsersJDBC daoUsersJDBC;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        daoUsersJDBC = DAOUsersJDBC.getDAOUsersJDBC();
        DAOUsersJDBC.initDAOUsersJDBC();
    }

    @Test
    public void testFindAll() throws SQLException {
        List<User> users = daoUsersJDBC.findAll();
        assertNotNull(users);
        assertEquals(users.size(), daoUsersJDBC.findAll().size());
    }

    @Test
    public void testGetLeaderBoard() throws SQLException {
        ArrayList<User> users = daoUsersJDBC.getLeaderBoard();
        assertNotNull(users);
        assertEquals(users.size(), daoUsersJDBC.getLeaderBoard().size());
    }

    @Test
    public void testInitialiseDatabaseLoginByNickname() throws SQLException {
        User user = daoUsersJDBC.initialiseDatabaseLoginByNickname("test");
        assertNotNull(user);
        assertEquals(user.getNickname(), "test");
    }

    /**
    @Test
    public void testUpdateUserScore() throws SQLException {
        LoginController.userLogged  = daoUsersJDBC.initialiseDatabaseLoginByNickname("a");
        LoginController.userLogged .setScore(50);
        daoUsersJDBC.updateUserScore(LoginController.userLogged );
        assertNotNull(LoginController.userLogged );
        assertEquals(LoginController.userLogged .getScore(), 50);
    }
 **/
}