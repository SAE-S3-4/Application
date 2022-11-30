package fr.univ_amu.iut.database.dao;

import fr.univ_amu.iut.database.Question;

import java.sql.SQLException;
import java.util.List;

public interface DAOQuestion extends DAO<Question, String>{
    List<Question> findAll() throws SQLException;
}
