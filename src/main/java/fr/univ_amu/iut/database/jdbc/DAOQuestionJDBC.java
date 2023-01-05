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
    private final PreparedStatement getTotalNumberOfQuestions;

    public static DAOQuestionJDBC daoQuestionJDBC;

    /**
     * Constructor used to initialize the statements
     *
     * @throws SQLException
     */
    public DAOQuestionJDBC() throws SQLException {
        //Requête
        findAllQuestions = connection.prepareStatement("SELECT * FROM QUESTION");
        getTotalNumberOfQuestions = connection.prepareStatement("SELECT COUNT(*) FROM QUESTION");
    }

    /**
     * Method used to initialize the DAO
     *
     * @throws SQLException
     */
    public static void initDAOQuestionsJDBC() throws SQLException {
        daoQuestionJDBC = new DAOQuestionJDBC();
    }

    /**
     *
     * @return the current object
     */
    public static DAOQuestionJDBC getDAOQuestionsJDB(){return daoQuestionJDBC;}

    /**
     *
     * @return all the questions in the DB
     * @throws SQLException
     */
    @Override
    public List<Question> findAll() throws SQLException {
        List<Question> questions = new ArrayList<>();
        ResultSet resultSet = findAllQuestions.executeQuery();

        while(resultSet.next()) {
            questions.add(initQuestion(resultSet));
        }
        return questions;
    }

    /**
     *
     * @param id
     * @return the question based on it's ID from the DB
     */
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

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Question insert(Question obj) {
        return null;
    }

    /**
     *
     * @param obj
     */
    @Override
    public void delete(Question obj) {

    }

    /**
     *
     * @param obj
     */
    @Override
    public void update(Question obj) {

    }

    /**
     * Method used to initialize a Question object using a result set
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Question initQuestion(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setID(resultSet.getInt(1));
        question.setText(resultSet.getString(2));
        question.setTitle(resultSet.getString(3));
        question.setSuggestion(resultSet.getString(4));
        question.setSolution(resultSet.getString(5));
        return question;
    }

    public int getGetTotalNumberOfQuestions() {
        try {
            ResultSet resultSet = getTotalNumberOfQuestions.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
