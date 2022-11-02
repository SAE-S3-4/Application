package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.Test;
import fr.univ_amu.iut.database.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOQuestionJDBC implements DAOQuestion {

    private final Connection connection = Test.getDBConnection();
    private final PreparedStatement findAllQuestions;

    /**
     * Constructeur | Initialise tout les prepareStatement
     *
     * @throws SQLException
     */
    public DAOQuestionJDBC() throws SQLException {
        //Requête
        findAllQuestions = connection.prepareStatement("SELECT * FROM QUESTION");
    }

    @Override
    public List<Question> findAll() throws SQLException {
        List<Question> questions = new ArrayList<>();
        ResultSet resultSet = findAllQuestions.executeQuery();

        while(resultSet.next()) {
            Question question = new Question();
            question.setID(resultSet.getString(1));
            question.setText(resultSet.getString(2));
            questions.add(question);
        }
        return questions;
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
}
