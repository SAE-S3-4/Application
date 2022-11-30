package fr.univ_amu.iut.database.jdbc;

import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.Question;
import fr.univ_amu.iut.database.dao.DAOQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOQuestionJDBC implements DAOQuestion {

    private final Connection connection = Database.getConnetion();
    private final PreparedStatement findAllQuestions;

    public static DAOQuestionJDBC daoQuestionJDBC;

    /**
     * Constructeur | Initialise tout les prepareStatement
     *
     * @throws SQLException
     */
    public DAOQuestionJDBC() throws SQLException {
        //Requête
        findAllQuestions = connection.prepareStatement("SELECT * FROM QUESTION");
    }

    public static void initDAOQuestionsJDBC() throws SQLException {
        daoQuestionJDBC = new DAOQuestionJDBC();
    }

    public static DAOQuestionJDBC getDAOQuestionsJDB(){return daoQuestionJDBC;}

    @Override
    public List<Question> findAll() throws SQLException {
        List<Question> questions = new ArrayList<>();
        ResultSet resultSet = findAllQuestions.executeQuery();

        while(resultSet.next()) {
            questions.add(initQuestion(resultSet));
        }
        return questions;
    }

    public Question findQuestionById(int id){
        try {
            PreparedStatement findQuestionById = connection.prepareStatement("SELECT * FROM QUESTION WHERE Q_ID = "+id);
            ResultSet resultSet = findQuestionById.executeQuery();
            resultSet.next();
            return initQuestion(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Question insert(Question obj) {
        return null;
    }

    @Override
    public void delete(Question obj) {

    }

    @Override
    public void update(Question obj) {

    }

    private Question initQuestion(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setID(resultSet.getInt(1));
        question.setText(resultSet.getString(2));
        question.setTitle(resultSet.getString(3));
        question.setSuggestion(resultSet.getString(4));
        question.setSolution(resultSet.getString(5));
        return question;
    }
}
