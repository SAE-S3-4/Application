package fr.univ_amu.iut.database.jdbc;

import fr.univ_amu.iut.database.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DAOQuestionJDBCTest {
    @BeforeEach
    public void initTest(){
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void initDAOQuestionsJDBC() throws SQLException {
        DAOQuestionJDBC.initDAOQuestionsJDBC();
        assertEquals(DAOQuestionJDBC.daoQuestionJDBC, DAOQuestionJDBC.getDAOQuestionsJDB());
    }

    @Test
    public void findAll() throws SQLException {
        DAOQuestionJDBC.initDAOQuestionsJDBC();
        assertEquals(DAOQuestionJDBC.daoQuestionJDBC.findAll().toString(), DAOQuestionJDBC.getDAOQuestionsJDB().findAll().toString());
    }

    @Test
    public void findQuestionById() throws SQLException {
        DAOQuestionJDBC.initDAOQuestionsJDBC();
        int id = 1;
        assertEquals(DAOQuestionJDBC.daoQuestionJDBC.findQuestionById(id).toString(), DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(id).toString());
    }

    @Test
    public void getGetTotalNumberOfQuestions() throws SQLException {
        DAOQuestionJDBC.initDAOQuestionsJDBC();
        assertEquals(DAOQuestionJDBC.daoQuestionJDBC.getGetTotalNumberOfQuestions(), DAOQuestionJDBC.getDAOQuestionsJDB().getGetTotalNumberOfQuestions());
    }
}